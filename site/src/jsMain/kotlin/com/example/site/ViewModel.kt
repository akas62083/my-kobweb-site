package com.example.site

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ViewModel {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val client = HttpClient()

    fun handleKey(key: String) {
        if(key.length == 1) {
            val c = key[0]
            if(c in 'a'..'z') {
                if(isWritingEnglish()) addEnglishText(c)

            } else if(c in 'A'..'Z') {
                if(isWritingEnglish()) addEnglishText(c)

            } else if(c in '0'..'9') {
                if(isWritingEnglish()) addEnglishText(c)

            } else if(c == ' ') {
                if(isWritingEnglish()) addEnglishText(c)

            } else if(c == '.') {
                if(isWritingEnglish()) addEnglishText(c)

            } else if(c == ',') {
                if(isWritingEnglish()) addEnglishText(c)

            } else if(c == '?') {
                if(isWritingEnglish()) addEnglishText(c)

            } else if(c == '!') {
                if(isWritingEnglish()) addEnglishText(c)

            } else if(c == '"') {
                if(isWritingEnglish()) addEnglishText(c)

            } else if(c == '\'') {
                if(isWritingEnglish()) addEnglishText(c)

            }
        } else {
            val k = key.lowercase()
            if(k == "enter") {
                if(uiState.value.pageState is Pages.English && (uiState.value.pageState as Pages.English).status is EnglishStatus.Waiting){
                    pushStartButton()
                } else if(isWritingEnglish()) {
                    if(uiState.value.sendText) {
                        pushSendButton()
                        _uiState.update { cs ->
                            cs.copy(sendText = false)
                        }
                    } else {
                        _uiState.update { cs ->
                            cs.copy(sendText = true)
                        }
                    }
                } else if(isNextProblemsEnglish()) {
                    if(uiState.value.pageState is Pages.English && (uiState.value.pageState as Pages.English).status is EnglishStatus.Solving && ((uiState.value.pageState as Pages.English).status as EnglishStatus.Solving).id == 9) {
                        _uiState.update { cs ->
                            cs.copy(pageState = (cs.pageState as Pages.English).copy(status = EnglishStatus.Waiting))
                        }
                    } else {
                        _uiState.update { cs ->
                            cs.copy(pageState = (cs.pageState as Pages.English).copy(status = (cs.pageState.status as EnglishStatus.Solving).copy(
                                input = "",
                                id = cs.pageState.status.id + 1,
                                score = EnglishScore.Waiting
                            )))
                        }
                    }
                }

            } else if(k == "backspace") {
                if(uiState.value.sendText) {
                    _uiState.update { cs ->
                        cs.copy(sendText = false)
                    }
                } else if(isWritingEnglish()) backspackEnglishText()
            }
        }
    }
    fun enterEnglishPage() {
        _uiState.update { cs ->
            cs.copy(pageState = Pages.English(EnglishStatus.Waiting))
        }
    }
    fun pushStartButton() {
        if(uiState.value.isLoading) return
        var response: Problems = Problems(mutableListOf())
        scope.launch {
            _uiState.update { cs ->
                cs.copy(isLoading = true)
            }
            try {
                val result: String = client.post("http://localhost:8081/a") {
                    contentType(ContentType.Application.Json)
                    setBody(Json.encodeToString(uiState.value.myData))
                }.body()
                response = Json.decodeFromString(result)
            } catch(e: Exception) {
                response = Problems(mutableListOf(Problem(0, e.toString(), "hello")))
            } finally {
                _uiState.update { cs ->
                    cs.copy(
                        isLoading = false,
                        pageState = Pages.English(EnglishStatus.Solving(
                            id = 0,
                            problems = response,
                            input = "",
                            score = EnglishScore.Waiting
                        )),
                    )
                }
            }
        }

    }
    fun pushSendButton() {
        if(uiState.value.isLoading) return
        var response: EnglishScore.Score = EnglishScore.Score("", Evaluation(0, ""), Evaluation(0, ""), "")
        val state = (uiState.value.pageState as Pages.English).status as EnglishStatus.Solving
        if(state.input == "") return
        scope.launch {
            _uiState.update { cs ->
                cs.copy(isLoading = true)
            }
            try {

                val result: String = client.post("http://localhost:8081/b") {
                    contentType(ContentType.Application.Json)
                    setBody(Json.encodeToString(MyAnswer(
                        pj = state.problems.exercises[state.id].sentence_ja,
                        bj = state.problems.exercises[state.id].background_ja,
                        ae = state.input
                    )))
                }.body()
                response = Json.decodeFromString(result)
            } catch(e: Exception) {
                response = EnglishScore.Score(e.toString(), Evaluation(0, ""), Evaluation(0, ""), "")
            } finally {
                _uiState.update { cs ->
                    cs.copy(
                        isLoading = false,
                        pageState = ((cs.pageState as Pages.English).copy(status = (cs.pageState.status as EnglishStatus.Solving).copy(score = response)))
                    )
                }
            }
        }

    }
    fun oauth() {
        scope.launch {
            window.location.href = "http://localhost:8081/oidc/google/login"
        }
    }


    private fun isWritingEnglish(): Boolean {
        val state = uiState.value.pageState
        return (state is Pages.English && state.status is EnglishStatus.Solving && state.status.score is EnglishScore.Waiting)
    }
    private fun isNextProblemsEnglish(): Boolean {
        val state = uiState.value.pageState
        return (state is Pages.English && state.status is EnglishStatus.Solving && state.status.score is EnglishScore.Score)
    }
    private fun addEnglishText(c: Char) {
        _uiState.update { cs ->
            cs.copy(pageState = (cs.pageState as Pages.English).copy(status = (cs.pageState.status as EnglishStatus.Solving).copy(input = cs.pageState.status.input + c)))
        }
    }
    private fun backspackEnglishText() {
        _uiState.update { cs ->
            cs.copy(pageState = (cs.pageState as Pages.English).copy(status = (cs.pageState.status as EnglishStatus.Solving).copy(input = cs.pageState.status.input.substring(0, cs.pageState.status.input.length - 1))))
        }
    }

}

package com.example.site.pages.english

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.site.EnglishScore
import com.example.site.EnglishStatus
import com.example.site.Evaluation
import com.example.site.Pages
import com.example.site.Problem
import com.example.site.Problems
import com.example.site.components.layouts.LocalAppViewModel
import com.example.site.components.layouts.PageLayout
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.heightIn
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.overlay.Overlay
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Page
@Composable
fun AboutPage() {
    PageLayout {
        val viewModel = LocalAppViewModel.current
        val uiState by viewModel.uiState.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.enterEnglishPage()
        }
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.darkred),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                if(uiState.pageState is Pages.English) {
                    val state = uiState.pageState as Pages.English
                    if (state.status is EnglishStatus.Waiting) {
                        Button(
                            onClick = { viewModel.oauth() }
                        ) {
                            Text("テスト")
                        }
                        Button(
                            onClick = { viewModel.pushStartButton() }
                        ) { Text(if (!uiState.isLoading) "送信" else "送信中...") }
                    } else if (state.status is EnglishStatus.Solving) {
                        val state2 = state.status
                        H2 { Text("シチュエーション") }
                        P { Text((state2.problems.exercises[state2.id].background_ja)) }
                        H2 { Text("表現") }
                        P { Text((state2.problems.exercises[state2.id].sentence_ja)) }
                        H2 { Text("入力") }
                        P { Text(state2.input + "|") }
                        if (state2.score is EnglishScore.Score) {
                            val state3 = state2.score
                            H2 { Text("評価") }
                            H3 { Text("模範解答") }
                            P { Text(state3.model_answer) }
                            H3 { Text("文法的評価") }
                            P { Text(state3.english_correctness.score.toString() + "点 : " + state3.english_correctness.comment) }
                            H3 { Text("ニュアンス的評価") }
                            P { Text(state3.native_naturalness.score.toString() + "点 : " + state3.native_naturalness.comment) }
                            H3 { Text("総合評価") }
                            P { Text(state3.native_comment_ja) }
                        }
                    }
                }
            }
        }
        if(uiState.sendText) {
            Overlay() {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        modifier = Modifier.padding(2.cssRem)
                            .width(300.px)
                            .borderRadius(8.px)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            P { Text("送信します") }
                            P { Text(((uiState.pageState as Pages.English).status as EnglishStatus.Solving).input) }
                        }
                    }
                }
            }
        }
    }
}

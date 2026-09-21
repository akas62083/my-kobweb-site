package com.example.site.pages.english

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.site.components.layouts.PageLayout
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.heightIn
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Button
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
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

private val client = HttpClient()
@Page
@Composable
fun AboutPage() {
    var state: PageState by remember { mutableStateOf(PageState()) }
    var response: ExercisesResponse? by remember { mutableStateOf(null) }
    val scope = rememberCoroutineScope()

    val mydata = MyData(3, 3, 3, 3, 3, 3, 3) // 仮データ

    PageLayout {
        Row(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.darkred),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box {}
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                if(state.list == null) {
                    Button(
                        onClick = {
                            scope.launch {
                                try {
                                    println("hello world")
                                    val result: String = client.post("http://localhost:8081/a") {
                                        contentType(ContentType.Application.Json)
                                        setBody(Json.encodeToString(mydata))
                                    }.body()
                                    response = Json.decodeFromString(result)
                                } catch(e: Exception) {
                                    response = ExercisesResponse(mutableListOf(MyTextStyle(0, e.toString(), "hello")))
                                } finally {
                                    state = PageState(0, true, response!!.exercises!!.toMutableList())
                                }
                            }
                        }
                    ) {
                        Text("送信")
                    }
                } else {
                    H2 { Text("シチュエーション")}
                    Text(state.list!!.get(state.id).background_ja)
                    H2 { Text("表現")}
                    Text(state.list!!.get(state.id).sentence_ja)
                    Button(onClick = {state = state.copy(id = state.id + 1)}) {Text("Next")}
                }
            }
            Box {}
        }
    }
}

data class PageState(
    val id: Int = 0,
    val writing: Boolean = false,
    val list: MutableList<MyTextStyle>? = null
)

@Serializable
data class MyTextStyle (
    val id: Int,
    val sentence_ja: String,
    val background_ja: String
)

@Serializable
data class ExercisesResponse(
    val exercises: List<MyTextStyle>
)

@Serializable
data class MyData(
    val vo: Int,
    val ex: Int,
    val gr: Int,
    val mo: Int,
    val pr: Int,
    val ss: Int,
    val oa: Int
)

package com.example.site.pages.me

import androidx.compose.runtime.Composable
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
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Page
@Composable
fun AboutPage() {
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
                H1 {
                    Text("About me")
                }
                Box(modifier = Modifier.fillMaxSize().backgroundColor(Color.aliceblue)) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(modifier = Modifier.fillMaxSize()) {
                            Box(modifier = Modifier.width(100.percent)) {}
                            H2 { Text("aiueo") }
                            Box(modifier = Modifier.width(100.percent)) {}
                        }
                        P { Text("・競技プログラミング") }
                        P { Text("・Androidアプリ開発") }
                    }
                }
            }
            Box {}
        }
    }
}
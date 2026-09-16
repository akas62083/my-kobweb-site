package com.example.site.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.heightIn
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    Row(
        modifier = Modifier.fillMaxSize()
            .padding(left = 300.px, right = 300.px)
            .background(color = Color.lightgreen),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column {
            H1 {
                Text("akas62083のホームページ")
            }
            P {
                Text("kotlin + kobweb で作っているホームページです。")
            }
            Box(modifier = Modifier.heightIn(50.px)) {}
            H2 {
                A(href = "/me") { Text("About me") }
            }
            P {
                Text("初のホームページ作成に挑戦です。。。")
                Text("バイブコーディングを使わずにやっていきます。。。")
            }
            H2 {
                A(href = "/blog") { Text("(未定)") }
            }
            P {
                Text("テスト")
            }
        }
    }
}

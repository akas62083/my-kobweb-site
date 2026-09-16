package com.example.site.components.sections

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
import com.varabyte.kobweb.compose.ui.modifiers.height
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Text

@Composable
fun Header() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .height(50.px)
            .background(color = Color.indianred),
        verticalArrangement = Arrangement.Center,
    ) {
        Box {}
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            A(href = "/") { Text("Home") }
            A(href = "/blog") { Text("Blog") }
            A(href = "/me") { Text("About") }
        }
        Box {}
    }
}

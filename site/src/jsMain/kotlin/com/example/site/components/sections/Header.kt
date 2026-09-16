package com.example.site.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Text

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        A(href = "/") { Text("Home") }
        Spacer()
        A(href = "/blog") { Text("Blog") }
        Spacer()
        A(href = "/about") { Text("About") }
    }
}

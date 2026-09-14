package com.example.site.components.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Text

@Composable
fun Header() {
    A(href = "/") { Text("Home") }
    A(href = "/blog") { Text("Blog") }
    A(href = "/about") { Text("About") }
}

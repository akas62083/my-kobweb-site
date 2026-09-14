package com.example.site.pages.blog

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun BlogPage() {
    H1 { Text("Blog") }
    P { Text("記事一覧") }
}

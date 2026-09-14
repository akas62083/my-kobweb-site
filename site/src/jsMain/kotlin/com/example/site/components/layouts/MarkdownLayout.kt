package com.example.site.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.layout.Layout

@Composable
@Layout
fun MarkdownLayout(content: @Composable () -> Unit) {
    PageLayout(content)
}

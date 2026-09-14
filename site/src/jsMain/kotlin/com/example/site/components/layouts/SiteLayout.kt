package com.example.site.components.layouts

import androidx.compose.runtime.Composable
import com.example.site.components.sections.Footer
import com.example.site.components.sections.Header
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.css.px

@Composable
@Layout
fun SiteLayout(content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(24.px)) {
        Header()
        content()
        Footer()
    }
}

package com.example.site.components.layouts

import androidx.compose.runtime.Composable
import com.example.site.components.sections.Footer as SiteFooter
import com.example.site.components.sections.Header as SiteHeader
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.Footer
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Main

@Composable
@Layout
fun PageLayout(content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header {
            SiteHeader()
        }
        Main {
            content()
        }
        Footer {
            SiteFooter()
        }
    }
}

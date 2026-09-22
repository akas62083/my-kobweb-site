package com.example.site.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import com.example.site.ViewModel
import com.example.site.keyboards.GlobalKeyboardHandler
import com.example.site.components.sections.Footer as SiteFooter
import com.example.site.components.sections.Header as SiteHeader
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.overlay.Overlay
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

val LocalAppViewModel =
    staticCompositionLocalOf<ViewModel> {
        error("AppViewModel not initialized")
    }
@Composable
@Layout
fun PageLayout(content: @Composable () -> Unit) {
    val viewModel = remember { ViewModel() }
    val uiState by viewModel.uiState.collectAsState()
    GlobalKeyboardHandler { key ->
        viewModel.handleKey(key)
    }
    CompositionLocalProvider(
        LocalAppViewModel provides viewModel,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SiteHeader()
            content()
            SiteFooter()
        }
        if(uiState.isLoading) {
            Overlay() {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        modifier = Modifier.padding(2.cssRem)
                            .width(300.px)
                            .borderRadius(8.px)
                            .onClick { it.stopPropagation() }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Now Loading ...")
                        }
                    }
                }
            }
        }
    }
}

package com.example.site.keyboards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import kotlinx.browser.document
import org.w3c.dom.AddEventListenerOptions
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.document

@Composable
fun GlobalKeyboardHandler(
    onKey: (String) -> Unit 
) { 
    DisposableEffect(Unit) {
        val handler: (Event) -> Unit = { event ->
            val keyboardEvent = event as KeyboardEvent
            onKey(event.key) 
        } 
        document.addEventListener("keydown", handler)
        onDispose { document.removeEventListener("keydown", handler) }
    } 
}

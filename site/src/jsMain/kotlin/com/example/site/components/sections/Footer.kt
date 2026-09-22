package com.example.site.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.css.Color
import com.varabyte.kobweb.compose.ui.modifiers.background

@Composable
fun Footer() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = Color.indianred),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text("© 2026 My Blog")
    }
}

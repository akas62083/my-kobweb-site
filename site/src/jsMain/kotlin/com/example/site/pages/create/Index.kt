package com.example.site.pages.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.site.components.layouts.LocalAppViewModel
import com.example.site.components.layouts.PageLayout
import com.example.site.model.LoginStatus
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun CreatePage() {
    PageLayout {
        val viewModel = LocalAppViewModel.current
        val uiState by viewModel.uiState.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.enterCreatePage()
        }
        when(uiState.loginStatus) {
            is LoginStatus.Registration -> {
                Text("Registration...")
            }
            is LoginStatus.Success -> {
                Text("hello ${(uiState.loginStatus as LoginStatus.Success).userId}!")
            }
            is LoginStatus.UnLogin -> {
                Text("unlogin")
            }
        }
    }
}

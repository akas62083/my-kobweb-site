package com.example.site.pages.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.site.components.layouts.LocalAppViewModel
import com.example.site.components.layouts.PageLayout
import com.varabyte.kobweb.core.Page

@Page
@Composable
fun AccountPage() {
    PageLayout {
        val viewModel = LocalAppViewModel.current
        val uiState by viewModel.uiState.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.enterAccountPage()
        }
    }
}

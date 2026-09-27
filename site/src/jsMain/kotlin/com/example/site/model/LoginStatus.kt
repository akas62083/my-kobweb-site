package com.example.site.model

import kotlinx.serialization.Serializable

sealed class LoginStatus {
    @Serializable
    data object UnLogin : LoginStatus()
    @Serializable
    data class Success(val userId: String): LoginStatus()
    @Serializable
    data class Registration(val email: String): LoginStatus()
}
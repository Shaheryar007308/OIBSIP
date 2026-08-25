package com.example.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
@Serializable
    object Home : Routes()

    @Serializable
    object History : Routes()

}
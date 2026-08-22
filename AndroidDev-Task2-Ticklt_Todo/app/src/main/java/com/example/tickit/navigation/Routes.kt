package com.example.tickit.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object LOGIN : Routes()
    @Serializable
    object SIGNING : Routes()

    @Serializable
    object MAIN : Routes()
}
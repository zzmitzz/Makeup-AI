package com.iec.makeup.core.model

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int = 0,
    val name: String = "Default",
    val username: String = "Default",
    val email: String = "Default",
    val phone: String = "Default",
    val avatar: String = "Default",
    // Other fields
)
package com.iec.makeup.core.model.ui

import kotlinx.serialization.Serializable

@Serializable
data class MakeUpLayout(
    val id: String,
    val title: String,
    val image: String
)


fun fakeMakeUpLayoutData(): List<MakeUpLayout> {
    return listOf(
        MakeUpLayout(
            id = "1",
            title = "Portrait",
            image = "https://images.unsplash.com/photo-153452874"
        ),
        MakeUpLayout(
            id = "2",
            title = "Landscape",
            image = "https://images.unsplash.com/photo-153452874"
        ),
        MakeUpLayout(
            id = "3",
            title = "Wedding",
            image = "https://images.unsplash.com/photo-153452874"
        ),
        MakeUpLayout(
            id = "4",
            title = "Wedding",
            image = "https://images.unsplash.com/photo-153452874"
        ),
        MakeUpLayout(
            id = "5",
            title = "Wedding",
            image = "https://images.unsplash.com/photo-153452874"
        ),
        MakeUpLayout(
            id = "6",
            title = "Wedding",
            image = "https://images.unsplash.com/photo-153452874"
        )
    )
}
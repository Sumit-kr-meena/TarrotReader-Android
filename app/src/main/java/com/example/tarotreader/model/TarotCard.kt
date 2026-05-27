package com.example.tarotreader.model

import androidx.annotation.DrawableRes

data class TarotCard(

    val name: String,

    @DrawableRes
    val imageRes: Int,

    val interpretation: String
)
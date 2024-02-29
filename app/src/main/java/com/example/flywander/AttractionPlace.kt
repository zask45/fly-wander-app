package com.example.flywander

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttractionPlace(
    val name: String,
    val location: String,
    val description: String,
    val photo: Int
) : Parcelable

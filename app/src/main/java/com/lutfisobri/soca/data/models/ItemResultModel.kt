package com.lutfisobri.soca.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemResultModel(val icon: Int, val title: String, var isFavorite: Boolean = false): Parcelable
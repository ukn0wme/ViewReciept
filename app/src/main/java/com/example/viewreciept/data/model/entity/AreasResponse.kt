package com.example.viewreciept.data.model.entity

import com.google.gson.annotations.SerializedName

data class AreasResponse(@SerializedName("meals") val areas: List<Area>)
package com.example.taskhumanassignment.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Favorite(
    @Expose @SerializedName(SkillConstants.PROVIDER_ID) var providerid: String? = null,
    @Expose @SerializedName(SkillConstants.FAVORITE_BY) var favoriteBy: String? = null,
)

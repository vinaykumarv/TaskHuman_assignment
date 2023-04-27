package com.example.taskhumanassignment.domain.models.networkresponse

import com.example.taskhumanassignment.domain.models.Favorite
import com.example.taskhumanassignment.domain.models.SkillConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddResultSuccess(
    @Expose @SerializedName(SkillConstants.SUCCESS) var success: Boolean = false,
    @Expose @SerializedName(SkillConstants.FAVORITE) var favorite: Favorite? = null,
    @Expose @SerializedName(SkillConstants.MESSAGE) var message: String? = null,
    @Expose @SerializedName(SkillConstants.SHOW_FAVORITE_TOAST) var showFavoriteToast: Boolean = false

)

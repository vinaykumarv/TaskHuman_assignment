package com.example.taskhumanassignment.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorReason(
    @Expose @SerializedName(SkillConstants.REASON) var reason: String? = null
)

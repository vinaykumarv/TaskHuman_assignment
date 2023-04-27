package com.example.taskhumanassignment.domain.models.networkresponse

import com.example.taskhumanassignment.domain.models.ErrorReason
import com.example.taskhumanassignment.domain.models.SkillConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddResultFailure(
    @Expose @SerializedName(SkillConstants.ERRORS) var errors: List<ErrorReason>,
    @Expose @SerializedName(SkillConstants.CODE) var code: Int
    )

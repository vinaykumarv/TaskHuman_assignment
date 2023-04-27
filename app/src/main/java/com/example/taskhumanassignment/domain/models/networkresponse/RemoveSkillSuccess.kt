package com.example.taskhumanassignment.domain.models.networkresponse

import com.example.taskhumanassignment.domain.models.SkillConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoveSkillSuccess(
    @Expose
@SerializedName(SkillConstants.SUCCESS) var success: Boolean = false,
    @Expose
@SerializedName(SkillConstants.MESSAGE) var message: String? = null,
)
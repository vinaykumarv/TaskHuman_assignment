package com.example.taskhumanassignment.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Availability(
    @Expose @SerializedName(SkillConstants.STATUS) var status: Int? = null,
    @Expose @SerializedName(SkillConstants.COLOR) var color: String? = null,
    @Expose @SerializedName(SkillConstants.START_TIME) var startTime: Long? = null,
    @Expose @SerializedName(SkillConstants.END_TIME) var endTime: Long? = null
)

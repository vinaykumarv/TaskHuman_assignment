package com.example.taskhumanassignment.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProviderInfo(
    @Expose @SerializedName(SkillConstants.PROVIDER_ID) var providerId: Int? = null,
    @Expose @SerializedName(SkillConstants.START_TIME) var startTime: Long? = null,
    @Expose @SerializedName(SkillConstants.END_TIME) var endTime: Long? = null,
    @Expose @SerializedName(SkillConstants.PROFILE_IMAGE) var profileImage: String? = null,
)

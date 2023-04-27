package com.example.taskhumanassignment.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopicHeader(
    @Expose @SerializedName(SkillConstants.TITLE_NAME) var tileName: String? = null,
    @Expose @SerializedName(SkillConstants.TYPE) var type: String? = null,
    @Expose @SerializedName(SkillConstants.COLUMNS) var columns: Int? = null,
    @Expose @SerializedName(SkillConstants.COLOR) var color: String? = null,
    @Expose @SerializedName(SkillConstants.TOPIC_ICON) var topicIcon: String? = null,

    )

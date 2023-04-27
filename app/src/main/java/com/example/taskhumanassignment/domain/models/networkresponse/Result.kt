package com.example.taskhumanassignment.domain.models.networkresponse

import com.example.taskhumanassignment.domain.models.Skill
import com.example.taskhumanassignment.domain.models.SkillConstants
import com.example.taskhumanassignment.domain.models.TopicHeader
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @Expose @SerializedName(SkillConstants.SUCCESS) var success: Boolean = false,
    @Expose @SerializedName(SkillConstants.TOPIC_HEADER) var topicHeader: TopicHeader? = null,
    @Expose @SerializedName(SkillConstants.SKILLS) var skills: List<Skill>? = null
)

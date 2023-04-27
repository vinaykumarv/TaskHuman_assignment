package com.example.taskhumanassignment.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Skill(
    @Expose @SerializedName(SkillConstants.TITLE_NAME) var tileName: String? = null,
    @Expose @SerializedName(SkillConstants.DISPLAY_TITLE_NAME) var displayTileName: String? = null,
    @Expose @SerializedName(SkillConstants.TYPE) var type: String? = null,
    @Expose @SerializedName(SkillConstants.DICTIONARY_NAME) var dictionaryName: String? = null,
    @Expose @SerializedName(SkillConstants.TITLE_BACKGROUND) var tileBackground: String? = null,
    @Expose @SerializedName(SkillConstants.SKILL_ICON) var skillIcon: String? = null,
    @Expose @SerializedName(SkillConstants.AVAILABILITY) var availability: Availability? = null,
    @Expose @SerializedName(SkillConstants.MORE_PROVIDERS_AVAILABLE) var moreProvidersAvailable: Boolean = false,
    @Expose @SerializedName(SkillConstants.PROVIDER_INFO) var providerInfo: List<ProviderInfo>? = null,
    @Expose @SerializedName(SkillConstants.IS_FAVORITE) var isFavorite: Boolean = false,
    @Expose @SerializedName(SkillConstants.COLUMNS) var columns: Int? = null
)

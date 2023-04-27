package com.example.taskhumanassignment.domain.manager

import com.example.taskhumanassignment.domain.models.networkresponse.AddResultSuccess
import com.example.taskhumanassignment.domain.models.networkresponse.RemoveSkillSuccess
import com.example.taskhumanassignment.domain.models.networkresponse.Result
import com.example.taskhumanassignment.eventbus.UiEventManager

object NetworkManager : NetworkResult{

    private var uiEventManagerInstance = UiEventManager

    suspend fun getSkills() {
        NetworkRequestAgent.getSkills(this)
    }

    override fun onGetSkillsResult(isSuccessful: Boolean, result: Result?) {
        uiEventManagerInstance.postEvent(
            UIEvents.OnGetSkillsResult(isSuccessful, result)
        )
    }

    override fun onAddSkillResult(
        isSuccessful: Boolean,
        addResultSuccess: AddResultSuccess?,
        failureCode: Int?,
        reason: String?
    ) {
        TODO("Not yet implemented")
    }

    override fun onRemoveSkillResult(
        isSuccessful: Boolean,
        removeSkillResponse: RemoveSkillSuccess?,
        failureCode: Int?,
        reason: String?
    ) {
        TODO("Not yet implemented")
    }
}
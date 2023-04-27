package com.example.taskhumanassignment.domain.manager

import com.example.taskhumanassignment.domain.models.networkresponse.AddResultSuccess
import com.example.taskhumanassignment.domain.models.networkresponse.RemoveSkillSuccess
import com.example.taskhumanassignment.domain.models.networkresponse.Result

interface NetworkResult {

    fun onGetSkillsResult(isSuccessful: Boolean,
                          result: Result?)

    fun onAddSkillResult(isSuccessful: Boolean,
                         addResultSuccess: AddResultSuccess?,
                         failureCode: Int?,
                         reason: String?, )

    fun onRemoveSkillResult(isSuccessful: Boolean,
                            removeSkillResponse: RemoveSkillSuccess?,
                            failureCode: Int?, reason: String?)
}
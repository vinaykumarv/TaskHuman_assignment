package com.example.taskhumanassignment.domain.manager

import com.example.taskhumanassignment.domain.models.networkresponse.Result

/**
 * Common place for all the Ui Events data classes.
 * Basically these events are result of previously initiated APIs or indications of new ones,
 * e.g. get all the skills from the server asynchronously.
 */
interface UIEvents {

    /**
     * GetSkills API asynchronous response
     *
     * @param isSuccessful states if the request to the network is successful or not
     * @param result result object contains all the skills if the request is successful
     */
    data class OnGetSkillsResult(
        val isSuccessful: Boolean,
        val result: Result?
    ): UIEvents

}
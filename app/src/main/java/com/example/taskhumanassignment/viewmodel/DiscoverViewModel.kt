package com.example.taskhumanassignment.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.taskhumanassignment.domain.manager.NetworkManager
import com.example.taskhumanassignment.domain.manager.UIEvents
import com.example.taskhumanassignment.domain.models.Skill
import com.example.taskhumanassignment.eventbus.UiEventManager
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiscoverViewModel : ViewModel() {

    private var eventsDisposable: Disposable? = null

    private val _skills = MutableLiveData<List<Skill>?>()
    val skills: LiveData<List<Skill>?> = _skills

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    private val _showErrorMessage = MutableLiveData<Boolean>()
    val showErrorMessage : LiveData<Boolean> = _showErrorMessage

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        subscribeToUIEvents()
        Log.d(TAG, "DiscoverViewModel -> init()")
    }

    /**
     * Subscribe to UIEvents
     */
    private fun subscribeToUIEvents() {
        eventsDisposable = UiEventManager.observeEventOnMainThread<UIEvents>()
            .subscribe(
                { event ->
                    Log.i(TAG, "UiEventManager.observeEventOnMainThread event = $event")
                    when (event) {
                        is UIEvents.OnGetSkillsResult -> {
                            handleOnGetSkillResult(event)
                        }
                    }
                },
                { throwable ->  // onError
                    Log.e(TAG, "subscribeToUIEvents() onError()", throwable)
                }
            )
    }

    fun getSkillsFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
          NetworkManager.getSkills()
        }
    }

    private fun handleOnGetSkillResult(event: UIEvents.OnGetSkillsResult) {
        if(event.isSuccessful) {
            if(event.result?.skills != null) {
                CoroutineScope(Dispatchers.Main).launch {
                    _skills.postValue(event.result.skills!!)
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    _skills.postValue(null)
                    _errorMessage.postValue("Failed to download the skills from network")
                }
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                _skills.postValue(null)
                _errorMessage.postValue("Failed to download the skills from network")
            }
        }
    }

    private fun unsubscribeToUIEvents() {
        eventsDisposable?.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        unsubscribeToUIEvents()
    }

    companion object {
        private const val TAG = "DiscoverViewModel"
    }
}
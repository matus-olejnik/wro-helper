package com.devmo.wrohelper.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmo.wrohelper.db.DataRepository
import com.devmo.wrohelper.db.dm.IssueEntity
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DataRepository) : ViewModel() {

    private val _selectedIssue = MutableLiveData<IssueEntity>()
    val selectedIssue: LiveData<IssueEntity>
        get() = _selectedIssue

    private val _issueIds = MutableLiveData<List<String>>()
    val issueIds: LiveData<List<String>>
        get() = _issueIds

    fun fetchIssueIds() {
        viewModelScope.launch {
            _issueIds.value = repository.getIssuesIds()
        }
    }

    fun fetchIssue(id: String) {
        viewModelScope.launch {
            _selectedIssue.value = repository.getIssuesById(id)
        }
    }
}
package me.androidbox.todocompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.usecase.GetAllTaskUseCase
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase
): ViewModel() {

    private val listOfTaskMutableStateFlow = MutableStateFlow<List<TodoTaskEntity>>(emptyList())
    val listOfTaskStateFlow = listOfTaskMutableStateFlow.asStateFlow()
    
    fun getAllTasks() {
        viewModelScope.launch {
            getAllTaskUseCase.getAllTask().collect { listOfTodoTask ->
                listOfTaskMutableStateFlow.value = listOfTodoTask
            }
        }
    }

}
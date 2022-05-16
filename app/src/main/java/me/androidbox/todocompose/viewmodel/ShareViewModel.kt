package me.androidbox.todocompose.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.androidbox.domain.repository.AllTaskRepository
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val allTaskRepository: AllTaskRepository
): ViewModel() {

/*
    private val listOfTaskMutableStateFlow = MutableStateFlow<List<TodoTaskEntity>>(emptyList())
    val listOfTaskStateFlow = listOfTaskMutableStateFlow.asStateFlow()

    fun getAllTasks() {
        viewModelScope.launch {
            getAllTaskUseCase.getAllTask().collect { listOfTodoTask ->
                listOfTaskMutableStateFlow.value = listOfTodoTask
            }
        }
    }
*/

}
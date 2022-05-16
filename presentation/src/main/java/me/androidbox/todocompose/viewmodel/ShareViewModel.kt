package me.androidbox.todocompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val listOfTaskMutableStateFlow = MutableStateFlow<List<TodoTaskEntity>>(emptyList())
    val listOfTaskStateFlow = listOfTaskMutableStateFlow.asStateFlow()

    fun getAllTasks() {
        viewModelScope.launch {
            taskRepository.fetchAllTask().collect { listOfTodoTask ->
                listOfTaskMutableStateFlow.value = listOfTodoTask
            }
        }
    }
}
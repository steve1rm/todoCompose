package me.androidbox.todocompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.todocompose.util.SearchAppBarState
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val searchAppBarStateMutableState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchAppBarState: State<SearchAppBarState> = searchAppBarStateMutableState

    val listOfTaskMutableStateFlow = MutableStateFlow<List<TodoTaskEntity>>(emptyList())
    val listOfTaskStateFlow = listOfTaskMutableStateFlow.asStateFlow()


    private val searchTextMutableState = mutableStateOf("")
    val searchTextState = searchTextMutableState

    fun getAllTasks() {
        viewModelScope.launch {
            taskRepository.fetchAllTask().collect { listOfTodoTask ->
                listOfTaskMutableStateFlow.value = listOfTodoTask
            }
        }
    }

    fun updateSearchAppBarState(searchAppBarState: SearchAppBarState) {
        searchAppBarStateMutableState.value = searchAppBarState
    }

    fun updateSearchText(searchText: String) {
        searchTextMutableState.value = searchText
    }
}
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
import me.androidbox.todocompose.util.RequestState
import me.androidbox.todocompose.util.SearchAppBarState
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val searchAppBarStateMutableState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchAppBarState: State<SearchAppBarState> = searchAppBarStateMutableState

    private val searchTextMutableState = mutableStateOf("")
    val searchTextState = searchTextMutableState

    private val listOfTaskMutableStateFlow = MutableStateFlow<RequestState<List<TodoTaskEntity>>>(RequestState.Idle)
    val listOfTaskStateFlow = listOfTaskMutableStateFlow.asStateFlow()

    fun getAllTasks() {
        listOfTaskMutableStateFlow.value = RequestState.Idle

        try {
            viewModelScope.launch {
                listOfTaskMutableStateFlow.value = RequestState.Loading

                taskRepository.fetchAllTask().collect { listOfTodoTask ->
                    listOfTaskMutableStateFlow.value = RequestState.Success(listOfTodoTask)
                }
            }
        }
        catch (exception: Exception) {
            listOfTaskMutableStateFlow.value = RequestState.Failure(exception)
        }
    }

    fun updateSearchAppBarState(searchAppBarState: SearchAppBarState) {
        searchAppBarStateMutableState.value = searchAppBarState
    }

    fun updateSearchText(searchText: String) {
        searchTextMutableState.value = searchText
    }
}
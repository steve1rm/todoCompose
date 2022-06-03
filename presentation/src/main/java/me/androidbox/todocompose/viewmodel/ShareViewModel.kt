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
import me.androidbox.data.model.Priority
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.todocompose.Constant.MAX_TITLE_LENGTH
import me.androidbox.todocompose.component.PriorityItem
import me.androidbox.todocompose.util.RequestState
import me.androidbox.todocompose.util.SearchAppBarState
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    val id = mutableStateOf(0)
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val priority = mutableStateOf(Priority.NONE)

    private val searchAppBarStateMutableState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchAppBarState: State<SearchAppBarState> = searchAppBarStateMutableState

    private val searchTextMutableState = mutableStateOf("")
    val searchTextState = searchTextMutableState

    private val listOfTaskMutableStateFlow = MutableStateFlow<RequestState<List<TodoTaskEntity>>>(RequestState.Idle)
    val listOfTaskStateFlow = listOfTaskMutableStateFlow.asStateFlow()

    private val selectedTaskMutableStateFlow = MutableStateFlow<TodoTaskEntity?>(null)
    val selectedTaskStateFlow = selectedTaskMutableStateFlow.asStateFlow()

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

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            taskRepository.fetchSelectedTask(taskId).collect { todoTaskEntity ->
                selectedTaskMutableStateFlow.value = todoTaskEntity
            }
        }
    }

    fun updateSearchAppBarState(searchAppBarState: SearchAppBarState) {
        searchAppBarStateMutableState.value = searchAppBarState
    }

    fun updateSearchText(searchText: String) {
        searchTextMutableState.value = searchText
    }

    fun updateSelectedTask(todoTaskEntity: TodoTaskEntity?) {
        if(todoTaskEntity != null) {
            id.value = todoTaskEntity.id
            title.value = todoTaskEntity.title
            description.value = todoTaskEntity.description
            priority.value = Priority.MEDIUM
        }
        else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.NONE
        }
    }

    fun updateTitle(newTitle: String) {
        if(newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }
}
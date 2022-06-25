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
import me.androidbox.domain.usecase.*
import me.androidbox.todocompose.Constant.MAX_TITLE_LENGTH
import me.androidbox.todocompose.mapper.DomainToPresentationMapper
import me.androidbox.todocompose.mapper.PresentationToDomainMapper
import me.androidbox.todocompose.model.Priority
import me.androidbox.todocompose.model.TodoTask
import me.androidbox.todocompose.util.Action
import me.androidbox.todocompose.util.RequestState
import me.androidbox.todocompose.util.SearchAppBarState
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val fetchAllTaskUseCase: FetchAllTaskUseCase,
    private val fetchSelectedTaskUseCase: FetchSelectedTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val deleteAllTaskUseCase: DeleteAllTaskUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val domainToPresentationMapper: DomainToPresentationMapper<@JvmSuppressWildcards TodoTaskEntity, @JvmSuppressWildcards TodoTask>,
    private val presentationToDomainMapper: PresentationToDomainMapper<@JvmSuppressWildcards TodoTask, @JvmSuppressWildcards TodoTaskEntity>
): ViewModel() {

    var actionMutableState = mutableStateOf(Action.NO_ACTION)
        private set

    var id = mutableStateOf(0)
        private set

    var title = mutableStateOf("")
        private set

    var description = mutableStateOf("")
        private set

    var priority = mutableStateOf(Priority.NONE)
        private set

    private val searchAppBarStateMutableState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchAppBarState: State<SearchAppBarState> = searchAppBarStateMutableState

    private val searchTextMutableState = mutableStateOf("")
    val searchTextState = searchTextMutableState

    private val listOfTaskMutableStateFlow = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val listOfTaskStateFlow = listOfTaskMutableStateFlow.asStateFlow()

    private val selectedTaskMutableStateFlow = MutableStateFlow<TodoTask?>(null)
    val selectedTaskStateFlow = selectedTaskMutableStateFlow.asStateFlow()

    private val _searchListOfTodoTaskMutableStateFlow = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val searchTaskStateFlow = _searchListOfTodoTaskMutableStateFlow.asStateFlow()

    fun searchDatabase(searchQuery: String) {
        _searchListOfTodoTaskMutableStateFlow.value = RequestState.Loading

        try {
            viewModelScope.launch {
                searchDatabaseUseCase.execute("%$searchQuery%").collect { listOfTodoTaskEntity ->
                    val listOfTodoTask = listOfTodoTaskEntity.map { todoTaskEntity ->
                        domainToPresentationMapper.map(todoTaskEntity)
                    }

                    _searchListOfTodoTaskMutableStateFlow.value = RequestState.Success(listOfTodoTask)
                }
            }
        }
        catch (exception: Exception) {
            _searchListOfTodoTaskMutableStateFlow.value = RequestState.Failure(exception)
        }

        searchAppBarStateMutableState.value = SearchAppBarState.TRIGGERED
    }

    fun getAllTasks() {
        listOfTaskMutableStateFlow.value = RequestState.Idle

        try {
            viewModelScope.launch {
                listOfTaskMutableStateFlow.value = RequestState.Loading

                fetchAllTaskUseCase.execute().collect { listOfTodoTaskEntity ->
                    val listOfTodoTask = listOfTodoTaskEntity.map { todoTaskEntity ->
                        domainToPresentationMapper.map(todoTaskEntity)
                    }

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
            fetchSelectedTaskUseCase.execute(taskId).collect { todoTaskEntity ->
                if(todoTaskEntity == null) {
                    selectedTaskMutableStateFlow.value = null
                }
                else {
                    val todoTask = domainToPresentationMapper.map(todoTaskEntity)
                    selectedTaskMutableStateFlow.value = todoTask
                }
            }
        }
    }

    fun updateSearchAppBarState(searchAppBarState: SearchAppBarState) {
        searchAppBarStateMutableState.value = searchAppBarState
    }

    fun updateSearchText(searchText: String) {
        searchTextMutableState.value = searchText
    }

    private fun addTask() {
        viewModelScope.launch {
            val todoTask = TodoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )

            addTaskUseCase.execute(presentationToDomainMapper.map(todoTask))
        }

        searchAppBarStateMutableState.value = SearchAppBarState.CLOSED
    }

    private fun updateTask() {
        viewModelScope.launch {
            val todoTask = TodoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )

            updateTaskUseCase.execute(presentationToDomainMapper.map(todoTask))
        }
    }

    private fun deleteTask() {
        viewModelScope.launch {
            val todoTask = TodoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )

            deleteTaskUseCase.execute(
                todoTaskEntity = presentationToDomainMapper.map(todoTask))
        }
    }

    private fun deleteAllTask() {
        viewModelScope.launch {
            deleteAllTaskUseCase.execute()
        }
    }

    fun handleDatabaseAction(action: Action) {
        when(action) {
            Action.ADD -> {
                addTask()
            }
            Action.UPDATE -> {
                updateTask()
            }
            Action.DELETE -> {
                deleteTask()
            }
            Action.DELETE_ALL -> {
                deleteAllTask()
            }
            Action.UNDO -> {
                addTask()
            }
            else -> {}
        }

        actionMutableState.value = Action.NO_ACTION
    }

    fun updateSelectedTask(todoTask: TodoTask?) {
        if(todoTask != null) {
            id.value = todoTask.id
            title.value = todoTask.title
            description.value = todoTask.description
            priority.value = todoTask.priority
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

    fun hasValidField(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}
package jp.gihyo.wdpress.todo.service

import jp.gihyo.wdpress.todo.dto.TaskDto
import jp.gihyo.wdpress.todo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoService {
    @Autowired
    lateinit var todoRepository: TodoRepository

    fun findAllTasks(): List<TaskDto> {
        val allTasks = todoRepository.findAll() as List<TaskDto>
        return allTasks.sortedWith(compareBy({it.deadLine}))
    }

    fun createTask(task: TaskDto) = todoRepository.create(task)

    fun findOneTask(id: Int) = todoRepository.find(id)

    fun updateTask(task: TaskDto) = todoRepository.update(task.id!!, task)

    fun deleteTask(id: Int) = todoRepository.delete(id)
}
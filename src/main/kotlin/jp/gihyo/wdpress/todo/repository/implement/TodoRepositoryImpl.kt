package jp.gihyo.wdpress.todo.repository.implement

import jp.gihyo.wdpress.todo.domain.Task
import jp.gihyo.wdpress.todo.dto.TaskDto
import jp.gihyo.wdpress.todo.repository.TodoRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class TodoRepositoryImpl: TodoRepository {
    override fun create(task: TaskDto): TaskDto {
        Task.insert(toRow(task))
        return task
    }
    override fun update(key: Int, task: TaskDto) {
        Task.update( { Task.id eq key } ) {
            it[subject] = task.subject
            it[deadLine] = task.deadLine
            it[hasDone] = task.hasDone
        }
    }
    override fun findAll(): Iterable<TaskDto> {
        return Task.selectAll().map { fromRow(it) }
    }
    override fun find(key: Int): TaskDto? {
        return if (Task.select { Task.id eq key }.count() == 1) {
            fromRow(Task.select { Task.id eq key }.first())
        } else {
            null
        }
    }
    override fun deleteAll(): Int {
        return Task.deleteAll()
    }
    override fun delete(key: Int): Int {
        return Task.deleteWhere { Task.id eq key }
    }

    private fun toRow(t: TaskDto): Task.(UpdateBuilder<*>) -> Unit = {
        it[subject] = t.subject
        it[deadLine] = t.deadLine
        it[hasDone] = t.hasDone
    }

    private fun fromRow(r: ResultRow) =
            TaskDto(r[Task.id],
                    r[Task.subject],
                    r[Task.deadLine],
                    r[Task.hasDone])
}
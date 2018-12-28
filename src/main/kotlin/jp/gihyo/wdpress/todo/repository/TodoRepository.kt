package jp.gihyo.wdpress.todo.repository

import jp.gihyo.wdpress.todo.dto.TaskDto

interface TodoRepository: CrudRepository<TaskDto, Int>
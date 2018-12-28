package jp.gihyo.wdpress.todo.dto

import org.joda.time.DateTime

data class TaskDto (
        val id: Int?,
        val subject: String,
        val deadLine: DateTime,
        val hasDone: Boolean
)

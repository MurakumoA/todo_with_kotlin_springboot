package jp.gihyo.wdpress.todo.form

import org.joda.time.DateTime
import org.springframework.format.annotation.DateTimeFormat

data class TaskForm(val subject: String,
                    @DateTimeFormat(pattern="yyyy-MM-dd")
                    val deadLine: DateTime,
                    val hasDone: Boolean=false,
                    val newTask: Boolean=false)

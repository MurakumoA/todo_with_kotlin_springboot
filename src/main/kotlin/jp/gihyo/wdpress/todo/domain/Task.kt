package jp.gihyo.wdpress.todo.domain

import org.jetbrains.exposed.sql.Table

object Task : Table(name="task") {
    val id = integer("id").primaryKey().autoIncrement()
    val subject = varchar("subject", 100)
    val deadLine = datetime("dead-line")
    val hasDone = bool("done")
}
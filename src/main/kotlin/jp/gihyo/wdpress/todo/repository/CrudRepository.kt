package jp.gihyo.wdpress.todo.repository

interface CrudRepository<T, K> {
    fun create(task: T): T
    fun update(key: K, task: T)
    fun findAll(): Iterable<T>
    fun find(key: K): T?
    fun deleteAll(): Int
    fun delete(key: K): Int
}
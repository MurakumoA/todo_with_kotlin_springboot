package jp.gihyo.wdpress.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class TodoApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            runApplication<TodoApplication>(*args)
        }
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    open fun dataSource() = DataSourceBuilder.create().build()
}
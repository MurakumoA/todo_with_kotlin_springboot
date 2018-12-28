package jp.gihyo.wdpress.todo.web

import jp.gihyo.wdpress.todo.dto.TaskDto
import jp.gihyo.wdpress.todo.form.TaskForm
import jp.gihyo.wdpress.todo.service.TodoService
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
class TodoWebController {
    companion object {
        private const val TASKS = "tasks"

        private const val REDIRECT_TO = "redirect:/$TASKS"
    }

    @Autowired
    lateinit var todoService:TodoService

    // タスクを全件取得
    @GetMapping("/tasks")
    fun readAllTasks(): ModelAndView {
        val form = createInitialForm()
        val modelAndView = toTaskPage()
        modelAndView.addObject("form", form)
        val tasks = todoService.findAllTasks()
        modelAndView.addObject(TASKS, tasks)
        return modelAndView
    }

    private fun toTaskPage(): ModelAndView {
        return ModelAndView(TASKS)
    }

    private fun createInitialForm(): TaskForm {
        val formSubject = ""
        val formDeadLine = DateTime.now()
        val isNewTask = true
        val hasDone = false
        return TaskForm(
                formSubject,
                formDeadLine,
                hasDone,
                isNewTask
        )

    }

    // タスクを１件取得
    @PostMapping("/tasks")
    fun createOneTask(
        @ModelAttribute form: TaskForm): ModelAndView {
        createTaskFromForm(form)
        return ModelAndView(REDIRECT_TO)
    }

    private fun createTaskFromForm(form: TaskForm) {
        val subject = form.subject
        val deadLine = form.deadLine
        val hasDone = form.hasDone
        val task = TaskDto(null, subject, deadLine, hasDone)
        todoService.createTask(task)
    }

    // タスクを１件取得
    @GetMapping("/tasks/{id}")
    fun readOneTask(
            @PathVariable id: Int): ModelAndView {
        val form = readTaskFromId(id)
        form ?: return ModelAndView(REDIRECT_TO)
        val modelAndView = toTaskPage()
        modelAndView.addObject("taskId", id)
        modelAndView.addObject("form", form)
        val tasks = todoService.findAllTasks()
        modelAndView.addObject(TASKS, tasks)
        return modelAndView
    }

    private fun readTaskFromId(id: Int): TaskForm? {
        val task = todoService.findOneTask(id)
        task ?: return null
        val formSubject = task.subject
        val formDeadLine = task.deadLine
        val hasDone = task.hasDone
        val isNewTask = false;
        return TaskForm(formSubject, formDeadLine, hasDone, isNewTask)
    }

    // タスクを１件更新
    @PutMapping("/tasks/{id}")
    fun updateOneTask(
            @PathVariable id: Int,
            @ModelAttribute form: TaskForm): ModelAndView {
        updateTask(id, form)
        return ModelAndView(REDIRECT_TO)
    }

    private fun updateTask(
        id: Int,
        form: TaskForm
    ) {
        val subject = form.subject
        val deadLine = form.deadLine
        val hasDone = form.hasDone
        val task = TaskDto(id, subject, deadLine, hasDone)
        todoService.updateTask(task);
    }

    // タスクを１件削除
    @DeleteMapping("/tasks/{id}")
    fun deleteOneTask(
            @PathVariable id: Int): ModelAndView {
        deleteTask(id)
        return ModelAndView(REDIRECT_TO)
    }

    private fun deleteTask(id: Int) {
        val task = todoService.findOneTask(id);
        if (task != null) {
            todoService.deleteTask(id)
        }
    }
}
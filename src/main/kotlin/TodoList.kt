import java.time.LocalDate

@Suppress("UNCHECKED_CAST")

/**
 * Contains a list of To-Do objects and logic for organizing/maintaining them.
 */
class TodoList(var name: String) : ProductivityList() {

    fun addTodo(name: String) {
        val todo = Todo(nextId, name)
        nextId++
        itemList.add(todo)
    }

    fun getTodosCompletedOn(day: LocalDate): List<Todo> {
        return itemList.filter {
            if (it is Todo)
                it.dateCompleted == day
            else
                false
        } as List<Todo>
    }

    fun markTodo(id: Int, complete: Boolean) {
        val todoToComplete = itemList.find { it.id == id } as Todo? ?: throw Error()
        if (complete)
            todoToComplete.complete()
        else
            todoToComplete.incomplete()
    }
}

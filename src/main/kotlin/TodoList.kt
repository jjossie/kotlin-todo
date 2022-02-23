import java.util.Date

class TodoList(var name: String) {
    private var nextId = 1
    private var todos = ArrayList<Todo>()
    fun addTodo(name: String){
        val todo = Todo(nextId, "")
        nextId++
        todos.add(todo)
    }
    fun getTodosCompletedOn(day: Date) : List<Todo>{
        return todos.filter { it.dateCompleted == day }
    }
    fun markTodo(id: Int, complete: Boolean) {
        val todoToComplete = todos.find {it.id == id} ?: throw Error()
        if (complete)
            todoToComplete.complete()
        else
            todoToComplete.incomplete()
    }

    fun display(filter: TodoListFilter) {
        var output = ""
        todos
            .filter {
                when (filter) {
                    TodoListFilter.All -> true
                    TodoListFilter.Completed -> it.completed
                    TodoListFilter.Incomplete -> !it.completed
                }
            }
            .forEach {
            output += "$it\n"
        }
        if (output == "") {
            output = "No to-do items.\n"
        }
        print(output)
    }

}

enum class TodoListFilter {
    All, Completed, Incomplete
}
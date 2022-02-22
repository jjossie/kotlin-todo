import java.util.Date

class TodoList(var name: String) {
    val id: Int = 0
    var todos = ArrayList<Todo>()
    fun addTodo(todo: Todo){
        todos.add(todo)
    }
    fun getTodosCompletedOn(day: Date) : List<Todo>{
        return todos.filter { it.dateCompleted == day }
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
        print(output)
    }

}

enum class TodoListFilter {
    All, Completed, Incomplete
}
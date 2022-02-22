import java.util.*

open class Goal(var name: String, val type: CompletionConditionType) {

}

class TodoGoal(
    name: String,
    var todoList: TodoList,
    var completionTarget: Int,
    var completed: Int
) : Goal(name = name, type = CompletionConditionType.Todo) {

    private fun updateProgress() {
        completed = todoList.getTodosCompletedOn(Date()).size
    }

    fun isComplete(): Boolean {
        return completed == completionTarget
    }

}

enum class CompletionConditionType {
    Count, Time, Todo
}
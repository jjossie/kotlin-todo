import java.util.*

open class Goal(var name: String, val type: CompletionConditionType) {
    var accomplished: Boolean = false
    val id: Int = 0
    override fun toString(): String {
        return "$id) [${if (accomplished) "X" else " "}] $name "
    }
}

class TodoGoal(
    name: String,
    var todoList: TodoList,
    var completionTarget: Int,
    var numberCompleted: Int
) : Goal(name = name, type = CompletionConditionType.Todo) {

    private fun updateProgress() {
        numberCompleted = todoList.getTodosCompletedOn(Date()).size
    }

    fun isComplete(): Boolean {
        return numberCompleted == completionTarget
    }

}

enum class CompletionConditionType {
    Count, Time, Todo
}
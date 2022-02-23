import java.util.*
import kotlin.math.roundToInt

abstract class Goal(
    override val id: Int,
    var name: String,
    val type: CompletionConditionType
) : Task() {
    // TODO field indicating when this resets (daily, weekly, etc)
    override var completed: Boolean = false
    abstract fun reset()
    abstract fun getProgressPercent(): Int
    override fun toString(): String {
        return "$id) [${if (completed) "✅" else " "}]$name: "
    }
}

class TodoGoal(
    id: Int,
    name: String,
    private var todoList: TodoList,
    private var completionTarget: Int,
) : Goal(id = id, name = name, type = CompletionConditionType.Todo) {
    private var numberCompleted: Int = 0

    private fun updateProgress() {
        numberCompleted = todoList.getTodosCompletedOn(Date()).size
        completed = numberCompleted >= completionTarget
    }

    override fun getProgressPercent(): Int {
        updateProgress()
        return ((numberCompleted).toDouble() / (completionTarget).toDouble()).roundToInt()
    }

    override fun toString(): String {
        return super.toString() + "$numberCompleted/$completionTarget ToDos (${getProgressPercent()}% complete)"
    }

    override fun reset() {
        numberCompleted = 0
    }
}

class CountGoal(
    id: Int,
    name: String,
    private var completionTarget: Int,
    private val units: String
) : Goal(id = id, name = name, type = CompletionConditionType.Count) {
    private var numberCompleted = 0

    fun addCount(count: Int) {
        numberCompleted += count
    }

    fun subtractCount(count: Int) {
        numberCompleted -= count
    }

    fun updateProgress() {
        completed = numberCompleted == completionTarget
    }

    override fun reset() {
        numberCompleted = 0
    }

    override fun getProgressPercent(): Int {
        return ((numberCompleted).toDouble() / (completionTarget).toDouble()).roundToInt()
    }

    override fun toString(): String {
        return super.toString() + "$numberCompleted/$completionTarget ${units}(s) (${getProgressPercent()}% complete)"
    }

}


enum class CompletionConditionType {
    Count, /*Time,*/ Todo
}
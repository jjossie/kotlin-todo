import java.util.*

class Todo(var name: String) {
    var completed: Boolean = false
    private val dateCreated = Date()
    var dateCompleted: Date? = null
    val id: Int = 0
    fun complete() {
        completed = true
        dateCompleted = Date()
    }
    fun incomplete() {
        completed = false
        dateCompleted = null
    }
    override fun toString(): String {
        return "[${if (completed) "X" else " "}] $name "
    }
    fun render(): String{
        var output = "$name: ${if (completed) "done" else "not done"}" +
                "\ncreated: $dateCreated"
        if (completed) {
            output += "\n completed: $dateCompleted"
        }
        return output
    }
}
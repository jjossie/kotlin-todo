import java.util.*

class Todo(val id: Int = 0, name: String) {
    var name: String = name
        private set
    var completed: Boolean = false
        private set
    private val dateCreated = Date()
    var dateCompleted: Date? = null
        private set
    private var reminderDate: Date? = null

    fun complete() {
        completed = true
        dateCompleted = Date()
    }
    fun incomplete() {
        completed = false
        dateCompleted = null
    }
    override fun toString(): String {
        return "$id) [${if (completed) "X" else " "}] $name "
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
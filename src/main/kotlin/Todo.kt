import java.time.LocalDate
import java.util.*

class Todo(override val id: Int = 0, name: String) : Task() {
    var name: String = name
        private set
    override var completed: Boolean = false
    private val dateCreated = LocalDate.now()
    var dateCompleted: LocalDate? = null
        private set
    private var reminderDate: LocalDate? = null

    fun complete() {
        completed = true
        dateCompleted = LocalDate.now()
    }
    fun incomplete() {
        completed = false
        dateCompleted = null
    }
    override fun toString(): String {
        return "$id) [${if (completed) "✅" else " "}] $name "
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
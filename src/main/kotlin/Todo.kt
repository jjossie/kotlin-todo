@file:Suppress("unused")

import java.time.LocalDate

/**
 * Represents a single To-Do list item that has a name, date created, date completed, and checked-off status.
 */
class Todo(override val id: Int = 0, private var name: String) : Task() {
    override var completed: Boolean = false
    private val dateCreated = LocalDate.now()
    var dateCompleted: LocalDate? = null
        private set
    private var reminderDate: LocalDate? = null // To be used in the future for reminders

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
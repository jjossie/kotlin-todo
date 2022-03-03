@file:Suppress("unused")

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.time.LocalDate

/**
 * Represents a single To-Do list item that has a name, date created, date completed, and checked-off status.
 */
class Todo(override val id: Int = 0, var name: String) : Task() {
    override var completed: Boolean = false
    @Exclude
    private val dateCreated: Long = LocalDate.now().toEpochDay()
    @Exclude
    private var dateCompleted: Long? = null
//        private set
    @Exclude
    private var reminderDate: Long? = null // To be used in the future for reminders

    fun complete() {
        completed = true
        dateCompleted = LocalDate.now().toEpochDay()
    }

    fun incomplete() {
        completed = false
        dateCompleted = null
    }

    @Exclude
    fun getCreationDate(): LocalDate {
        return LocalDate.ofEpochDay(dateCreated)
    }

    @Exclude
    fun getCompletionDate(): LocalDate? {
        return dateCompleted?.let { LocalDate.ofEpochDay(it) }
    }

    override fun toString(): String {
        return "$id) [${if (completed) "✅" else " "}] $name "
    }

    fun render(): String {
        var output = "$name: ${if (completed) "done" else "not done"}" + 1
        "\ncreated: ${getCreationDate()}"
        if (completed) {
            output += "\n completed: ${getCreationDate()}"
        }
        return output
    }

}
@file:Suppress("unused")

import com.google.cloud.firestore.annotation.Exclude
import com.google.cloud.firestore.annotation.IgnoreExtraProperties
import java.time.LocalDate

/**
 * Represents a single To-Do list item that has a name, date created, date completed, and checked-off status.
 */
@IgnoreExtraProperties
class Todo(override val id: Int = 0, var name: String) : Task() {
    override var completed: Boolean = false
    val dateCreated: Long = LocalDate.now().toEpochDay()
    var dateCompleted: Long? = null
        private set
    var reminderDate: Long? = null // To be used in the future for reminders
        private set

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
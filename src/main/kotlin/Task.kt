import com.google.firebase.database.IgnoreExtraProperties

/**
 * Parent class for To-do and Goal. Allows TodoList and GoalList to inherit from ProductivityList
 * because ProductivityList can have an ArrayList of Tasks rather than either To-Dos or Goals.
 */
@IgnoreExtraProperties
abstract class Task {
    abstract var completed: Boolean
        protected set
    abstract val id: Int
}
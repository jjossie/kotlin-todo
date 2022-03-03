import com.google.firebase.database.IgnoreExtraProperties

/**
 * Parent class for TodoList and GoalList since they share a lot in common.
 */
@IgnoreExtraProperties
abstract class ProductivityList {
    protected var nextId = 1

    var itemList = ArrayList<Task>()
        protected set

    fun display(filter: ListFilter) {
        var output = ""
        itemList
            .filter {
                when (filter) {
                    ListFilter.All -> true
                    ListFilter.Completed -> it.completed
                    ListFilter.Incomplete -> !it.completed
                }
            }
            .forEach {
                output += "$it\n"
            }
        if (output == "") {
            output = "No items.\n"
        }
        print(output)
    }

}

enum class ListFilter {
    All, Completed, Incomplete
}
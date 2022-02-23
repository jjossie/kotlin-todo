abstract class ProductivityList {
    protected var nextId = 1

    protected var itemList = ArrayList<Task>()

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
            output = "No to-do items.\n"
        }
        print(output)
    }

}

enum class ListFilter {
    All, Completed, Incomplete
}
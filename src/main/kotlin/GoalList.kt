class GoalList : ProductivityList() {
    fun addGoal(
        name: String,
        type: CompletionConditionType,
        completionTarget: Int,
        units: String? = null,
        todoList: TodoList? = null
    ) {
        val newGoal: Goal = when (type) {
            CompletionConditionType.Count ->
                CountGoal(nextId++, name, completionTarget, units ?: throw Error())
            CompletionConditionType.Todo ->
                TodoGoal(nextId++, name, todoList ?: throw Error(), completionTarget)
        }
        itemList.add(newGoal)
    }

    fun displayOfType(type: CompletionConditionType){
        var output = ""
        itemList
            .filter {
                if (it is Goal) {
                    it.type == type
                } else {
                    false
                }
            }
            .forEach {
                output += "$it\n"
            }
        print(output)
    }
}
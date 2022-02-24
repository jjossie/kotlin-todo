import java.sql.Time

class GoalList : ProductivityList() {

    // TODO add logic for resetting goals once per day
//    var needsReset: Boolean = false
//    val resetTime: Time = Time(0)

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

    /***
     * Update the logic of each goal, including updating progress
     * based on information from the todos and resetting based on
     * the day.
     */
    fun update() {
        // Update progress for each TodoGoal
        itemList
            .filterIsInstance<TodoGoal>()
            .forEach { it.updateProgress() }
        // TODO Reset necessary goals
    }
}
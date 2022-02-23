typealias menuFunction = () -> Unit

class TodoUI {
    private val todoList = TodoList("Test")
    private val goalList = GoalList()
    private val todoMenu: Menu = Menu(
        arrayListOf(
            MenuOperation("Show All") {
                todoList.display(ListFilter.All)
            },
            MenuOperation("Show Completed") {
                todoList.display(ListFilter.Completed)
            },
            MenuOperation("Show Incomplete") {
                todoList.display(ListFilter.Incomplete)
            },
            MenuOperation("Add To-Do") {
                todoList.addTodo(getTaskName("What would you like to do?"))
            },
            MenuOperation("Complete task") {
                markTodo(true)
            },
            MenuOperation("Uncomplete Task") {
                markTodo(false)
            },
            MenuOperation("Exit") {
                clear()
                menu = mainMenu // Switch back to main menu
            },
        )
    )
    private val goalMenu: Menu = Menu(
        arrayListOf(
            MenuOperation("Add Goal") {
                // Create a new Goal (need a type first)
                try {
                    val goalType = getGoalType() ?: throw Error()
                } catch (e: Error) {
                    error("Not a valid Goal Type.")
                }
            },
            MenuOperation("Show All Goals") {
                // Display All Goals
                goalList.display(ListFilter.All)
            },
            MenuOperation("Show Completed Goals") {
                // Display Completed Goals
                goalList.display(ListFilter.Completed)
            },
            MenuOperation("Show Incomplete Goals") {
                // Display Incomplete Goals
                goalList.display(ListFilter.Incomplete)
            },
            MenuOperation("Show ToDo-based Goals") {
                goalList.displayOfType(CompletionConditionType.Todo)
            },
            MenuOperation("Show Count-based Goals") {
                goalList.displayOfType(CompletionConditionType.Count)
            },
            MenuOperation("Exit"){
                clear()
                menu = mainMenu // Exit to main menu
            }
        )
    )
    private val mainMenu = Menu(arrayListOf(
        MenuOperation("To-Dos") {
            todoList.display(ListFilter.All)
            menu = todoMenu // Switch Menus
        },
        MenuOperation("Goals") {
            menu = goalMenu // Switch Menus
        }
    ))

    private var menu: Menu = mainMenu

    private fun getGoalType(): CompletionConditionType? {
        print("1) Count-Based")
        print("2) To-Do List Based")
        return when (getUserNumber()) {
            1 -> CompletionConditionType.Count
            2 -> CompletionConditionType.Todo
            else -> null
        }
    }

    private fun getTaskName(message: String): String {
        var finished = false
        var name = ""
        while (!finished) {
            print(message)
            print("> ")
            val inputName = readLine()
            if (inputName == null) {
                error("Cannot be blank")
            } else {
                name = inputName
                finished = true
            }
        }
        return name
    }

    private fun markTodo(complete: Boolean) {
        print("Which task would you like to mark as ${if (complete) "complete" else "incomplete"}? (enter the ID number)\n>")
        val todoId = getUserNumber()
        try {
            todoList.markTodo(todoId, complete)
        } catch (e: Error) {
            error("Not a valid todo item.")
        }
    }

    private fun getUserNumber(): Int {
        print("> ")
        val choice: String = readLine() ?: ""
        val choiceNum: Int
        try {
            choiceNum = choice.toInt()
        } catch (e: java.lang.NumberFormatException) {
            error("Not a number")
            throw e
        }
        return choiceNum
    }

    fun displayMenu() {
        print("Select an option: \n")
        print(menu.render())
    }

    fun doMenuOperation() {
        try {
            val choiceNum = getUserNumber()
            menu.execute(choiceNum)
        } catch (e: java.lang.NumberFormatException) {
            return
        } catch (e: Error) {
            error("Not a valid menu item")
            return
        }
    }

    private fun error(msg: String) {
        print("\nERROR: $msg\n")
    }

    /**
     * Simulate a clear screen function by just printing a bunch of empty lines lol
     */
    private fun clear() {
        for (i in 0..20) {
            print("\n")
        }
    }

}

class Menu(
    private val operations: ArrayList<MenuOperation>
) {
    init {
        MenuOperation.nextId = 1
    }
    fun execute(operationId: Int) {
        val operation: MenuOperation = operations.find { it.id == operationId } ?: throw Error() // TODO?
        operation.menuFunction.invoke()
    }

    fun render(): String {
        var menuOutput = ""
        for (op in operations) {
            menuOutput += "${op.id}. ${op.description} \n"
        }
        return menuOutput
    }
//    fun newMenuOperation(description: String, menuFunction: menuFunction): MenuOperation {
//        return MenuOperation(nextId++, description, menuFunction)
//    }
}

class MenuOperation(/*val id: Int,*/ val description: String, val menuFunction: menuFunction) {
    var id: Int = nextId++

    companion object {
        var nextId = 1
    }
}
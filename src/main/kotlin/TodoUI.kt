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
                todoList.addTodo(getUserString("What would you like to do?"))
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
                createNewGoal()
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
            MenuOperation("Exit") {
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
    private fun createNewGoal() {
        // Get goal Type
        val goalType =
            getGoalType() ?: throw Error("Not a valid goal type") // TODO change this to be handled in the function

        // Get goal name
        val name = getUserString("What is your goal?")

        // Two different sets of params based on goal type
        if (goalType == CompletionConditionType.Count) {
            // Get completion target based on goal type
            val unitPrompt = "What type of progress are you counting?"
            val units = getUserString(unitPrompt)
            val targetPrompt = "How many ${units} would you like to complete per day?"
            val target = getUserNumber(targetPrompt)

            // Finally, create the goal
            goalList.addGoal(name, goalType, target, units = units)

        } else if (goalType == CompletionConditionType.Todo) {
            // Get target number of to-do items
            val targetPrompt = "How many todo list items would you like to complete per day?"
            val target = getUserNumber(targetPrompt)

            // Finally, create the goal
            goalList.addGoal(name, goalType, target, todoList = todoList)
        }
    }


    private fun getGoalType(): CompletionConditionType? {
        // TODO guarantee results with loop logic
        println("What type of goal would you like to make?")
        println("1) Count-Based")
        println("2) To-Do List Based")
        return when (getUserNumber()) {
            1 -> CompletionConditionType.Count
            2 -> CompletionConditionType.Todo
            else -> null
        }
    }


    private fun markTodo(complete: Boolean) {
        val prompt = "Which task would you like to mark as ${if (complete) "complete" else "incomplete"}? (enter the ID number)\n>"
        val todoId = getUserNumber(prompt)
        try {
            todoList.markTodo(todoId, complete)
        } catch (e: Error) {
            error("Not a valid todo item.")
        }
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

    private fun getUserString(message: String): String {
        var finished = false
        var name = ""
        while (!finished) {
            print(message)
            print("> ")
            val inputName = readLine()
            if (inputName.isNullOrBlank()) {
                error("Cannot be blank")
            } else {
                name = inputName
                finished = true
            }
        }
        return name
    }

    private fun getUserNumber(message: String? = null): Int {
        // TODO make this a guaranteed loop like getUserString()
        if (message != null) {
            print(message)
        }
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
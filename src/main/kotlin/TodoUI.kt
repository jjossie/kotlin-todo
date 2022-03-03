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

    /**
     * Holds the currently displayed menu.
     */
    private var menu: Menu = mainMenu

    /**
     * Allows the user to create a new goal by prompting for a set of parameters. Which parameters
     * are prompted for depends on the type specified first by the user - for example,
     * To-Do type goals will prompt the user for the number of to-dos that day, whereas a
     * count-based goal will prompt the user for a unit of measurement of the goal as well as
     * a target number.
     */
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
            val targetPrompt = "How many $units would you like to complete per day?"
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

    /**
     * Displays all menu options for this UI object's current menu.
     */
    fun displayMenu() {
        print("Select an option: \n")
        print(menu.render())
    }

    /**
     * Enables the user to select a menu item and execute the operation associated with it.
     */
    fun doMenuOperation() {
        try {
            val choiceNum = getUserNumber()
            menu.execute(choiceNum)
        } catch (e: Error) {
            error("Not a valid menu item")
            return
        }
    }

    /**
     * Wrapper function to be called at a logical update interval which
     * will update the progress of all goals and all todos.
     */
    fun update() {
        goalList.update()
    }

    /**
     * Prompts the user to select one of the enumerated goal types.
     */
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

    /**
     * Helps the user select a to-do to mark as complete or incomplete.
     */
    private fun markTodo(complete: Boolean) {
        val prompt = "Which task would you like to mark as ${if (complete) "complete" else "incomplete"}? (enter the ID number)\n>"
        val todoId = getUserNumber(prompt)
        try {
            todoList.markTodo(todoId, complete)
        } catch (e: Error) {
            error("Not a valid todo item.")
        }
    }

    /**
     * Prompts the user repeatedly for a non-blank string to be used for
     * getting goal and to-do names.
     */
    fun getUserString(message: String): String {
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

    /**
     * Prompts the user repeatedly for an integer to be used for menu choices,
     * goal counts, etc.
     */
    private fun getUserNumber(message: String? = null): Int {

        var finished = false
        var choiceNum = 0
        while (!finished) {
            if (message != null) {
                print(message)
            }
            print("> ")
            val choice = readLine()
            if (choice.isNullOrBlank()) {
                error("Cannot be blank")
            } else {
                try {
                    choiceNum = choice.toInt()
                    finished = true
                } catch (e: java.lang.NumberFormatException) {
                    error("Not a number")
                }
            }
        }
        return choiceNum
    }

    /**
     * A helper function like debug() for printing errors to the console.
     */
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

/**
 * Represents a list of Menu Operations with methods to display the menu and
 * execute one of its operations.
 */
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
}

/**
 * Represents a function with a name attached to it, as well as an automatically generated
 * ID so the user can execute menu options by only entering a number.
 */
class MenuOperation(/*val id: Int,*/ val description: String, val menuFunction: menuFunction) {
    var id: Int = nextId++

    companion object {
        var nextId = 1
    }
}
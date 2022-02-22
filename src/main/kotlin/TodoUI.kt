typealias menuFunction = () -> Unit

class TodoUI {
    private val todoList: TodoList = TodoList("Test")
    private val todoMenu: Menu = Menu(
        arrayListOf(
            MenuOperation(1, "Show All") {
                todoList.display(TodoListFilter.All)
            },
            MenuOperation(2, "Show Completed") {
                todoList.display(TodoListFilter.Completed)
            },
            MenuOperation(3, "Show Incomplete") {
                todoList.display(TodoListFilter.Incomplete)
            },
            MenuOperation(4, "Add To-Do") {
                todoList.addTodo(getTodoName())
            },
            MenuOperation(5, "Complete task") {
                // mark as complete
                markTodo(true)
            },
            MenuOperation(6, "Uncomplete Task") {
                markTodo(false)
            },
            MenuOperation(7, "Exit") {
                clear()
                menu = mainMenu // Switch back to main menu
            },
        )
    )
    private val goalMenu: Menu = Menu(
        arrayListOf(
            // TODO
        )
    )
    private val mainMenu = Menu(arrayListOf(
        MenuOperation(1, "To-Dos") {
            todoList.display(TodoListFilter.All)
            menu = todoMenu // Switch Menus
        },
        MenuOperation(2, "Goals") {
            menu = goalMenu // Switch Menus
        }
    ))

    private var menu: Menu = mainMenu

    private fun getTodoName(): String {
        var finished = false
        var name = ""
        while (!finished) {
            print("What would you like to do?")
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

    fun displayMenu() {
        print("Select an option: \n")
        print(menu.render())
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

class MenuOperation(val id: Int, val description: String, val menuFunction: menuFunction)
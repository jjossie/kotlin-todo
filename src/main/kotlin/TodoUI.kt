typealias menuFunction = () -> Unit

class TodoUI {
    private val todoList: TodoList = TodoList("Test")
    private val todoMenu: Menu = Menu(arrayListOf(
        MenuOperation(1, "Display All Todos") {
            todoList.display(TodoListFilter.All)
        },
        MenuOperation(2, "Show Completed") {
            todoList.display(TodoListFilter.Completed)
        },
        MenuOperation(3, "Show Incomplete") {
            todoList.display(TodoListFilter.Incomplete)
        },
        MenuOperation(4, "Add To-Do") {
            todoList.addTodo(createTodo())
        },
        MenuOperation(5, "Exit"){
            menu = mainMenu // Switch back to main menu
        },
        MenuOperation(6, "Complete task") {
            // mark as complete
        }
    ))
    private val goalMenu: Menu = Menu(arrayListOf(
        // TODO
    ))
    private val mainMenu = Menu(arrayListOf(
        MenuOperation(1, "To-Dos") {
            todoList.display(TodoListFilter.All)
            menu = todoMenu // Switch Menus
        },
        MenuOperation(2, "Goals"){
            menu = goalMenu // Switch Menus
        }
    ))

    private var menu: Menu = mainMenu

    private fun createTodo(): Todo {
        var finished = false
        var name: String = ""
        while (!finished) {
            print("> ")
            val inputName = readLine()
            if (inputName == null) {
                error("Cannot be blank")
            } else {
                name = inputName
                finished = true
            }
        }
        return Todo(name)
    }

    fun displayMenu() {
        print("Select an option: \n")
        print(menu.render())
    }

    fun doMenuOperation() {
        print("> ")
        val choice: String = readLine() ?: ""
        val choiceNum: Int
        try {
            choiceNum = choice.toInt()
        } catch (e: java.lang.NumberFormatException) {
            error("Not a number")
            return
        }
        try {
            menu.execute(choiceNum)
        } catch (e: Error) {
            error("Not a valid menu item")
        }
    }

    private fun error(msg: String) {
        print("\nERROR: $msg\n")
    }

}

class Menu(private val operations: ArrayList<MenuOperation>) {
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
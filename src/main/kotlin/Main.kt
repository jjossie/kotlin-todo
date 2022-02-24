@file:Suppress("unused")

/**
 * Main Function simply creates a UI object and loops through its display, execution, and update functions.
 */
fun main() {

    val ui = TodoUI()

    while (true){
        ui.displayMenu()
        ui.doMenuOperation()
        ui.update()
    }
}

/**
 * Helper function for debugging purposes
 */
fun debug(message: String) {
    println("\nDEBUG: $message\n")
}
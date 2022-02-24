
fun main() {

    val ui = TodoUI()

    while (true){
        ui.displayMenu()
        ui.doMenuOperation()
        ui.update()
    }
}

fun debug(message: String) {
    println("\nDEBUG: $message\n")
}
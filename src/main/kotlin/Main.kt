@file:Suppress("unused")

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.cloud.firestore.Firestore
import com.google.firebase.cloud.FirestoreClient


/**
 * Main Function simply creates a UI object and loops through its display, execution, and update functions.
 */
fun main() {

    val ui = TodoUI()

    testFirestore()

    while (true) {
        ui.displayMenu()
        ui.doMenuOperation()
        ui.update()
    }
}


fun testFirestore() {
    val db = Database.getDatabase()

    val user = hashMapOf<String, Any>(
        "first" to "hugh",
        "last" to "jass",
        "born" to 420
    )
    db.collection("users")
        .add(user)
}


/**
 * Helper function for debugging purposes
 */
fun debug(message: String) {
    println("\nDEBUG: $message\n")
}

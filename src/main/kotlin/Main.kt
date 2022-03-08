@file:Suppress("unused")

import com.google.api.core.ApiFuture
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.CollectionReference
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QuerySnapshot
import com.google.cloud.firestore.WriteResult
import com.google.firebase.cloud.FirestoreClient


/**
 * Main Function simply creates a UI object and loops through its display, execution, and update functions.
 */
fun main() {

    val username = getUserString("Enter your username")
    val ui = TodoUI(username)
//    testFirestore()

    while (true) {
        ui.displayMenu()
        ui.doMenuOperation()
        ui.update()
    }
}


fun testFirestore() {
    val documentName = "users"

    // Get database
    val db = Database.getDatabase()
//    val colRef: CollectionReference = db.collection("users")

    // Make document
    val docRef = db.collection(documentName).document("hughjass")
    // Make user hashmap
    val user = hashMapOf<String, Any>(
        "first" to "hugh",
        "last" to "jass",
        "born" to 420
    )
//    val colResult = colRef.add(user)

    // Store user hashmap in document
    val result: ApiFuture<WriteResult> = docRef.set(user)
    // This call is blocking
    println("Time taken: ${result.get().updateTime}")


    // Get all users
    val query = db.collection(documentName).get()
    val querySnapshot = query.get()
    val documents = querySnapshot.documents
    for (doc in documents) {
        println("User ID: ${doc.id}")
        val middleName = doc.getString("middle") ?: ""
        println("Name: ${doc.getString("first")} $middleName ${doc.getString("last")}")
        println("Born: ${doc.getLong("born")}")
    }

}


/**
 * Helper function for debugging purposes
 */
fun debug(message: String) {
    println("\nDEBUG: $message\n")
}


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
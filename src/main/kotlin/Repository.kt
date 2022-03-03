class Repository {

    private val userCollectionName = "users"
    private val todoListDocumentName = "todo_list"
    private val goalListDocumentName = "goal_list"

    fun saveUser(name: String, todoList: TodoList?, goalList: GoalList?){
        val db = Database.getDatabase()
        val colRef = db.collection(userCollectionName)
//        if (todoList != null) {
//            colRef.document(todoListDocumentName).set(todoList)
//        }
//        if (goalList != null) {
//            colRef.document(goalListDocumentName).set(goalList)
//        }
        val docData = hashMapOf<String, Any?>(
            todoListDocumentName to todoList,
            goalListDocumentName to goalList
        )
        val result = colRef.document(name).set(docData)
    }
}
class Repository {

    private val userCollectionName = "users"
    private val todoListSubCollectionName = "todo_list"
    private val goalListSubCollectionName = "goal_list"

    fun saveUser(name: String, todoList: TodoList?, goalList: GoalList?){
        val db = Database.getDatabase()
        val colRef = db.collection(userCollectionName)
//        if (todoList != null) {
//            colRef.document(todoListSubCollectionName).set(todoList)
//        }
//        if (goalList != null) {
//            colRef.document(goalListSubCollectionName).set(goalList)
//        }
        val docData = hashMapOf<String, Any?>(
            todoListSubCollectionName to todoList,
//            goalListSubCollectionName to goalList
        )
        val result = colRef.document(name).set(docData)
        val todoColRef = colRef.document(name).collection(todoListSubCollectionName)
        if (todoList == null) {
            error("todo list is empty when trying to save to DB")
        } else {
            for (todo in todoList.itemList){
//                todoColRef.add(todo)
            }
        }
    }
}
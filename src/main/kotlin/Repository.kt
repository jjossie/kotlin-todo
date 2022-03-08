class Repository {

    private val userCollectionName = "users"
    private val todoListSubCollectionName = "todo_list"
    private val goalListSubCollectionName = "goal_list"

    fun saveUser(name: String, todoList: TodoList?, goalList: GoalList?){
        val db = Database.getDatabase()
        val colRef = db.collection(userCollectionName)
        val docData = hashMapOf<String, Any?>(
            todoListSubCollectionName to todoList,
            goalListSubCollectionName to goalList
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

    fun loadTodoList(name: String) : TodoList? {
        val db = Database.getDatabase()

        // Get TodoList Object
        val todoListDocRef = db.collection(name).document(todoListSubCollectionName)
        val future = todoListDocRef.get()
        val todoListDocSnap = future.get()
        debug(todoListDocSnap.toString())
        debug(TodoList::class.java.toString())
        debug(TodoList::class.java.declaredFields[0].toString())
        val todoList = todoListDocSnap.toObject(TodoList::class.java) ?: return null
        debug("TodoList found - Name: ${todoList.name}")
        // Get To-do Objects to reconstruct TodoList's itemList
//        val query = todoListDocRef.collection(todoListSubCollectionName).get()
//        val documents = query.get().documents
//        for (doc in documents) {
//            val todo = doc.toObject(Todo::class.java)
//            todoList.itemList.add(todo)
//        }

        return todoList
    }
}
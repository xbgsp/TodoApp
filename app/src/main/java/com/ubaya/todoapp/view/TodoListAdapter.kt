package com.ubaya.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.todoapp.R
import com.ubaya.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Any) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.checkTask.setText(todoList[position].title.toString())

        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, b ->
            compoundButton.isChecked = false
            adapterOnClick(todoList[position])
        }
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}

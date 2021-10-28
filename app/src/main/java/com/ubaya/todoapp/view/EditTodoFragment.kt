package com.ubaya.todoapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.todoapp.R
import com.ubaya.todoapp.databinding.FragmentEditTodoBinding
import com.ubaya.todoapp.model.Todo
import com.ubaya.todoapp.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*
import kotlinx.android.synthetic.main.fragment_create_todo.radioGroupPriority
import kotlinx.android.synthetic.main.fragment_create_todo.radioHigh
import kotlinx.android.synthetic.main.fragment_create_todo.radioLow
import kotlinx.android.synthetic.main.fragment_create_todo.radioMedium
import kotlinx.android.synthetic.main.fragment_create_todo.txtJudulTodo
import kotlinx.android.synthetic.main.fragment_create_todo.txtNotes
import kotlinx.android.synthetic.main.fragment_create_todo.txtTitle
import kotlinx.android.synthetic.main.fragment_edit_todo.*

class EditTodoFragment : Fragment(), RadioClickListener, TodoSaveChangesListener {
    private lateinit var viewModel:DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater,
            R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        dataBinding.radioListener = this
        dataBinding.listener = this

//        btnCreate.setOnClickListener {
//            val radio = view.findViewById<RadioButton>(radioGroupPriorty.checkedRadioButtonId)
//            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//        }

        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it

//            txtTitle.setText(it.title)
//            txtNotes.setText(it.notes)
//
//            when (it.priority) {
//                3 -> radioHigh.isChecked = true
//                2 -> radioMedium.isChecked = true
//                else -> radioLow.isChecked = true
//            }
        })
    }

    override fun onRadioClick(v: View, obj: Todo) {
        obj.priority = v.tag.toString().toInt()
    }

    override fun onTodoSaveChanges(v: View, obj: Todo) {
        Log.d("cek", obj.toString())
        viewModel.update(obj.title,obj.notes,obj.priority,obj.uuid)
        Toast.makeText(v.context, "Todo updated", Toast.LENGTH_SHORT).show()
    }
}
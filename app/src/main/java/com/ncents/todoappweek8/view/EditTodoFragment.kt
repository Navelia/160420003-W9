package com.ncents.todoappweek8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ncents.todoappweek8.R
import com.ncents.todoappweek8.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.txtTitle).text = "Edit Todo"

        view.findViewById<Button>(R.id.btnAdd).text = "Save Changes"

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        view.findViewById<Button>(R.id.btnAdd).setOnClickListener{
            val radio = view.findViewById<RadioButton>(view.findViewById<RadioGroup>(R.id.radioGroupPriority).checkedRadioButtonId)
            viewModel.update(view.findViewById<TextView>(R.id.txtTitle).text.toString(),view.findViewById<TextView>(R.id.txtNotes).text.toString(), radio.tag.toString().toInt(), uuid)

            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            view?.findViewById<TextView>(R.id.txtTitle)?.setText(it.title)
            view?.findViewById<TextView>(R.id.txtNotes)?.setText(it.notes)

            var radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
            var radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
            var radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)

            when(it.priority){
                1 -> radioLow?.isChecked = true
                2 -> radioMedium?.isChecked = true
                else -> radioHigh?.isChecked = true
            }
        })
    }
}
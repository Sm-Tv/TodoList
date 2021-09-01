package sm_tv_prodactions.com.newtodolist.fragments.mainList.list

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_first_main.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.mainList.adapters.AdapterPersonalNote
import sm_tv_prodactions.com.newtodolist.fragments.mainList.adapters.AdapterTest
import sm_tv_prodactions.com.newtodolist.fragments.mainList.adapters.MyMainFirstAdapter
import sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel.FirstMainViewModel
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ParentModel


class FirstMainFragment : Fragment() {

    private lateinit var adapter: MyMainFirstAdapter

    private lateinit var mViewModel: FirstMainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_main, container, false)

        // добавляем слушатель на кнопку добавить
        view.my_first_main_floatingActionButton.setOnClickListener{
            addMainNote()
        }

        //recycler view
        adapter = MyMainFirstAdapter()

        val myRecyclerView = view.my_first_main_recyclerView
        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        //myRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        //view model
        mViewModel = ViewModelProvider(this).get(FirstMainViewModel::class.java)
        mViewModel.readParentModel.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it, mViewModel)
        })

        setHasOptionsMenu(true)

        return view
    }

    override fun onResume() {
        super.onResume()
    }

    private fun addMainNote() {
        val dialog = Dialog(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
        val edText = dialogView.findViewById<EditText>(R.id.edDialogText)
        val pbutton = dialogView.findViewById<Button>(R.id.posButton)
        val nbutton = dialogView.findViewById<Button>(R.id.negButton)
        pbutton.setOnClickListener {
            val title = edText.text.toString()
            val timestamp = System.currentTimeMillis()
            val mainNote = ParentModel(0,title,timestamp)
            if (chekInput(title)) {

                mViewModel.addParentModel(mainNote)
                dialog.dismiss()//закрываем диалоговое окно
                //bundelMainNote(title,timestamp)
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.warning_dialog),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        nbutton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setContentView(dialogView)
        dialog.setCancelable(false)
        dialog.show()

    }

    private fun chekInput(title: String): Boolean {
        return !(TextUtils.isEmpty(title))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.delete -> deleteAll()

        }

        return true
    }

    private fun deleteAll() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setPositiveButton(resources.getString(R.string.yes)){_, _ ->
            mViewModel.deleteAllParentModel()
        }
        dialog.setNegativeButton(resources.getString(R.string.no)){_, _ ->

        }
        dialog.setTitle(resources.getString(R.string.Title_dialog_note))
        dialog.setMessage(resources.getString(R.string.massage_dialog_note))
        dialog.create().show()
    }


}
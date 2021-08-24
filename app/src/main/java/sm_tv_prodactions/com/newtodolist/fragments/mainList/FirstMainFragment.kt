package sm_tv_prodactions.com.newtodolist.fragments.mainList

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.android.synthetic.main.fragment_list.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.mainList.adapters.MyMainFirstAdapter
import sm_tv_prodactions.com.newtodolist.models.MainNote
import sm_tv_prodactions.com.newtodolist.viewmodels.MainNoteViewModel


class FirstMainFragment : Fragment() {

    private lateinit var adapter: MyMainFirstAdapter
    private lateinit var mViewModel: MainNoteViewModel


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
        mViewModel = ViewModelProvider(this).get(MainNoteViewModel::class.java)
        mViewModel.readAllMainNoteData.observe(viewLifecycleOwner, Observer { mainNote->
            adapter.setItems(mainNote)
        })


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
            val mainNote = MainNote(0,title,timestamp)
            //val key_id = TOKEN_ID
            if (chekInput(title)) {
                //getData(title,key_id)//делаем запрос
                //добавление в бд
                mViewModel.addMainNote(mainNote)
                dialog.dismiss()//закрываем диалоговое окно
                bundelMainNote(title)
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

    private fun bundelMainNote(title: String){
        val bundle = Bundle()
        bundle.putString("mainNoteName", title)
        findNavController().navigate(R.id.action_firstMainFragment_to_mainListFragment,bundle)
    }



}
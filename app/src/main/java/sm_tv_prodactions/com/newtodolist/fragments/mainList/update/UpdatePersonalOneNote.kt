package sm_tv_prodactions.com.newtodolist.fragments.mainList.update

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update_feagment.*
import kotlinx.android.synthetic.main.fragment_update_feagment.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel.FirstMainViewModel
import sm_tv_prodactions.com.newtodolist.fragments.update.note.UpdateFeagmentArgs
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ChildModel
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels

class UpdatePersonalOneNote : Fragment() {

    private val args by navArgs<UpdatePersonalOneNoteArgs>()
    private lateinit var myViewModel: FirstMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update_personal_one_note, container, false)

        view.edTitle_update.setText(args.childTitle)
        myViewModel = ViewModelProvider(this).get(FirstMainViewModel::class.java)

        view.idAdd_update.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val uid = args.childUid
        val done =args.childDone
        val title = edTitle_update.text.toString()
        val timestamp = System.currentTimeMillis()
        if (chekInput(title)) {
            val updateChildModel = ChildModel(args.uid, uid,  title, timestamp, done )
            myViewModel.updateChildModel(updateChildModel)
            //Toast.makeText(requireContext(), "Обновили успешно", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putInt("parent_uid", args.childUid )
            bundle.putString("parent_title", args.parentTitle)
            findNavController().navigate(R.id.action_updatePersonalOneNote_to_mainAddListFragment, bundle)
        } else {
            Toast.makeText(requireContext(), resources.getString(R.string.warning_message), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun chekInput(title: String): Boolean {
        return !(TextUtils.isEmpty(title))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save ->  {
                updateItem()
            }
            android.R.id.home -> {
                val bundle = Bundle()
                bundle.putInt("parent_uid", args.childUid )
                bundle.putString("parent_title", args.parentTitle)
                findNavController().navigate(R.id.action_updatePersonalOneNote_to_mainAddListFragment, bundle)}
        }


        return true
    }


}
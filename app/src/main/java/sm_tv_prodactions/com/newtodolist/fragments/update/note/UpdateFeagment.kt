package sm_tv_prodactions.com.newtodolist.fragments.update.note

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
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels


class UpdateFeagment : Fragment() {

    private val args by navArgs<UpdateFeagmentArgs>()
    private lateinit var myViewModel: NoteViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_feagment, container, false)

        view.edTitle_update.setText(args.currentNote.title)
        myViewModel = ViewModelProvider(this).get(NoteViewModels::class.java)

        view.idAdd_update.setOnClickListener {
            updateItem()
        }
        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem() {
        val title = edTitle_update.text.toString()
        val timestamp = System.currentTimeMillis()
        if (chekInput(title)) {
            val updatenote = Note(args.currentNote.uid, title, timestamp, args.currentNote.done)
            myViewModel.updateNote(updatenote)
            //Toast.makeText(requireContext(), "Обновили успешно", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFeagment_to_listFragment)
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
        when (item.itemId) {
            R.id.save -> {
                updateItem()
            }
            android.R.id.home -> findNavController().navigate(R.id.action_updateFeagment_to_listFragment)
        }

        return true
    }

}
package sm_tv_prodactions.com.newtodolist.fragments.add.note

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels


class AddFragment : Fragment() {
    private lateinit var mViewModel: NoteViewModels
    private lateinit var edTitle: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mViewModel = ViewModelProvider(this).get(NoteViewModels::class.java)

        //устанавливает каретку
        edTitle = view.findViewById(R.id.edTitle)

        view.idAdd.setOnClickListener {
            insertDataToDatabase()
        }
        setHasOptionsMenu(true)

        return view
    }

    override fun onResume() {
        edTitle.showKeyboard()
        super.onResume()
    }

    private fun insertDataToDatabase() {
        val title = edTitle.text.toString()
        val done = false
        val timestamp = System.currentTimeMillis()
        if(chekInput(title)){
            val note = Note(0,title,timestamp,done)
            mViewModel.addNote(note)
            //Toast.makeText(requireContext(),"выполнено добавление в бд",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else {
            Toast.makeText(requireContext(),resources.getString(R.string.warning_message),Toast.LENGTH_SHORT).show()
        }

    }

    private fun chekInput(title:String): Boolean{
        return !(TextUtils.isEmpty(title))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save ->  {
                insertDataToDatabase()
            }
            android.R.id.home -> findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }


        return true
    }

    fun View.showKeyboard() {
        //поставит фокус на поле где вводить текст
        this.requestFocus()
        // эти две строчки вызывают клавиатуру
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}
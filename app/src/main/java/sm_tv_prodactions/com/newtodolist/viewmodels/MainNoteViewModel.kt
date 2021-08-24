package sm_tv_prodactions.com.newtodolist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sm_tv_prodactions.com.newtodolist.data.NoteDataBase
import sm_tv_prodactions.com.newtodolist.models.MainNote
import sm_tv_prodactions.com.newtodolist.repositories.NoteRepository

class MainNoteViewModel(application: Application): AndroidViewModel(application) {

    val readAllMainNoteData: LiveData<List<MainNote>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDataBase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        readAllMainNoteData = repository.readAllMainNoteData
    }

    fun addMainNote(mainNote: MainNote){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMainNote(mainNote)
        }
    }

}
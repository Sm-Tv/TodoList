package sm_tv_prodactions.com.newtodolist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sm_tv_prodactions.com.newtodolist.data.NoteDataBase
import sm_tv_prodactions.com.newtodolist.repositories.NoteRepository
import sm_tv_prodactions.com.newtodolist.models.Note

class NoteViewModels(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Note>>

    //val readAllDataCompletedNote: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDataBase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        readAllData = repository.readAllData
        //readAllDataCompletedNote = repository.readAllData
    }

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(note)
        }
    }

    fun deleteNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }



}
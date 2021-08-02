package sm_tv_prodactions.com.newtodolist.repositories

import androidx.lifecycle.LiveData
import sm_tv_prodactions.com.newtodolist.data.NoteDao
import sm_tv_prodactions.com.newtodolist.models.Note

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.getAllLiveData()
    //val readAllDataCompleted: LiveData<List<Note>> = noteDao.getAllLiveDataCompleted()

    suspend fun addNote(note: Note){
        noteDao.insert(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.update(note)
    }

    suspend fun deleteNote(note:Note){
        noteDao.delete(note)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }


}

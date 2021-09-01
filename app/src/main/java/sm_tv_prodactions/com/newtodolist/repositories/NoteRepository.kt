package sm_tv_prodactions.com.newtodolist.repositories

import androidx.lifecycle.LiveData
import sm_tv_prodactions.com.newtodolist.data.NoteDao
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ChildModel
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ParentModel

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.getAllLiveData()
    val readAllChildModel: LiveData<List<ChildModel>> = noteDao.getChildModel()
    //val readAllMainNoteData: LiveData<List<MainNote>> = noteDao.getAllLiveMainNoteData()
    val readAllParentModel: LiveData<List<ParentModel>> = noteDao.getParentModel()
    //val readAllDataCompleted: LiveData<List<Note>> = noteDao.getAllLiveDataCompleted()



    fun getModel(uid: Int): LiveData<List<ChildModel>>{
        return noteDao.getForeignKeyPersonalNote(uid)
    }

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

    //////////////////////////////
    //foreignKey
    suspend fun addParentModel(parentModel: ParentModel){
        noteDao.insertParentModel(parentModel)
    }

    suspend fun addChildModel(childModel: ChildModel){
        noteDao.insertChildModel(childModel)
    }

    suspend fun updateChildModel(childModel: ChildModel){
        noteDao.updateChildModel(childModel)
    }

    suspend fun deleteParentModel(parentModel: ParentModel){
        noteDao.deleteParentModel(parentModel)
    }

    suspend fun deleteAllParentModel(){
        noteDao.deleteAllParentModel()
    }

    suspend fun deleteChildModel(childModel: ChildModel){
        noteDao.deleteChildModel(childModel)
    }

    suspend fun deleteAllChildInParent(child_uid: Int){
        noteDao.deleteAllChildInParent(child_uid)
    }

}

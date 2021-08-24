package sm_tv_prodactions.com.newtodolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import sm_tv_prodactions.com.newtodolist.models.MainNote
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.StickyNotes

@Dao
interface NoteDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insert(vararg note: Note)

    @Update
    fun update(vararg note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM Note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM Note_table ORDER BY uid DESC")
    fun getAllLiveData(): LiveData<List<Note>>


    //main!!
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insertMain (vararg mainNote: MainNote)

    @Update
    fun updateMain(vararg mainNote: MainNote)

    @Delete
    fun deleteMain(mainNote: MainNote)

    @Query("DELETE FROM Main_note_table")
    fun deleteAllMainNotes()

    @Query("SELECT * FROM Main_note_table ORDER BY main_uid DESC")
    fun getAllLiveMainData(): LiveData<List<StickyNotes>>

    @Query("SELECT * FROM Main_note_table ORDER BY main_uid DESC")
    fun getAllLiveMainNoteData(): LiveData<List<MainNote>>

    //@Query("SELECT * FROM goods WHERE done = 0")
    //fun getAllLiveData(): LiveData<List<Note>>


}

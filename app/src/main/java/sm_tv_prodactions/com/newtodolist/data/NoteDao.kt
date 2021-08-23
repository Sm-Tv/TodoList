package sm_tv_prodactions.com.newtodolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Query("SELECT * FROM Main_note_table ORDER BY main_uid DESC")
    fun getAllLiveMainData(): LiveData<List<StickyNotes>>

    //@Query("SELECT * FROM goods WHERE done = 0")
    //fun getAllLiveData(): LiveData<List<Note>>


}

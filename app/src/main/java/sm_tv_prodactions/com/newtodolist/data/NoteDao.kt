package sm_tv_prodactions.com.newtodolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import sm_tv_prodactions.com.newtodolist.models.Note

@Dao
interface NoteDao {
    //@Query("SELECT * FROM Note_table")
    //fun getAll(): List<Note>


    //загрузить все заметки у которых айди из списка
    //@Query("SELECT * FROM Note_table WHERE uid IN (:userIds)")
    //fun loadAllByIds(userIds: IntArray): List<Note>

    //@Query("SELECT * FROM Note_table WHERE uid = :uider LIMIT 1")
    //suspend fun findById(uider: Int)

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

    //@Query("SELECT * FROM goods WHERE done = 0")
    //fun getAllLiveData(): LiveData<List<Note>>


}

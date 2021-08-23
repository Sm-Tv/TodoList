package sm_tv_prodactions.com.newtodolist.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note_Plus_table")
data class NotePlus(

    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "note_id")
    var main_note_id: Int,

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long,

    @ColumnInfo(name = "done")
    var done: Boolean
)

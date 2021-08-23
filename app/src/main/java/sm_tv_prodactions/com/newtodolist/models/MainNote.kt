package sm_tv_prodactions.com.newtodolist.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Main_note_table")
data class MainNote(

    @PrimaryKey(autoGenerate = true)
    val main_uid: Int,

    @ColumnInfo(name = "main_title")
    var main_title:String,

    @ColumnInfo(name = "main_timestamp")
    var main_timestamp: Long
)

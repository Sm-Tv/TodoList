package sm_tv_prodactions.com.newtodolist.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Note_plus_table")
data class NotePlus(

    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "note_id")
    var main_note_id: Long,

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long,

    @ColumnInfo(name = "done")
    var done: Boolean
): Parcelable

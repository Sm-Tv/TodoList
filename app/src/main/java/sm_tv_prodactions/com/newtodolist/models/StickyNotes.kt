package sm_tv_prodactions.com.newtodolist.models

import androidx.room.Embedded
import androidx.room.Relation

data class StickyNotes(

    @Embedded
    val main_note: MainNote,

    @Relation(parentColumn = "main_uid", entityColumn = "note_id")
    val notes: List<NotePlus>
)

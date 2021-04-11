package ipvc.estg.cm_tp01.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteinfo")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "desc") val desc: String

)
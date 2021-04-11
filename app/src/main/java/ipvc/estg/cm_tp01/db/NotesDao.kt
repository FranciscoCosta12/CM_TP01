package ipvc.estg.cm_tp01.db

import androidx.room.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM noteinfo ORDER BY id DESC")
    fun getAllNoteInfo(): List<NoteEntity>?

    @Insert
    fun insertNote(user: NoteEntity?)

    @Delete
    fun deleteNote(user: NoteEntity?)

    @Update
    fun updateNote(user: NoteEntity?)
}
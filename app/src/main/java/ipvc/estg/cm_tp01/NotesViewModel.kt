package ipvc.estg.cm_tp01

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ipvc.estg.cm_tp01.db.NoteEntity
import ipvc.estg.cm_tp01.db.NotesDb

class NotesViewModel(app: Application): AndroidViewModel(app)  {

    lateinit var allNotes : MutableLiveData<List<NoteEntity>>

    init{
        allNotes = MutableLiveData()
    }

    fun getAllNotesObservers(): MutableLiveData<List<NoteEntity>> {
        return allNotes
    }

    fun getAllNotes(){
        val noteDao = NotesDb.getAppDatabase((getApplication()))?.notesDao()
        val list = noteDao?.getAllNoteInfo()

        allNotes.postValue(list)
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.trim().isEmpty())
            return false
        return true
    }

    fun insertNoteInfo(entity: NoteEntity){
        val noteDao = NotesDb.getAppDatabase(getApplication())?.notesDao()
        noteDao?.insertNote(entity)
        getAllNotes()
    }

    fun updateNoteInfo(entity: NoteEntity){
        val noteDao = NotesDb.getAppDatabase(getApplication())?.notesDao()
        noteDao?.updateNote(entity)
        getAllNotes()
    }

    fun deleteNoteInfo(entity: NoteEntity){
        val noteDao = NotesDb.getAppDatabase(getApplication())?.notesDao()
        noteDao?.deleteNote(entity)
        getAllNotes()
    }
}
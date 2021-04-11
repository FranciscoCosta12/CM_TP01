package ipvc.estg.cm_tp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ipvc.estg.cm_tp01.adapter.RecyclerViewAdapter
import ipvc.estg.cm_tp01.db.NoteEntity
import kotlinx.android.synthetic.main.activity_notes.*

class Notes : AppCompatActivity() , RecyclerViewAdapter.RowClickListener {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@Notes)
            recyclerViewAdapter = RecyclerViewAdapter(this@Notes)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        viewModel.getAllNotesObservers().observe(this, Observer{
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        saveButton.setOnClickListener{
            val title = titleInput.text.toString()
            val desc = descInput.text.toString()

            if(saveButton.text.equals("Save")){
                if(isEmpty(title) || isEmpty(desc)){
                    Toast.makeText(this@Notes, "Cannot fill in empty fields!",Toast.LENGTH_SHORT).show()
                }else{
                    val note = NoteEntity(0,title, desc)
                    viewModel.insertNoteInfo(note)
                }

            }else{
                if(isEmpty(title) || isEmpty(desc)){
                    Toast.makeText(this@Notes, "Cannot fill in empty fields!",Toast.LENGTH_SHORT).show()
                }else{
                    val note = NoteEntity(titleInput.getTag(titleInput.id).toString().toInt(), title, desc)
                    viewModel.updateNoteInfo(note)
                    saveButton.setText("Save")
                }

            }
            titleInput.setText("")
            descInput.setText("")
        }
    }

    override fun onDeleteNoteClickListener(note: NoteEntity) {
        viewModel.deleteNoteInfo(note)
    }

    override fun onItemClickListener(note: NoteEntity) {
        titleInput.setText(note.title)
        descInput.setText(note.desc)
        titleInput.setTag(titleInput.id, note.id)
        saveButton.setText("Update")
    }
}
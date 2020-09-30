package com.examples.architectureexample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private var noteViewModel: NoteViewModel? = null
    val ADD_NOTE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonAddNote: FloatingActionButton = findViewById(R.id.button_add_note)
        buttonAddNote.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter = NoteAdapter()
        recyclerView.adapter = adapter

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel?.allNotes?.observe(this, {
            adapter.setNotes(it)
        })
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            val title = data?.getStringExtra(AddNoteActivity.EXTRA_TITLE)
            val description = data?.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            val priority = data?.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1)
            val note = Note(
                title!!,
                description!!, priority!!
            )
            noteViewModel!!.insert(note)
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show()
        }
    }
}
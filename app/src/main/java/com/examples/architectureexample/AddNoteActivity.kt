package com.examples.architectureexample


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AddNoteActivity : AppCompatActivity() {
    private var editTextTitle: EditText? = null
    private var editTextDescription: EditText? = null
    lateinit var numberPickerPriority: NumberPicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPickerPriority = findViewById(R.id.number_picker_priority)
        numberPickerPriority.setMinValue(1)
        numberPickerPriority.setMaxValue(10)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"
    }

    private fun saveNote() {
        val title = editTextTitle!!.text.toString()
        val description = editTextDescription!!.text.toString()
        val priority = numberPickerPriority.value
        if (title.trim { it <= ' ' }.isEmpty() || description.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_PRIORITY, priority)
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_TITLE = "com.examples.architectureexample.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.examples.architectureexample.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.examples.architectureexample.EXTRA_PRIORITY"
    }
}
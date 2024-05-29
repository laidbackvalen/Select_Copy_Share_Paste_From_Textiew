package com.example.copyandshare

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textview = findViewById<TextView>(R.id.discriptionText)
        val copyDiscription = findViewById<TextView>(R.id.copyDiscription)

        copyDiscription.setOnClickListener {
            val clipBoardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", textview.text)
            clipBoardManager.setPrimaryClip(clip)
            //ClipData.newPlainText is used to create a new clip with a label and the text from the TextView.
            //ClipboardManager.setPrimaryClip is used to set this clip to the clipboard.
            Toast.makeText(this, "Text Copied To Your ClipBoard!", Toast.LENGTH_SHORT).show()
        }

        val share = findViewById<TextView>(R.id.shareDiscription)
        share.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, textview.text.toString())
            startActivity(shareIntent)
        }

        val edittext = findViewById<TextView>(R.id.editText)
        val delete = findViewById<TextView>(R.id.delete)
        delete.setOnClickListener {
            edittext.text = ""
        }
    }
}
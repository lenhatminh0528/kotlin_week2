package kotlinclass.leminh.kotlin_week2

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.layout_edit_text.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        edt_fullname.setOnClickListener{
            var dialogLayout = LayoutInflater.from(this).inflate(R.layout.layout_edit_text,null)
            var text = dialogLayout.findViewById<EditText>(R.id.edt_text) as EditText
            text.hint = "Full name"
            text.requestFocus()
            AlertDialog.Builder(this).apply {
                setTitle("Full name")
                setPositiveButton("OK"){dialog, which ->
                    edt_fullname.text = text.text.toString()
                    dialog.dismiss()
                }
                setView(dialogLayout)
                show()
            }
        }
        edt_email.setOnClickListener{
            var dialogLayout = LayoutInflater.from(this).inflate(R.layout.layout_edit_text,null)
            var text = dialogLayout.findViewById<EditText>(R.id.edt_text) as EditText
            text.hint = "Email"
            text.requestFocus()
            AlertDialog.Builder(this).apply {
                setTitle("Email")
                setPositiveButton("OK"){dialog, which ->
                    edt_email.text = text.text.toString()
                    dialog.dismiss()
                }
                setView(dialogLayout)
                show()
            }
        }
        edt_phonenumber.setOnClickListener{
            var dialogLayout = LayoutInflater.from(this).inflate(R.layout.layout_edit_text,null)
            var text = dialogLayout.findViewById<EditText>(R.id.edt_text) as EditText
            text.hint = "Phone number"
            text.requestFocus()
            AlertDialog.Builder(this).apply {
                setTitle("Phone number")
                setPositiveButton("OK"){dialog, which ->
                    edt_phonenumber.text = text.text.toString()
                    dialog.dismiss()
                }
                setView(dialogLayout)
                show()
            }
        }

    }
}
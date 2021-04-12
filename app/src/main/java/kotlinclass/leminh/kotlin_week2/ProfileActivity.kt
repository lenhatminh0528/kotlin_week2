package kotlinclass.leminh.kotlin_week2

import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinclass.leminh.kotlin_week2.data.DataStore
import kotlinclass.leminh.kotlin_week2.databinding.ActivityProfileBinding
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.layout_edit_text.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding :ActivityProfileBinding
    private lateinit var viewmodel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
        getBundleData()
        edt_fullname.setOnClickListener{
            var dialogLayout = LayoutInflater.from(this).inflate(R.layout.layout_edit_text,null)
            var text = dialogLayout.findViewById<EditText>(R.id.edt_text) as EditText
            text.hint = "Full name"
            text.requestFocus()
            AlertDialog.Builder(this).apply {
                setTitle("Full name")
                setPositiveButton("OK"){dialog, which ->
                    viewmodel.editData(binding.edtEmail.text.toString(), DataStore.TYPE.USERNAME.value,text.text.toString())
                    Toast.makeText(this@ProfileActivity,"User name changed!",Toast.LENGTH_LONG).show()
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
                    viewmodel.editData(binding.edtEmail.text.toString(),DataStore.TYPE.EMAIL.value,text.text.toString())
                    Toast.makeText(this@ProfileActivity,"Email changed!",Toast.LENGTH_LONG).show()
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
                    viewmodel.editData(binding.edtEmail.text.toString(),DataStore.TYPE.PHONENUMBER.value,text.text.toString())
                    Toast.makeText(this@ProfileActivity,"Phone number changed!",Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
                setView(dialogLayout)
                show()
            }
        }

//        viewmodel.user.observe(this , Observer {
//            Toast.makeText(this,"Data changed!",Toast.LENGTH_LONG).show()
//        })
    }
    fun setUp(){
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile)
        viewmodel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.lifecycleOwner = this
        binding.profileViewModel = viewmodel
    }
    fun getBundleData(){
        var bundle = intent.extras
        var user = bundle?.getParcelable<Account>("user")
        Log.d("TAG","user: ${user?.email}")
        if(user != null){
            viewmodel.setupUserProfile(user.email)
        }
    }
}
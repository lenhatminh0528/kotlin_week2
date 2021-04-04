package kotlinclass.leminh.kotlin_week2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import utils.DialogUtils

class LoginActivity: AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        btn_login.setOnClickListener{
            var email: String = edt_email.text.toString()
            var pass : String = edt_password.text.toString()
            //Toast.makeText(this,"email: "+email+", pass: "+pass+" ",Toast.LENGTH_LONG).show()
            var builder = AlertDialog.Builder(this)
            if (email.equals("ronaldo@gmail.com") && pass.equals("123456")){
//                var intent = Intent(this@LoginActivity, VerifyCodeActivity::class.java)
//                startActivity(intent)
                builder.apply {
                    setMessage("Login successful!")
                    setPositiveButton("OK"){dialog,_->
                        var intent = Intent(this@LoginActivity, ProfileActivity::class.java)
                        startActivity(intent)
                        dialog.dismiss()
                    }
                }
            }else{
                builder.apply {
                    setMessage("Login failed!")
                    setPositiveButton("OK"){dialog,_->
                        dialog.dismiss()
                    }
                }
            }
            Handler(Looper.getMainLooper()).post{
                builder.show()
            }
        }
    }
}
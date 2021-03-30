package kotlinclass.leminh.kotlin_week2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity: AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        edt_email.clearFocus()
        edt_fullname.clearFocus()
        edt_password.clearFocus()
        btn_signup.setOnClickListener{
            var it = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(it)
        }
    }
}
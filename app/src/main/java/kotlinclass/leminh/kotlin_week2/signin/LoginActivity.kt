package kotlinclass.leminh.kotlin_week2.signin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import kotlinclass.leminh.kotlin_week2.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
//    lateinit var binding : ActivityLoginBinding
//    lateinit var viewmodel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setUp()
//        binding.apply {
//            btnSignup.setOnClickListener{
//                gotoSignUp()
//            }
//        }
//        viewmodel.isSuccess.observe(this, Observer {
//            it?.let{
//                if(it){
//                    Toast.makeText(this, " Sign in successful!",Toast.LENGTH_LONG).show()
//                    gotoProfile()
//                }
//            }
//        })
//        viewmodel.isError.observe(this, Observer { message ->
//            message?.let{
//                Toast.makeText(this,message,Toast.LENGTH_LONG).show()
//            }
//        })
    }
//    fun setUp(){
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
//        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
//        binding.lifecycleOwner = this
//        binding.loginViewModel = viewmodel
//    }
//    fun gotoProfile(){
//        var intent = Intent(this@LoginActivity, ListRestaurantActivity::class.java)
//        var user = Account("",binding.edtEmail.text.toString(),binding.edtPassword.text.toString(),"")
//        intent.putExtra("user",user)
//        startActivity(intent)
//    }
//    fun gotoSignUp(){
//        var intent = Intent(this@LoginActivity, SignupActivity::class.java)
//        startActivity(intent)
//        finish()
//    }

//    override fun onStop() {
//        super.onStop()
//        viewmodel.clear()
//    }
}
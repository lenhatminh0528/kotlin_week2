package kotlinclass.leminh.kotlin_week2.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.databinding.FragmentSignupBinding
import kotlinclass.leminh.kotlin_week2.signin.LoginActivity
import kotlinclass.leminh.kotlin_week2.signin.LoginFragment

class SignupFragment: Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewmodel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(SignupViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        var view = inflater.inflate(R.layout.fragment_signup,container,false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false)
//        binding.lifecycleOwner = this
        binding.signupViewModel = viewmodel
        var view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSignin.setOnClickListener{
                goToSignIn()
            }
//            invalidateAll()
        }
        viewmodel.isSuccess.observe(requireActivity(), Observer {
            Log.d("TAG","go to signup isSuccess viewmodel")
            it?.let{
                if(it){
                    Log.d("TAG","go to signup viewmodel $it")
                    Toast.makeText(requireContext(),"Signup successful!", Toast.LENGTH_LONG).show()
                    goToSignIn()
                }
            }
        })
        viewmodel.isError.observe(requireActivity(), Observer {message->
            Log.d("TAG","go to signup isError viewmodel: $message")
            message?.let{
                Toast.makeText(requireActivity(),message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun goToSignIn(){
//        var intent = Intent(requireActivity(), LoginActivity::class.java)
//        startActivity(intent)
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.fg_content)
            addToBackStack(null)
        }
    }

    override fun onStop() {
        super.onStop()
        viewmodel.clear()
    }
}
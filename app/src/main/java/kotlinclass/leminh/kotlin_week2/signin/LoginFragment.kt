package kotlinclass.leminh.kotlin_week2.signin

import android.os.Bundle
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
import kotlinclass.leminh.kotlin_week2.databinding.FragmentSigninBinding
import kotlinclass.leminh.kotlin_week2.listrestaurant.ListFilmFramgment
import kotlinclass.leminh.kotlin_week2.signup.SignupFragment
import kotlinx.android.synthetic.main.fragment_signin.*

class LoginFragment : Fragment() {
    lateinit var binding: FragmentSigninBinding
    lateinit var viewmodel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin,container,false)
        binding.loginViewModel = viewmodel
        var view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btn_signup.setOnClickListener{
                gotoSignUp()
            }
        }
        viewmodel.isSuccess.observe(requireActivity(), Observer {
            it?.let{
                if(it){
                    Toast.makeText(requireContext(), " Sign in successful!", Toast.LENGTH_LONG).show()
                    gotoProfile()
                }
            }
        })
        viewmodel.isError.observe(requireActivity(), Observer { message ->
            message?.let{
                Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun gotoProfile(){
//        var intent = Intent(requireActivity(), ListRestaurantActivity::class.java)
//        var user = Account("",binding.edtEmail.text.toString(),binding.edtPassword.text.toString(),"")
//        intent.putExtra("user",user)
//        startActivity(intent)
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ListFilmFramgment>(R.id.fg_content)
//            addToBackStack(null)
        }
    }
    fun gotoSignUp(){
//        var intent = Intent(requireActivity(), SignupActivity::class.java)
//        startActivity(intent)
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SignupFragment>(R.id.fg_content)
            addToBackStack(null)
        }
    }

}
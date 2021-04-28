package kotlinclass.leminh.kotlin_week2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import kotlinclass.leminh.kotlin_week2.signin.LoginFragment
import kotlinclass.leminh.kotlin_week2.signup.SignupActivity
import kotlinclass.leminh.kotlin_week2.signup.SignupFragment
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeFragment : Fragment() {
    lateinit var btn_start : LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.activity_welcome,container,false)
        btn_start = view.findViewById(R.id.btn_start)
        return view
    }

    override fun onResume() {
        super.onResume()
        btn_start.setOnClickListener {
//            var intent = Intent(requireActivity(),SignupActivity::class.java)
//            startActivity(intent)
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<SignupFragment>(R.id.fg_content)
                addToBackStack(null)
            }
        }
    }
}
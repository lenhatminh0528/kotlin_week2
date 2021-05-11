package kotlinclass.leminh.kotlin_week2.OnBoarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import kotlinclass.leminh.kotlin_week2.R
import kotlinx.android.synthetic.main.activity_onboarding_two.*

class OnBoardingTwoFragment : Fragment() {
    lateinit var btn_next: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.activity_onboarding_two,container,false)
        btn_next = view.findViewById(R.id.btn_next)
        return view
    }

    override fun onResume() {
        super.onResume()
        btn_next.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                replace<OnBoardingThreeFragment>(R.id.fg_content)
                addToBackStack(null)
            }
        }
    }
}
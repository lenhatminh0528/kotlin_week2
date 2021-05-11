package kotlinclass.leminh.kotlin_week2.OnBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.listrestaurant.ListFilmFramgment

class OnBoardingThreeFragment : Fragment() {
    lateinit var btn_next: LinearLayout
//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putInt("currentFragment",3)
//        super.onSaveInstanceState(outState)
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.activity_onboarding_three,container,false)
        btn_next = view.findViewById(R.id.btn_next)
        return view
    }

    override fun onResume() {
        super.onResume()
        btn_next.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                replace<ListFilmFramgment>(R.id.fg_content)
                addToBackStack(null)
            }
        }
    }
}
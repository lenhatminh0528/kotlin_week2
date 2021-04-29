package kotlinclass.leminh.kotlin_week2.OnBoarding

import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import kotlinclass.leminh.kotlin_week2.R


var ONBOARDING_3 = 3
class OnBoardingActivity : AppCompatActivity(){
    var currentFragment : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<OnBoardingOneFragment>(R.id.fg_content)

            }
        }
//        else{
//            currentFragment = savedInstanceState.getInt("currentFragment")
//            when(currentFragment){
//                ONBOARDING_3 ->
//                    supportFragmentManager.commit {
//                        setReorderingAllowed(true)
//                        add<OnBoardingThreeFragment>(R.id.fg_content)
//                    }
//            }
//        }
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            add<OnBoardingOneFragment>(R.id.fg_content)
////
//        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//    }
}
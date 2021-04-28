package kotlinclass.leminh.kotlin_week2.OnBoarding

import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import kotlinclass.leminh.kotlin_week2.R



class OnBoardingActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<OnBoardingOneFragment>(R.id.fg_content)

        }
    }
}
package kotlinclass.leminh.kotlin_week2

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinclass.leminh.kotlin_week2.fragment.GridFragment
import kotlinclass.leminh.kotlin_week2.fragment.LinearFragment
import kotlinclass.leminh.kotlin_week2.fragment.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_list_restaurant.*

class ListRestaurantActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_restaurant)
        setUpTabs()

    }
    fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(GridFragment(),"Grid")
        adapter.addFragment(LinearFragment(),"Linear")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
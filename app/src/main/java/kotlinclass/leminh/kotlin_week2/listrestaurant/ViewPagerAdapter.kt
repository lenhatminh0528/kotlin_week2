package kotlinclass.leminh.kotlin_week2.listrestaurant

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(context: Context, supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mFragmentList = ArrayList<Fragment>()
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }
    fun addFragment(fragment: Fragment){
        mFragmentList.add(fragment)
    }
}
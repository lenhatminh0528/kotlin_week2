package kotlinclass.leminh.kotlin_week2.listrestaurant

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinclass.leminh.kotlin_week2.R

class ListRestaurantFramgment: Fragment() {
//    lateinit var mAdapter : ViewPagerAdapter
//    lateinit var viewpager: ViewPager
    lateinit var navigateview : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_list_restaurant,container,false)
        navigateview = view.findViewById(R.id.navigationView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeFragment(TopFragment())
        navigateview.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.navigation_top ->{
                    changeFragment(TopFragment())
                }
                R.id.navigation_favorite -> {
                    changeFragment(FavoriteFragment())
                }
            }
            true
        }
    }
    fun changeFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame,fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}
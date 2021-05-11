package kotlinclass.leminh.kotlin_week2.listrestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinclass.leminh.kotlin_week2.R

class ListFilmFramgment: Fragment() {
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
        changeFragment(NowPlayingFragment())
        navigateview.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.navigation_top ->{
                    changeFragment(NowPlayingFragment())
                }
                R.id.navigation_favorite -> {
                    changeFragment(TopRateFragment())
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
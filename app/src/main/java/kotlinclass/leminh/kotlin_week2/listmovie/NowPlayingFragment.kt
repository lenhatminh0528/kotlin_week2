package kotlinclass.leminh.kotlin_week2.listmovie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.movie.Movie

class NowPlayingFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var madapter : MovieAdapter
    lateinit var viewmodel : NowPlayingViewModel
    lateinit var btnmore : LinearLayout
    private var data : MutableList<Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_now_playing,container,false)
        recyclerView = view.findViewById(R.id.recyclerview)
        btnmore = view.findViewById(R.id.btn_more)
        setupRecyclerView()
        fetchData()
        btnmore.setOnClickListener {
            val popupMenu = PopupMenu(requireActivity(),btnmore)
            popupMenu.menuInflater.inflate(R.menu.menu_restaurant,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.linear -> {
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        madapter.toggle(true)
                    }
                    R.id.grid -> {
                        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                        madapter.toggle(false)
                    }
                }
                true
            }
            popupMenu.show()
        }
        return view
    }

    fun setupRecyclerView(){
        var mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        madapter = MovieAdapter(data)
        recyclerView.apply{
            layoutManager = mLayoutManager
            adapter = madapter
        }
    }

    fun fetchData(){
        viewmodel.getNowPlaying().observe(viewLifecycleOwner,Observer{movieList->
            Log.d("NowPlayingFragment: ", movieList.size.toString())
            Log.d("NowPlayingFragment: ", movieList[0].title.toString())
            data.clear()
            data.addAll(movieList)
            madapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        madapter.setOnClickToFavoriteListener(object: MovieAdapter.onClickToFavoriteListener{
            override fun onClick(movie: Movie) {
                val bundle = Bundle()
                bundle.putParcelable("detailMovie",movie)
                val detailMovieFragment = DetailMovieFragment()
                detailMovieFragment.arguments = bundle
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    replace(R.id.fg_content,detailMovieFragment)
                    addToBackStack(null)
                }
            }
        })
    }

}
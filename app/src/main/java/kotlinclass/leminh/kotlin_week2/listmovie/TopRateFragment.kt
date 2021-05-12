package kotlinclass.leminh.kotlin_week2.listmovie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.data.DataStore
import kotlinclass.leminh.kotlin_week2.movie.Movie

class TopRateFragment: Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var btnmore : LinearLayout
    lateinit private var viewModel : TopRateViewModel
    private var data : MutableList<Movie> = arrayListOf()
    lateinit var madapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopRateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_toprate,container,false)
        recyclerView = view.findViewById(R.id.recyclerview)
        btnmore = view.findViewById(R.id.btn_more)
        setupRecycleView()
        btnmore.setOnClickListener {
            var popupMenu = PopupMenu(requireActivity(),btnmore)
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

    fun setupRecycleView(){
            madapter = MovieAdapter(data)
            var mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
            recyclerView.apply {
                adapter = madapter
                layoutManager = mLayoutManager
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopRate().observe(viewLifecycleOwner, Observer {
            data.clear()
            data.addAll(it)
            madapter.notifyDataSetChanged()
        })
        madapter.setOnClickToFavoriteListener(object: MovieAdapter.onClickToFavoriteListener{
            override fun onClick(movie: Movie) {
                var bundle = Bundle()
                bundle.putParcelable("detailMovie",movie)
                var detailMovieFragment = DetailMovieFragment()
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
package kotlinclass.leminh.kotlin_week2.listmovie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.movie.Movie
import kotlinx.android.synthetic.main.fragment_detail_movie.view.*

class DetailMovieFragment: Fragment() {
    private var movie = Movie()
    lateinit var title : TextView
    lateinit var overview : TextView
    lateinit var imagePath : CircleImageView
    lateinit var voteAverage: TextView
    lateinit var id : TextView
    lateinit var popularity: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable<Movie>("detailMovie")!!
        Log.d("DetailMovieFragment","bundle: ${movie.title}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_detail_movie,container,false)
        title = view.findViewById(R.id.title)
        overview = view.findViewById(R.id.overview)
        imagePath = view.findViewById(R.id.image)
        voteAverage = view.findViewById(R.id.voteaverage)
        id = view.findViewById(R.id.id)
        popularity = view.findViewById(R.id.popularity)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInitData()

    }
    fun getInitData(){
        title.text= movie.title
        overview.text= movie.overview
        voteAverage.text= movie.voteAverage.toString()
        id.text= movie.id.toString()
        popularity.text= movie.popularity.toString()

        Glide.with(this)
            .load(movie.getImagePath())
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(imagePath)
    }
}
package kotlinclass.leminh.kotlin_week2.listmovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.movie.Movie

class MovieAdapter(private var list: List<Movie>):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var listMovie: List<Movie>
    private var isLinear: Boolean = true

    init {
        listMovie = list
    }

    interface onClickToFavoriteListener{
        fun onClick(movie: Movie)
    }
    private lateinit var listener : onClickToFavoriteListener

    fun setOnClickToFavoriteListener(onClickToFavoriteListener: onClickToFavoriteListener){
        listener = onClickToFavoriteListener
    }

    fun toggle(value: Boolean){
        isLinear = value
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView
        var tv_overview: TextView
        var image: CircleImageView
        var btn_item: LinearLayout
        init{
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_overview = itemView.findViewById(R.id.tv_overview)
            image = itemView.findViewById(R.id.image)
            btn_item = itemView.findViewById(R.id.btn_item)
        }

        fun bind(item: Movie){
            tv_title.text = item.title
            tv_overview.text =  item.overview
            Glide.with(itemView.context)
                .load(item.getImagePath())
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View
        if (viewType == 1){
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_linear_layout_restaurant,parent,false)
        }else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_layout_restaurant,parent,false)
        }
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        if(isLinear) return 1
        else return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovie[position]
        holder.bind(item)
        holder.btn_item.setOnClickListener {
            listener.onClick(listMovie[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
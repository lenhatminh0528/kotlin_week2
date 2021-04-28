package kotlinclass.leminh.kotlin_week2.listrestaurant

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.Restaurant
import kotlinclass.leminh.kotlin_week2.data.DataStore
import kotlinx.android.synthetic.main.item_linear_layout_restaurant.*
import java.util.*

class FavoriteFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit private var viewModel : FavoriteViewModel
    lateinit var madapter: RestaurantAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_favorite,container,false)
        recyclerView = view.findViewById(R.id.recyclerview)
        viewModel.favoriteList.value = DataStore.instance.favoriteList
        viewModel.favoriteList.value?.let {
//            Log.d("TAG","favoriteList has value ${viewModel.favoriteList.value!!.get(0)}")
            madapter = RestaurantAdapter(requireContext(), viewModel.favoriteList.value!!)
            var mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
            recyclerView.apply {
                adapter = madapter
                layoutManager = mLayoutManager
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.favoriteList.observe(requireActivity(), androidx.lifecycle.Observer {
            madapter.notifyDataSetChanged()
        })
        viewModel.listFavoriteCheck.observe(requireActivity(), androidx.lifecycle.Observer {
        })
    }
    class RestaurantAdapter(private var context: Context, private var list: List<Restaurant>):
        RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tv_name: TextView
            var tv_address: TextView
            var image: CircleImageView
            var ic_favorite: ImageView
            init{
                tv_name = itemView.findViewById(R.id.tv_name)
                tv_address = itemView.findViewById(R.id.tv_address)
                image = itemView.findViewById(R.id.image)
                ic_favorite = itemView.findViewById(R.id.ic_favorite)
                ic_favorite.visibility = View.GONE
            }

            fun bind(item: Restaurant){
                tv_address.text = item.address
                tv_name.text =  item.name
                Glide.with(itemView.context)
                    .load(item.avatar)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .into(image)
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_linear_layout_restaurant,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.bind(item)


        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
}
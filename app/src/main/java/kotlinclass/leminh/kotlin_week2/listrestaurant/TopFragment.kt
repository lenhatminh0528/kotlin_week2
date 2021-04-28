package kotlinclass.leminh.kotlin_week2.listrestaurant

import android.content.Context
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
import com.bumptech.glide.load.engine.Resource
import de.hdodenhof.circleimageview.CircleImageView
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.Restaurant
import kotlinclass.leminh.kotlin_week2.data.DataStore
import java.util.*

class TopFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var madapter : RestaurantAdapter
    lateinit var viewmodel : FavoriteViewModel
    private val data = DataStore.instance.getData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_top,container,false)
        recyclerView = view.findViewById(R.id.recyclerview)
        viewmodel.listFavoriteCheck.value = DataStore.instance.checkRestaurantList
        var mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        madapter = RestaurantAdapter(requireActivity(),data,viewmodel.listFavoriteCheck.value!!)
        recyclerView.apply{
            layoutManager = mLayoutManager
            adapter = madapter
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        madapter.setOnClickToFavoriteListener(object: RestaurantAdapter.onClickToFavoriteListener{
            override fun onClick(position: Int, value: Boolean) {
                Log.d("TopFragment","pos: $position, value: $value")
                viewmodel.checkList(position,value)
                if(value){
                    viewmodel.addToList(data[position])
                }else{
                    viewmodel.removeItem(data[position])
                }
                madapter.notifyDataSetChanged()
            }
        })
    }
    class RestaurantAdapter(private var context: Context, private var list: List<Restaurant>,private var checkList: BooleanArray):
        RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
        interface onClickToFavoriteListener{
            fun onClick(position: Int,value: Boolean)
        }
        private lateinit var listener : onClickToFavoriteListener
        fun setOnClickToFavoriteListener(onClickToFavoriteListener: onClickToFavoriteListener){
            listener = onClickToFavoriteListener
        }
        private var favoriteList =  checkList
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
            if(favoriteList[position]){
                holder.ic_favorite.setImageResource(R.drawable.ic_favorite_choosed)
            }else{
                holder.ic_favorite.setImageResource(R.drawable.ic_baseline_favorite_normal)
            }
            holder.ic_favorite.setOnClickListener{
                favoriteList[position] = !favoriteList[position]
                listener.onClick(position,favoriteList[position])
            }
            holder.bind(item)


        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
}
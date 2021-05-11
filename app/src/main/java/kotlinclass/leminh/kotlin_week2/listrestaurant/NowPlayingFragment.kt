package kotlinclass.leminh.kotlin_week2.listrestaurant

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.Restaurant
import kotlinclass.leminh.kotlin_week2.data.DataStore
import kotlinx.android.synthetic.main.activity_list_restaurant.*

class NowPlayingFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var madapter : RestaurantAdapter
    lateinit var viewmodel : TopRateViewModel
    lateinit var btnmore : LinearLayout
    private val data = DataStore.instance.getData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(TopRateViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_now_playing,container,false)
        recyclerView = view.findViewById(R.id.recyclerview)
        btnmore = view.findViewById(R.id.btn_more)
        viewmodel.listFavoriteCheck.value = DataStore.instance.checkRestaurantList

        var mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        madapter = RestaurantAdapter(requireActivity(),data,viewmodel.listFavoriteCheck.value!!)
        recyclerView.apply{
            layoutManager = mLayoutManager
            adapter = madapter
        }
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
        private var isLinear: Boolean = true
        interface onClickToFavoriteListener{
            fun onClick(position: Int,value: Boolean)
        }
        private lateinit var listener : onClickToFavoriteListener
        fun setOnClickToFavoriteListener(onClickToFavoriteListener: onClickToFavoriteListener){
            listener = onClickToFavoriteListener
        }
        fun toggle(value: Boolean){
            isLinear = value
        }
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tv_name: TextView
            var tv_address: TextView
            var image: CircleImageView
            init{
                tv_name = itemView.findViewById(R.id.tv_name)
                tv_address = itemView.findViewById(R.id.tv_address)
                image = itemView.findViewById(R.id.image)
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
            val item = list[position]
            holder.bind(item)


        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
}
package kotlinclass.leminh.kotlin_week2.listrestaurant

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.Restaurant
import kotlinclass.leminh.kotlin_week2.data.DataStore

class TopRateFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var btnmore : LinearLayout
    lateinit private var viewModel : TopRateViewModel
    lateinit var madapter: TopRateAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopRateViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_favorite,container,false)
        recyclerView = view.findViewById(R.id.recyclerview)
        btnmore = view.findViewById(R.id.btn_more)
        setupRecycleView()
        btnmore.setOnClickListener {
            var popupMenu = PopupMenu(requireActivity(),btnmore)
            popupMenu.menuInflater.inflate(R.menu.menu_restaurant,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.linear ->
                        Toast.makeText(requireContext(),"CC", Toast.LENGTH_LONG).show()
                    R.id.grid ->
                        Toast.makeText(requireContext(),"CC", Toast.LENGTH_LONG).show()
                }
                true
            }
            popupMenu.show()
        }
        return view
    }
    fun setupRecycleView(){
        viewModel.favoriteList.value = DataStore.instance.favoriteList
        viewModel.favoriteList.value?.let {
            madapter = TopRateAdapter(requireContext(), viewModel.favoriteList.value!!)
            var mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
            recyclerView.apply {
                adapter = madapter
                layoutManager = mLayoutManager
            }
        }
    }

    class TopRateAdapter(private var context: Context, private var list: List<Restaurant>):
        RecyclerView.Adapter<TopRateAdapter.ViewHolder>() {
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
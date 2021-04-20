package kotlinclass.leminh.kotlin_week2.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinclass.leminh.kotlin_week2.R
import kotlinclass.leminh.kotlin_week2.Restaurant
import kotlinclass.leminh.kotlin_week2.data.DataStore
import kotlinx.android.synthetic.main.fragment_linear.*

class LinearFragment: Fragment() {
    private val data = DataStore.instance.getData()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RestaurantAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var view: View = inflater.inflate(R.layout.fragment_linear,container,false)

        recyclerView = view.findViewById(R.id.recyclerview)
        var mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        adapter = RestaurantAdapter(requireActivity(),data)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = adapter
        return view

    }

    class RestaurantAdapter(private var context: Context, private var list: List<Restaurant>):
        RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tv_name: TextView
            var tv_address: TextView
            var image: CircleImageView
            init{
                tv_name = itemView.findViewById(R.id.tv_name)
                tv_address = itemView.findViewById(R.id.tv_address)
                image = itemView.findViewById(R.id.image)
            }

            fun bind(item:Restaurant){
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
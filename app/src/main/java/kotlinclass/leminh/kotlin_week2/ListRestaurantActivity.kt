package kotlinclass.leminh.kotlin_week2

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import de.hdodenhof.circleimageview.CircleImageView
import kotlinclass.leminh.kotlin_week2.data.DataStore
import kotlinclass.leminh.kotlin_week2.fragment.GridFragment
import kotlinclass.leminh.kotlin_week2.fragment.LinearFragment
import kotlinclass.leminh.kotlin_week2.fragment.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_list_restaurant.*

class ListRestaurantActivity : AppCompatActivity(){
    private val data = DataStore.instance.getData()
    lateinit var restaurantAdapter: RestaurantAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_restaurant)
        setSupportActionBar(toolbar)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        setUpTabs()
        setRecyclerView()

    }

    fun setRecyclerView(){
        var linearLayoutManager  = LinearLayoutManager(this)
        restaurantAdapter = RestaurantAdapter(this,data)
        recyclerview.apply {
            adapter = restaurantAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_restaurant,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.grid -> {
                restaurantAdapter.toggle(false)
                recyclerview.layoutManager = GridLayoutManager(this,2)
//                restaurantAdapter.notifyDataSetChanged()
                return true
            }
            R.id.linear -> {
                restaurantAdapter.toggle(true)

                recyclerview.layoutManager = LinearLayoutManager(this)
//                restaurantAdapter.notifyDataSetChanged()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
//    fun setUpTabs(){
//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(GridFragment(),"Grid")
//        adapter.addFragment(LinearFragment(),"Linear")
//        viewPager.adapter = adapter
//        tabs.setupWithViewPager(viewPager)
//    }
class RestaurantAdapter(private var context: Context, private var list: List<Restaurant>):
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    private var isLinear: Boolean = true
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

    fun toggle(value: Boolean){
        isLinear = value
    }


    override fun getItemViewType(position: Int): Int {
        if(isLinear){
            return 1
        } else return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View
        if(viewType == 1){
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_linear_layout_restaurant,parent,false)
        }else
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_layout_restaurant,parent,false)
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
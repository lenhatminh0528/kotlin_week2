package kotlinclass.leminh.kotlin_week2.listrestaurant

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinclass.leminh.kotlin_week2.Account
import kotlinclass.leminh.kotlin_week2.Restaurant
import kotlinclass.leminh.kotlin_week2.data.DataStore
import java.util.*
import kotlin.collections.ArrayList

class TopRateViewModel: ViewModel() {
    var favoriteList =  MutableLiveData<ArrayList<Restaurant>>()
    var listFavoriteCheck = MutableLiveData<BooleanArray>()
    private var dataStore = DataStore.instance
    init {
        listFavoriteCheck.value = dataStore.checkRestaurantList
        listFavoriteCheck.postValue(listFavoriteCheck.value)
    }
    fun addToList(res: Restaurant){
        dataStore.addToFavorite(res)
        favoriteList.value?.add(res)
        favoriteList.postValue(favoriteList.value)
    }
    fun removeItem(res: Restaurant){
        dataStore.removeFavorite(res)
        favoriteList.value?.remove(res)
        favoriteList.postValue(favoriteList.value)
    }
    fun checkList(pos: Int, value: Boolean){
        dataStore.updateCheckList(pos,value)
        listFavoriteCheck.value?.set(pos,value)
        listFavoriteCheck.postValue(listFavoriteCheck.value)
    }

}
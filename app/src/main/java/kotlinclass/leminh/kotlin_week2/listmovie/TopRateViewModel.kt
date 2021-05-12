package kotlinclass.leminh.kotlin_week2.listmovie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinclass.leminh.kotlin_week2.movie.Movie
import kotlinclass.leminh.kotlin_week2.movie.RestClient
import kotlinclass.leminh.kotlin_week2.movie.TopRateResp
import kotlinx.coroutines.launch
import java.lang.Exception

class TopRateViewModel: ViewModel() {
    private var topRateResp = MutableLiveData<List<Movie>>()

    fun getTopRate(): MutableLiveData<List<Movie>>{
        viewModelScope.launch {
            try{
                var mTopRateResp = RestClient.getInstance().API.listTopRateMovies(
                    language = "en-US",
                    page = 1
                )
                mTopRateResp.let {
                    topRateResp.value = it.results!!
                }
            }catch (ex: Exception){
                Log.d("TAG","exception: ${ex.message}")
            }
        }
        return topRateResp
    }
}
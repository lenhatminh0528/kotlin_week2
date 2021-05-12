package kotlinclass.leminh.kotlin_week2.listmovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinclass.leminh.kotlin_week2.movie.Movie
import kotlinclass.leminh.kotlin_week2.movie.RestClient
import kotlinx.coroutines.launch

class NowPlayingViewModel: ViewModel() {
    private val nowPlayingListResponse = MutableLiveData<List<Movie>>()

    fun getNowPlaying() : LiveData<List<Movie>> {
        Log.d("TAG","goto getnowplaying")
        viewModelScope.launch {
            Log.d("TAG","goto getnowplaying1")
            try{
                val movieResp = RestClient.getInstance().API.listNowPlayingMovies(
                    language = "en-US",
                    page = 1
                )
                movieResp?.let {
                    nowPlayingListResponse.value = it.results!!
                }
            }catch (ex: Exception){
                Log.d("TAG","exception: ${ex.message}")
            }

        }
        return nowPlayingListResponse
    }

}
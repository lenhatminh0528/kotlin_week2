package kotlinclass.leminh.kotlin_week2.rest

import kotlinclass.leminh.kotlin_week2.movie.NowPlayingResp
import kotlinclass.leminh.kotlin_week2.movie.TopRateResp
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {

    @GET("movie/now_playing")
    suspend fun listNowPlayingMovies(@Query("language") language: String, @Query("page") page: Int
    ): NowPlayingResp

    @GET("movie/top_rated")
    suspend fun listTopRateMovies(@Query("language") language: String, @Query("page") page: Int
    ): TopRateResp
}
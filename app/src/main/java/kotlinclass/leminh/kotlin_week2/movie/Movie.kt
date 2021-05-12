package kotlinclass.leminh.kotlin_week2.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Movie (
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")    //cho giong ten voi JSON key
    val backdropPath: String? = null,

    @SerializedName("genre_ids")
    val genreIDS: List<Long>? = null,
    val id: Long? = null,

    @SerializedName("original_language")
    val originalLanguage: OriginalLanguage? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("vote_count")
    val voteCount: Long? = null
): Parcelable
{
    fun getImagePath(): String{
        return "https://image.tmdb.org/t/p/w300$posterPath"
    }
}


enum class OriginalLanguage {
    En,
    Es,
    Ko,
    Ru
}
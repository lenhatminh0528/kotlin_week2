package kotlinclass.leminh.kotlin_week2.movie

data class TopRateResp (
    val page: Long? = null,
    val results: List<Movie>? = null,
    val totalPages: Long? = null,
    val totalResults: Long? = null
)
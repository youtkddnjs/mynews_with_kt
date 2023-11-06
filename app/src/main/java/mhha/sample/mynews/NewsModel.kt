package mhha.sample.mynews

data class NewsModel (
    val title: String,
    val link: String,
    var imageUrl: String? = null
)

fun List<NewsItem>.transfrom() : List<NewsModel> {
    return this.map {
        NewsModel(
            title = it.title ?: "",
            link = it.link ?: "",
            imageUrl = null
        ) //NewsModel
    }//return this.map
} //fun List<NewsItem>.transfrom() : List<NewsModel>
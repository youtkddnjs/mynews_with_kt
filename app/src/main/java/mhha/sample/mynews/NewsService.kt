package mhha.sample.mynews

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/rss?hl=ko&gl=KR&ceid=KR:ko")
    fun getMainNews(): Call<Feed>

    //topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRFZ4ZERBU0FtdHZLQUFQAQ?hl=ko&gl=KR&ceid=KR%3Ako
    @GET("/rss/search?q=정치&hl=ko&gl=KR&ceid=KR:ko")
    fun getpolisticsNews(): Call<Feed>

    //topics/CAAqIggKIhxDQkFTRHdvSkwyMHZNR2RtY0hNekVnSnJieWdBUAE?hl=ko&gl=KR&ceid=KR%3Ako
    @GET("/rss/search?q=경제&hl=ko&gl=KR&ceid=KR:ko")
    fun geteconomyNews(): Call<Feed>

    //topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRGs0ZDNJU0FtdHZLQUFQAQ?hl=ko&gl=KR&ceid=KR%3Ako
    @GET("/rss/search?q=사회&hl=ko&gl=KR&ceid=KR:ko")
    fun getsocietyNews(): Call<Feed>

    //topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRE41ZEdNU0FtdHZLQUFQAQ?hl=ko&gl=KR&ceid=KR%3Ako
    @GET("/rss/search?q=it&hl=ko&gl=KR&ceid=KR:ko")
    fun getitNews(): Call<Feed>

    //topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRFp1ZEdvU0FtdHZHZ0pMVWlnQVAB?hl=ko&gl=KR&ceid=KR%3Ako
    @GET("/rss/search?q=스포츠&hl=ko&gl=KR&ceid=KR:ko")
    fun getsportNews(): Call<Feed>

    @GET("/rss/search?&hl=ko&gl=KR&ceid=KR:ko")
    fun search(@Query("q") query: String): Call<Feed>

}
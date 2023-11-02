package mhha.sample.mynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import mhha.sample.mynews.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://news.google.com/")
        .addConverterFactory(
            TikXmlConverterFactory.create(
                TikXml.Builder()
                    //Custom으로 만들기위한 코드
                    .exceptionOnUnreadXml(false)
                    .build()
            )
        )
        .build()

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter()


        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = newsAdapter
        }



        val newsService = retrofit.create(NewsService::class.java)
        newsService.getMainNews().enqueue(object: Callback<Feed>{
            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                Log.e("mainactivity", "${response.body()?.channel?.items}")

                newsAdapter.submitList(response.body()?.channel?.items.orEmpty())

            }

            override fun onFailure(call: Call<Feed>, t: Throwable) {
                Toast.makeText(this@MainActivity, "에러",Toast.LENGTH_SHORT).show()
                Log.e("mainactivity", "에러")
                t.printStackTrace() // 에러 표시하는 코드
            }
        }) //newsService.getMainNews().enqueue(object: Callback<Feed>{


    }//override fun onCreate(savedInstanceState: Bundle?)
}//class MainActivity : AppCompatActivity()
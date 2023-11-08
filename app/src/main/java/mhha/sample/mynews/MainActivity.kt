package mhha.sample.mynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import mhha.sample.mynews.databinding.ActivityMainBinding
import org.jsoup.Jsoup
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
        val newsService = retrofit.create(NewsService::class.java)
        //newsService.getMainNews().getNuws()

        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = newsAdapter
        } //binding.newsRecyclerView.apply

        binding.feedChip.setOnClickListener {
            if( it is ChipGroup) it.clearCheck()
            if( it is Chip) it.isChecked = true

        } //binding.feedChip.setOnClickListener
        binding.politicsChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.politicsChip.isChecked = true
            newsService.getpolisticsNews().getNuws()
        } //binding.politicsChip.setOnClickListener

        binding.economyChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.economyChip.isChecked = true
            newsService.geteconomyNews().getNuws()
        } //binding.economyChip.setOnClickListener
        binding.societyChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.societyChip.isChecked = true
            newsService.getsocietyNews().getNuws()
        } //binding.societyChip.setOnClickListener
        binding.itChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.itChip.isChecked = true
            newsService.getitNews().getNuws()
        } //binding.itChip.setOnClickListener

        binding.sportChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.sportChip.isChecked = true
            newsService.getsportNews().getNuws()
        } //binding.sportChip.setOnClickListener



    }//override fun onCreate(savedInstanceState: Bundle?)

    private fun Call<Feed>.getNuws(){
        this.enqueue(object: Callback<Feed>{
            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                Log.e("mainactivity", "${response.body()?.channel?.items}")

                val list = response.body()?.channel?.items.orEmpty().transfrom()
                newsAdapter.submitList(list)

                list.forEachIndexed { index, newsModel ->
                    Thread{
                        try {
                            val jsoup = Jsoup.connect(newsModel.link).get()
                            val elements = jsoup.select("meta[property^=og:]")
                            val ogImageNode = elements.find { node ->
                                Log.e("mainactivity", "node : ${node}")
                                node.attr("property") == "og:image"
                            }
                            newsModel.imageUrl = ogImageNode?.attr("content")
                            Log.e("mainactivity", "images : ${newsModel.imageUrl}")
                        }catch (e : Exception){
                            e.printStackTrace()
                        }
                        runOnUiThread{
                            newsAdapter.notifyItemChanged(index)
                        }
                    }.start() //Thread
                }//list.forEach
            }//override fun onResponse(call: Call<Feed>, response: Response<Feed>)

            override fun onFailure(call: Call<Feed>, t: Throwable) {
                Toast.makeText(this@MainActivity, "에러",Toast.LENGTH_SHORT).show()
                Log.e("mainactivity", "에러")
                t.printStackTrace() // 에러 표시하는 코드
            }//override fun onFailure(call: Call<Feed>, t: Throwable)
        })//this.enqueue(object: Callback<Feed>
    }//private fun Call<Feed>.getNuws()

}//class MainActivity : AppCompatActivity()
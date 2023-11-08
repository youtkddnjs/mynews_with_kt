package mhha.sample.mynews

import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mhha.sample.mynews.databinding.ActivityWebviewBinding

class WebViewActivity: AppCompatActivity() {

    private lateinit var binding:ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = intent.getStringExtra("url")
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled=true

        if(url.isNullOrEmpty()){
            Toast.makeText(this,"잘못된 URL 입니다.", Toast.LENGTH_SHORT).show()
            finish()
        }else {
            binding.webView.loadUrl(url)
        }

    }//override fun onCreate(savedInstanceState: Bundle?)

}//class WebViewActivity: AppCompatActivity()
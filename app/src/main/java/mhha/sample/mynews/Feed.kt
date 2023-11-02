package mhha.sample.mynews

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="rss")
data class Feed(

    @Element(name="channel")
    val channel: FeedChannel

)//data class Feed

@Xml(name="channel")
data class FeedChannel(

    @PropertyElement(name="title")
    val title: String,

    @Element(name = "item")
    val items: List<NewsItem>? = null,
)//data class FeedChannel

@Xml(name="item")
data class NewsItem(

    @PropertyElement(name="title")
    val title: String? = null,
    @PropertyElement(name="link")
    val link: String? = null,

)//data clase NewsItem

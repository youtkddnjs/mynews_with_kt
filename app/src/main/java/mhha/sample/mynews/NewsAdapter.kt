package mhha.sample.mynews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mhha.sample.mynews.databinding.ItemNewsBinding

class NewsAdapter:ListAdapter<NewsItem,NewsAdapter.ViewHolder>(diffUtil){ //class NewsAdapter:ListAdapter<NewsItem,ViewHolder>(diffUtil)
    inner class ViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item:NewsItem){
            binding.titleTextViews.text = item.title
        }//fun bind(item:NewsItem)

    }//inner class ViewHolder(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )//ItemNewsBinding.inflate
        ) //return ViewHolder
    } //override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    } //override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<NewsItem>() {
            override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem == newItem
            }
        }
    }//object {
}
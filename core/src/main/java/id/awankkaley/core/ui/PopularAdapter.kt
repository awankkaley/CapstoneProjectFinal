package id.awankkaley.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.awankkaley.capstoneproject.util.imageViewGlide
import id.awankkaley.core.domain.model.Popular
import id.awankkaley.core.R
import id.awankkaley.core.databinding.ItemListBinding
import id.awankkaley.core.util.Util

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.ListViewHolder>() {

    private var listData = ArrayList<Popular>()
    var onItemClick: ((Popular) -> Unit)? = null


    fun setData(newListData: List<Popular>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: Popular) {
            with(binding) {
                imgPoster.imageViewGlide(data.posterPath)
                tvItemTitle.text = data.title
                tvItemDescription.text = Util.convertOnlyYear(data.releaseDate)
                tvItemRate.text = data.voteAverage.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
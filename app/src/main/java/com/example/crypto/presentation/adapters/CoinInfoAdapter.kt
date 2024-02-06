package com.example.crypto.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.R
import com.example.crypto.databinding.ItemCoinInfoBinding
import com.example.crypto.domain.entity.CoinInfoEntity
import com.squareup.picasso.Picasso

class CoinInfoAdapter :
    ListAdapter<CoinInfoEntity, CoinInfoAdapter.CoinInfoViewHolder>(DiffCallback)
{

    var onCoinClickListener: ((CoinInfoEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ItemCoinInfoBinding.inflate(inflater, parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinInfoEntity = getItem(position)

        with(holder) {
            val symbolsTemplate =
                itemView.context.resources.getString(R.string.symbols_template)

            val lastUpdateTemplate =
                itemView.context.resources.getString(R.string.last_update_template)

            with(binding) {
                tvSymbols.text = String.format(symbolsTemplate, coinInfoEntity.fromSymbol, coinInfoEntity.toSymbol)
                tvPrice.text = coinInfoEntity.price
                tvLastUpdate.text =
                    String.format(lastUpdateTemplate, coinInfoEntity.lastUpdate)
                Picasso.get()
                    .load(coinInfoEntity.imageUrl)
                    .into(ivLogoCoin)
            }

            itemView.setOnClickListener {
                onCoinClickListener?.invoke(coinInfoEntity)
            }
        }
    }

    class CoinInfoViewHolder(val binding: ItemCoinInfoBinding) :
        RecyclerView.ViewHolder(binding.root)

    object DiffCallback : DiffUtil.ItemCallback<CoinInfoEntity>() {
        override fun areItemsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
            return oldItem.fromSymbol == newItem.fromSymbol
        }

        override fun areContentsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
            return oldItem == newItem
        }
    }

}
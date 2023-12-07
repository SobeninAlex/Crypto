package com.example.crypto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.adapters.CoinInfoAdapter
import com.example.crypto.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        val adapter = CoinInfoAdapter(this)

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromSymbol)
                startActivity(intent)
            }
        }

        val rvCoinPriceList: RecyclerView = findViewById(R.id.rvCoinPriceList)
        rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })

    }

}
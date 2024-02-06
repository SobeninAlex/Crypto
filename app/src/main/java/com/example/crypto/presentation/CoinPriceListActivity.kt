package com.example.crypto.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.crypto.databinding.ActivityCoinPriceListBinding
import com.example.crypto.domain.entity.CoinInfoEntity
import com.example.crypto.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private val viewModel by viewModels<CoinViewModel>()

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val coinInfoAdapter by lazy {
        CoinInfoAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()
        viewModelObserver()
    }

    private fun viewModelObserver() {
        viewModel.coinInfoList.observe(this) {
            coinInfoAdapter.submitList(it)
        }
    }

    private fun initAdapter() {
        binding.rvCoinList.adapter = coinInfoAdapter
        binding.rvCoinList.itemAnimator == null

        coinInfoAdapter.onCoinClickListener = {
            CoinDetailActivity.newIntent(
                this@CoinPriceListActivity,
                fromSymbol = it.fromSymbol
            ).also { intent ->
                startActivity(intent)
            }
        }
    }

}
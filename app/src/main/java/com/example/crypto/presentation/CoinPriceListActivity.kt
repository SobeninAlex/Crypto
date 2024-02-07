package com.example.crypto.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.crypto.R
import com.example.crypto.databinding.ActivityCoinPriceListBinding
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

    private fun isOnePaneMode() =
        binding.coinDetailContainer == null


    private fun viewModelObserver() {
        viewModel.coinInfoList.observe(this) {
            coinInfoAdapter.submitList(it)
        }
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.coin_detail_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    private fun launchDetailActivity(fromSymbol: String) {
        CoinDetailActivity.newIntent(this, fromSymbol).also { intent ->
            startActivity(intent)
        }
    }

    private fun initAdapter() {
        binding.rvCoinList.adapter = coinInfoAdapter
        binding.rvCoinList.itemAnimator == null

        coinInfoAdapter.onCoinClickListener = {
            if (isOnePaneMode()) {
                launchDetailActivity(it.fromSymbol)
            } else {
                launchDetailFragment(it.fromSymbol)
            }

        }
    }

}
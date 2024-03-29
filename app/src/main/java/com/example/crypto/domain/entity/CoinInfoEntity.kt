package com.example.crypto.domain.entity


data class CoinInfoEntity (
    val price: String?,
    val lowDay: String?,
    val highDay: String?,
    val lastMarket: String?,
    val lastUpdate: String,
    val fromSymbol: String,
    val toSymbol: String?,
    val imageUrl: String
)
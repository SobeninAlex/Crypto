package com.example.crypto.data.mapper

import com.example.crypto.data.database.table.CoinInfoDbModel
import com.example.crypto.data.network.model.CoinInfoDto
import com.example.crypto.data.network.model.CoinInfoJsonContainerDto
import com.example.crypto.data.network.model.CoinNamesListDto
import com.example.crypto.domain.entity.CoinInfoEntity
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapCoinInfoDtoToCoinInfoDbModel(coinInfoDto: CoinInfoDto) =
        CoinInfoDbModel(
            fromSymbol = coinInfoDto.fromSymbol,
            price = coinInfoDto.price,
            lowDay = coinInfoDto.lowDay,
            highDay = coinInfoDto.highDay,
            lastMarket = coinInfoDto.lastMarket,
            lastUpdate = coinInfoDto.lastUpdate,
            toSymbol = coinInfoDto.toSymbol,
            imageUrl = BASE_IMAGE_URL + coinInfoDto.imageUrl
        )

    fun mapCoinInfoJsonContainerDtoToListCoinInfoDto(jsonContainerDto: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainerDto.json ?: return result

        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson()
                    .fromJson(currencyJson.getAsJsonObject(currencyKey), CoinInfoDto::class.java)
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapCoinNamesListDtoToString(coinNamesListDto: CoinNamesListDto): String {
        return coinNamesListDto.names?.map {
            it.coinName?.name
        }?.joinToString(",") ?: ""
    }

    fun mapCoinInfoDbModelToCoinInfoEntity(coinInfoDbModel: CoinInfoDbModel) =
        CoinInfoEntity(
            price = coinInfoDbModel.price,
            lowDay = coinInfoDbModel.lowDay,
            highDay = coinInfoDbModel.highDay,
            lastMarket = coinInfoDbModel.lastMarket,
            lastUpdate = convertTimestampToTime(coinInfoDbModel.lastUpdate),
            fromSymbol = coinInfoDbModel.fromSymbol,
            toSymbol = coinInfoDbModel.toSymbol,
            imageUrl = coinInfoDbModel.imageUrl
        )

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }

}
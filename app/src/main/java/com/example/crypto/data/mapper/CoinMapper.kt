package com.example.crypto.data.mapper

import com.example.crypto.data.database.table.CoinInfoDbModel
import com.example.crypto.data.network.model.CoinInfoDto
import com.example.crypto.data.network.model.CoinInfoJsonContainerDto
import com.example.crypto.data.network.model.CoinNamesListDto
import com.example.crypto.domain.entity.CoinInfoEntity
import com.google.gson.Gson

class CoinMapper {

    fun mapCoinInfoDtoToCoinInfoDbModel(coinInfoDto: CoinInfoDto) =
        CoinInfoDbModel(
            fromSymbol = coinInfoDto.fromSymbol,
            price = coinInfoDto.price,
            lowDay = coinInfoDto.lowDay,
            highDay = coinInfoDto.highDay,
            lastMarket = coinInfoDto.lastMarket,
            lastUpdate = coinInfoDto.lastUpdate,
            toSymbol = coinInfoDto.toSymbol,
            imageUrl = coinInfoDto.imageUrl
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
            lastUpdate = coinInfoDbModel.lastUpdate,
            fromSymbol = coinInfoDbModel.fromSymbol,
            toSymbol = coinInfoDbModel.toSymbol,
            imageUrl = coinInfoDbModel.imageUrl
        )

}
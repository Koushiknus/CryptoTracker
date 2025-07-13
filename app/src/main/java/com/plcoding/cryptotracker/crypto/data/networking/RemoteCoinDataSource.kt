package com.plcoding.cryptotracker.crypto.data.networking

import com.plcoding.cryptotracker.core.data.networking.constructUrl
import com.plcoding.cryptotracker.core.data.networking.safeCall
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result
import com.plcoding.cryptotracker.core.domain.util.map
import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinDataSource
import com.plcoding.cryptotracker.crypto.presentation.coin_list.components.MockCoinUtils
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.delay // Import for simulating network delay
import toCoin


class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {

    // Flag to toggle between real and mock data
    private val useMockData = true // Set to false to use real network call

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {

            // Simulate network delay
            delay(1000L) // Optional: Simulate a 1-second delay

            // --- Option 1: Return mock success data ---
        if(useMockData) {
            val mockCoins = MockCoinUtils.generateMockCoins(count = 20)
          // return Result.Error(NetworkError.NO_INTERNET)
            return Result.Success(mockCoins)
        }else{
            return safeCall<CoinResponseDto> {
                httpClient.get(
                    urlString = constructUrl("/assets")
                )
            }.map { response ->
                response.data.map { it.toCoin() }
        }


            // --- Option 2: Return a mock network error ---
            // return Result.Error(NetworkError.NO_INTERNET)
            // return Result.Error(NetworkError.SERVER_ERROR)
            // return Result.Error(NetworkError.SERIALIZATION)
            // return Result.Error(NetworkError.UNKNOWN)

        }
    }}


// Dummy toCoin extension if you don't have it, adjust based on your actual DTO
// fun SomeCoinDto.toCoin(): Coin {
//     return Coin(id = this.id ?: "", name = this.name ?: "", symbol = this.symbol ?: "", /* ... other fields */)
// }

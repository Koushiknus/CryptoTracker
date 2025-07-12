package com.plcoding.cryptotracker.crypto.presentation.coin_list.components


import com.plcoding.cryptotracker.crypto.domain.Coin
import kotlin.random.Random

object MockCoinUtils {

    fun generateMockCoins(count: Int = 20): List<Coin> {
        val baseCoin = Coin(
            id = "bitcoin", // Base ID, will be made unique
            rank = 0, // Will be incremented
            name = "Bitcoin", // Base name, will be made unique
            symbol = "BTC",
            marketCapUsd = 1200000000.00,
            priceUsd = 60000.00,
            changePercent24hr = 0.0
        )

        return (1..count).map { i ->
            val randomFactor = Random.nextDouble(-5.0, 5.0) // For slight variations
            baseCoin.copy(
                id = "${baseCoin.id}_$i", // Ensure unique ID
                rank = i,
                name = "${baseCoin.name} $i",
                symbol = "${baseCoin.symbol}",
                marketCapUsd = baseCoin.marketCapUsd * (1 + (i * 0.05)) + Random.nextDouble(0.0, 1000000.0), // Vary market cap
                priceUsd = baseCoin.priceUsd * (1 + (i * 0.01)) + Random.nextDouble(-500.0, 500.0), // Vary price
                changePercent24hr = String.format("%.2f", randomFactor + (i * 0.1)).toDouble() // Vary change
            )
        }
    }
}

// Example usage (e.g., in your RemoteCoinDataSource or a preview)
// val twentyMockCoins = MockCoinUtils.generateMockCoins()
// val fiveMockCoins = MockCoinUtils.generateMockCoins(count = 5)


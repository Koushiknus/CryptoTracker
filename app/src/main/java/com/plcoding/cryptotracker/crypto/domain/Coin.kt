package com.plcoding.cryptotracker.crypto.domain

/**
 * This is for UI Purpose to show it
 */
data class Coin(
    val id : String,
    val rank : Int,
    val name : String,
    val symbol : String,
    val marketCapUsd : Double,
    val priceUsd : Double ,
    val changePercent24hr : Double
)

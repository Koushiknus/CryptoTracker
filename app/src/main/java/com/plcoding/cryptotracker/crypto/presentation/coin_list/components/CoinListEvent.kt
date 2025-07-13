package com.plcoding.cryptotracker.crypto.presentation.coin_list.components

import com.plcoding.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val message : NetworkError) : CoinListEvent
}
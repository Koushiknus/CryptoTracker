package com.plcoding.cryptotracker.crypto.presentation.coin_list.components

import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class onCoinClick(val coinUI :CoinUi) : CoinListAction
    data object OnRefresh : CoinListAction
}
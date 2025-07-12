package com.plcoding.cryptotracker.crypto.presentation.coin_list.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptotracker.core.domain.util.onError
import com.plcoding.cryptotracker.core.domain.util.onSuccess
import com.plcoding.cryptotracker.crypto.domain.CoinDataSource
import com.plcoding.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    //Mutable - Change
    private val _state = MutableStateFlow(CoinListState())
    //Immutable

    val state = _state
        .onStart {
            loadCoins()
        }
        // Keep on executing flow until no more subscriber plus 5 sec
        //Useful when loading during screen configuration
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            //Default State
            CoinListState()
        )

    fun onAction(action: CoinListAction) {
        when (action) {
            CoinListAction.OnRefresh -> TODO()
            is CoinListAction.onCoinClick -> TODO()
        }
    }

    // When flow collection starts
    private fun loadCoins() {
        viewModelScope.launch {
            //Copies existing state

            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            coinDataSource.getCoins().onSuccess { coins ->
                _state.update { it.copy(isLoading = false, coins = coins.map { it.toCoinUi() }) }
            }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }

                }
        }
    }
}
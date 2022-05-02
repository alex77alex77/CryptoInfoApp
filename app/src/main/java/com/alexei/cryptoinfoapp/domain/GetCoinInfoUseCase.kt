package com.alexei.cryptoinfoapp.domain

class GetCoinInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(symbol: String) = repository.getCoinInfo(symbol)
}
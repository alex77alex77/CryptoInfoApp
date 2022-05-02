package com.alexei.cryptoinfoapp.domain

class GetCoinInfoListUseCase(private val repository: CoinRepository) {
    operator fun invoke() = repository.getCoinInfoList()
}
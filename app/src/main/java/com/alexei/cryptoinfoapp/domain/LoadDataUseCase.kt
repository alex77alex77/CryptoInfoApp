package com.alexei.cryptoinfoapp.domain

class LoadDataUseCase(private val repository: CoinRepository) {
     operator fun invoke() = repository.loadData()
}
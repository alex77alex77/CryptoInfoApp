package com.alexei.cryptoinfoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alexei.cryptoinfoapp.data.repository.CoinRepositoryImpl
import com.alexei.cryptoinfoapp.domain.GetCoinInfoListUseCase
import com.alexei.cryptoinfoapp.domain.GetCoinInfoUseCase
import com.alexei.cryptoinfoapp.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()//вызов как метод(invoke)

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }

}
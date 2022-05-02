package com.alexei.cryptoinfoapp.data.workers

import android.content.Context
import androidx.work.*
import com.alexei.cryptoinfoapp.data.mapper.CoinMapper
import com.example.cryptoapp.api.ApiFactory
import com.example.cryptoapp.database.AppDatabase
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
):CoroutineWorker(context,workerParameters) {

    private val coinInfoDao = AppDatabase.getInstance(context).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins =apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListDtoToString(topCoins)
                val gsonContainer =apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(gsonContainer)
                val dbModelList = coinInfoDtoList.map {
                    mapper.mapDtoToModel(it)
                }

                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {

            }
            delay(10000)
        }
    }

    companion object{
        const val NAME ="RefreshDataWorker"
       fun makeRequest():OneTimeWorkRequest{
           return OneTimeWorkRequestBuilder<RefreshDataWorker>().apply {
                   setConstraints(makeConstrains())
           }.build()
       }
        private fun makeConstrains():Constraints{
            return Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        }
    }
}
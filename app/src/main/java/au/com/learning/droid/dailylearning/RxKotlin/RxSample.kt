package au.com.learning.droid.dailylearning.RxKotlin

import android.util.Log
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class RxSample {
    fun getTimerObservable(): Observable<Long> = Observable.interval(2, TimeUnit.SECONDS)

    fun testSample() {
        Log.d("Priju","Testing sample")
        getTimerObservable()
                .sample(5, TimeUnit.SECONDS)
                .subscribe { Log.d("Priju", "Testing Sample $it") }
    }
}
package au.com.learning.droid.dailylearning.RxKotlin.transforming

import android.util.Log
import io.reactivex.rxkotlin.toObservable

class RxMap {
    fun listToMap() {

        listOf<Int>(10,23,34,56,25,67,43,22,44)
                .toObservable()
                .map { "Transforming Int to String $it" }
                .subscribe { item -> Log.d("RxMap","$item") }
    }
}
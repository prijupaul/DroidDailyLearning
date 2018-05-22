package au.com.learning.droid.dailylearning.RxKotlin

import android.util.Log
import io.reactivex.Maybe
import io.reactivex.rxkotlin.subscribeBy

class RxMaybe {
    fun maybeValue() {
        val maybeValue = Maybe.just(1)
        maybeValue.subscribeBy(
                onComplete = { Log.d("RxMaybe", "onComplete") },
                onSuccess = { Log.d("RxMaybe", "onSuccess $it") },
                onError = { Log.d("RxMaybe", "onError ${it.message}") }
        )
    }

    fun maybeEmpty() {
        val maybeEmpty = Maybe.empty<Int>()
        maybeEmpty.subscribeBy(
                onComplete = { println("Completed Empty") },
                onError = { println("Error $it") },
                onSuccess = { println("Completed with value $it") }
        )
    }
}
package au.com.learning.droid.dailylearning.RxKotlin

import android.util.Log
import io.reactivex.rxkotlin.toObservable

class RxDistinct {

    fun toDistinct() {
        listOf<Int>(1,2,3,4,4,5,4,6,6,7,7,7,8,20)
                .toObservable()
                .distinct()
                .subscribe { println(it) }
    }

    fun toDistinctUntilChanged() {
        listOf<Int>(1,2,3,4,4,5,4,6,6,7,7,7,8,20)
                .toObservable()
                .distinctUntilChanged()
                .subscribe { println(it) }
    }

    fun elementAt() {
        listOf<Int>(1,2,3,4,4,5,4,6,6,7,7,7,8,20)
                .toObservable()
                .elementAt(5)
                .doOnComplete { Log.d("RxDistinct","Complete") }
                .subscribe { println(it) }

    }
}
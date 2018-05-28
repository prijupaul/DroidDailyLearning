package au.com.learning.droid.dailylearning.RxKotlin.transforming

import android.util.Log
import io.reactivex.rxkotlin.toObservable

class RxScan {

    fun performScanOperator() {

        listOf<String>("First String", "Second String", "Third String", "Fourth String", "Fifth String")
                .toObservable()
                .scan { prev, curr -> "$prev  $curr" }
                .subscribe { Log.d("RxScan","$it") }

    }
}
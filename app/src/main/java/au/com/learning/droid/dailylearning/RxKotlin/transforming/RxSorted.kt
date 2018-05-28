package au.com.learning.droid.dailylearning.RxKotlin.transforming

import android.util.Log
import io.reactivex.rxkotlin.toObservable

class RxSorted {

    fun sortList() {
        val subscribe = listOf(HolderClass("Priju", 24, 1.3f),
                HolderClass("Jacob", 53, 44.3f),
                HolderClass("Paul", 23, 20.3f))
                .toObservable()
                .sorted { item1, item2 ->
                    if (item1.distance > item2.distance) 1 else -1
                }
                .subscribe {
                    Log.d("RxSorted", "${it.name} ${it.age} ${it.distance}")

                }
    }

    data class HolderClass(val name: String, val  age: Int, val distance: Float)
}
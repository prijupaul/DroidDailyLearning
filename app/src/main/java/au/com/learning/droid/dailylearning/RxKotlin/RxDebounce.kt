package au.com.learning.droid.dailylearning.RxKotlin

import io.reactivex.Observable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

class RxDebounce {

    private fun createObservable(): Observable<String> =
            Observable.create<String> {
                it.onNext("R")
                runBlocking { delay(100) }
                runBlocking { delay(100) }
                it.onNext("Re")
                it.onNext("Reac")
                runBlocking { delay(130) }
                it.onNext("Reactiv")
                runBlocking { delay(140) }
                it.onNext("Reactive")
                runBlocking { delay(250) }
                it.onNext("Reactive P")
                runBlocking { delay(130) }
                it.onNext("Reactive Pro")
                runBlocking { delay(100) }
                it.onNext("Reactive Progra")
                runBlocking { delay(100) }
                it.onNext("Reactive Programming")
                runBlocking { delay(300) }
                it.onNext("Reactive Programming in")
                runBlocking { delay(100) }
                it.onNext("Reactive Programming in Ko")
                runBlocking { delay(150) }
                it.onNext("Reactive Programming in Kotlin")
                runBlocking { delay(250) }
                it.onComplete()
            }

    fun testDebouce() {
        createObservable()
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribe {
                    println(it)
                }
    }
}
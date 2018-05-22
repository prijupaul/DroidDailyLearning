package au.com.learning.droid.dailylearning.RxKotlin

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class RxCombineLatest {

    private val observable: Observable<String> = Observable.create<String> {
        Thread {
            it.onNext("Emit 1")
            Thread.sleep(1000)
            it.onNext("Emit 2")
            it.onNext("Emit 3")
            Thread.sleep(2000)
            it.onNext("Emit 4")
            Thread.sleep(1000)
            it.onComplete()
        }.start()
    }

    private val observable2: Observable<String> = Observable.create<String> {
        Thread {
            it.onNext("Emit 1")
            it.onNext("Emit 2")
            it.onNext("Emit 3")
            it.onNext("Emit 4")
            it.onError(Exception("My Custom Exception"))
        }.start()

    }


    private val observer: Observer<String> = object : Observer<String> {
        override fun onComplete() {
            println("On Completed")
        }

        override fun onSubscribe(d: Disposable) {
            println("On onSubscribe")
        }

        override fun onNext(t: String) {
            println("On onNext $t")
        }

        override fun onError(e: Throwable) {
            println("On onError ${e.message}")
        }
    }

    fun linkObservables() {
        observable.subscribe(observer)
        observable2.subscribe(observer)
    }

    fun combineBoth() {
        RxJavaPlugins.setErrorHandler { it -> println("something nasty happened ${it.message}") }

        val d = Observables.combineLatest(observable, observable2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .doOnDispose { println("combineboth disposed") }
                .doFinally { println("combineboth finally") }
                .doOnComplete { println("combineboth oncomplete") }
                .subscribeBy(
                        onNext = { println("combineboth onNext $it") },
                        onError = { println("combineboth onError ${it.message}") },
                        onComplete = { println("combine both oncomplete") })
    }
}


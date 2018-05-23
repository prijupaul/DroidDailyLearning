package au.com.learning.droid.dailylearning.Delegates

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class LoginContractPresenter(context: Context) : LoginContract, LoadingContract by LoadContractImpl(context) {

    fun onButtonPress() {
        loginClick
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .switchMap { Observable.timer(4, TimeUnit.SECONDS) }
                .doOnSubscribe { showLoading() }
                .doOnComplete { hideLoading() }
                .subscribe()

    }

    override val loginClick: Observable<Int>
        get() = Observable.just(1) // Integrate with RxBinding
}
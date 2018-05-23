package au.com.learning.droid.dailylearning.Delegates

import io.reactivex.Observable

interface LoadingContract {
    fun showLoading()
    fun hideLoading()
}

interface LoginContract : LoadingContract {
    val loginClick: Observable<Int>
}


package au.com.learning.droid.dailylearning

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import au.com.learning.droid.dailylearning.RxKotlin.RxCombineLatest
import au.com.learning.droid.dailylearning.Preconditions.Preconditions
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private val name = PublishSubject.create<String>()
    private val age = PublishSubject.create<Int>()
    private val preconditions = Preconditions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUi()

        setupCombinedLatest()

        convertArrayToVar()

        RxCombineLatest().combineBoth()
    }

    val View.isVisible
        inline get() = visibility == View.VISIBLE

    private fun checkVisible() {
        if (buttonLogin.isVisible) {
            Toast.makeText(this, "I'm a button!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun convertArrayToVar() {
        val l = listOf<String>("A","B")
        val (a,b) = l
        Log.d("ConvertArrayToVar","$a, $b")
    }

    private fun setupCombinedLatest() {
        Observables.combineLatest(name,age) { n, a -> "$n - age:${a}" }
                .subscribe { Log.d("combineLatest",it) }
    }

    private fun triggerCombineLatest() {
        name.onNext("saito")
        age.onNext(24)
        testSampleOperator()
        name.onNext("yoshida")
        age.onNext(34)
        name.onNext("Priju")
        name.onNext("Jacob")
        name.onNext("Paul")
    }

    private fun testSampleOperator() {
        val obser = name.sample(1,TimeUnit.SECONDS)
        obser.subscribe { Log.d("Sample",it) }
    }

    @SuppressLint("CheckResult")
    private fun setupUi() {
        RxTextView.afterTextChangeEvents(editTextEmail)
                .skipInitialValue()
                .map {
                    emailWrapper.error = null
                    it.view().text.toString()
                }
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lengthGreaterThanSix)
                .compose(verifyEmailPattern)
                .compose(verifyDigitPattern)
                .compose(retryWhenError { emailWrapper.error = it.message })
                .subscribe()

        RxTextView.afterTextChangeEvents(editTextPassword)
                .skipInitialValue()
                .map { passwordWrapper.error = null
                    it.view().text.toString()
                }
                .debounce(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lengthGreaterThanSix)
                .compose(verifyPasswordPattern)
                .compose(retryWhenError { passwordWrapper.error = it.message })
                .subscribe()

        RxView.clicks(buttonLogin)
                .subscribe{ triggerCombineLatest() }
    }

    private inline fun retryWhenError(crossinline onError: (ex: Throwable) -> Unit): ObservableTransformer<String, String> = ObservableTransformer { observable ->
        observable.retryWhen { errors ->
            errors.flatMap {
                onError(it)
                Observable.just("")
            }
        }
    }

    private val verifyDigitPattern = ObservableTransformer<String,String> { observable ->
        observable.flatMap {
            Observable.just(it)
                    .map { it.trim()  }
                    .filter { val charSequence: CharSequence = it
                            charSequence.any { it.isDigit() } }
                    .singleOrError()
                    .onErrorResumeNext {
                        if (it is NoSuchElementException) {
                            Single.error(Exception("Does not contain digit"))
                        } else {
                            Single.error(it)
                        }
                    }
                    .toObservable()
        }

    }


    private val verifyPasswordPattern = ObservableTransformer<String,String> { observable ->
        observable.flatMap {
            Observable.just(it)
                    .map { it.trim() }
                    .filter { it.length > 7 }
                    .singleOrError()
                    .onErrorResumeNext {
                        if (it is NoSuchElementException) {
                            Single.error(Exception("Length should be greater than 7"))
                        } else {
                            Single.error(it)
                        }
                    }
                    .toObservable()
        }

    }

    private val lengthGreaterThanSix = ObservableTransformer<String, String> { observable ->
        observable.switchMap { Observable.just(it)
                    .map {
                          Log.d(TAG,it)
                         it.trim() }
                    .filter { it.length > 6 }
                    .singleOrError()
                    .onErrorResumeNext {
                        if (it is NoSuchElementException) {
                            Single.error(Exception("Length should be greater than 6"))
                        } else {
                            Single.error(it)
                        }
                    }
                    .toObservable()
        }
    }

    private val verifyEmailPattern = ObservableTransformer<String, String> { observable ->
        observable.switchMap {
            Observable.just(it)
            .map { it.trim() }
                    .filter { Patterns.EMAIL_ADDRESS.matcher(it).matches() }
                    .singleOrError()
                    .onErrorResumeNext {
                        if (it is NoSuchElementException) {
                            Single.error(Exception("Email not valid"))
                        } else {
                            Single.error(it)
                        }
                    }
                    .toObservable()
        }
    }
}

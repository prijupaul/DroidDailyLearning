package au.com.learning.droid.dailylearning.livedata


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer


class NonNullMediatorLiveData<T> : MediatorLiveData<T>()

fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
    val mediator: NonNullMediatorLiveData<T> = NonNullMediatorLiveData()
    mediator.addSource(this, Observer {
        it?.let { mediator.value = it }
    })
    return mediator
}

fun <T> NonNullMediatorLiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

class testLiveData {
    val fooLiveData = NonNullMediatorLiveData<String>()
    fun testFoo(lifeCycleOwner: LifecycleOwner) {

        fooLiveData
                .nonNull()
                .observe(lifeCycleOwner, {

                })

        fooLiveData.observe(lifeCycleOwner, Observer {})
    }
}


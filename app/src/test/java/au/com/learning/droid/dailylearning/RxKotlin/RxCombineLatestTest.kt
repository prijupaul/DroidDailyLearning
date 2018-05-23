package au.com.learning.droid.dailylearning.RxKotlin

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RxCombineLatestTest {

    lateinit var rxCombineLatest : RxCombineLatest
    @Before
    fun setup() {

        rxCombineLatest = RxCombineLatest()
    }

    @Test
    fun `test complete`() {
        val testObserver = rxCombineLatest.observable.test()
        testObserver.assertSubscribed()
        testObserver.assertValues("Emit 1","Emit 2","Emit 3","Emit 4")
    }
}
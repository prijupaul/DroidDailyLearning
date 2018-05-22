package au.com.learning.droid.dailylearning.RxKotlin

import org.junit.Before
import org.junit.Test

class RxCombineLatestTest {

    lateinit var rxCombineLatest : RxCombineLatest
    @Before
    fun setup() {
        rxCombineLatest = RxCombineLatest()
    }

    @Test
    fun combineBoth() {
        rxCombineLatest.combineBoth()
    }
}
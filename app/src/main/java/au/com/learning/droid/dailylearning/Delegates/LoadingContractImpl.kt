package au.com.learning.droid.dailylearning.Delegates

import android.content.Context
import android.util.Log

class LoadContractImpl(private val context: Context) : LoadingContract {

    override fun showLoading() {
        Log.d("Loading Contract","Show Loading")
    }

    override fun hideLoading() {
        Log.d("Hide Contract","Hide Loading")
    }
}
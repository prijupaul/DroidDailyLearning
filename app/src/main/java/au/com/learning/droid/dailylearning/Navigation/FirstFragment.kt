package au.com.learning.droid.dailylearning.Navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import au.com.learning.droid.dailylearning.R
import kotlinx.android.synthetic.main.firstfragment.*

class FirstFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(this.context, R.layout.firstfragment,null)
    }

    override fun onResume() {
        super.onResume()
        button_press.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_firstFragment_to_secondFragment))
    }
}
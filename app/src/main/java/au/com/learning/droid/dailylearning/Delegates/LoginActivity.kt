package au.com.learning.droid.dailylearning.Delegates

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import au.com.learning.droid.dailylearning.R
import kotlinx.android.synthetic.main.simple_layout.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginPresenter: LoginContractPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_layout)
        loginPresenter = LoginContractPresenter(this)
        button_press.setOnClickListener { loginPresenter.onButtonPress() }
    }





}
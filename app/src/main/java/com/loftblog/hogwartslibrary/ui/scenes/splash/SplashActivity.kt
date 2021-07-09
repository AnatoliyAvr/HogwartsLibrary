package com.loftblog.hogwartslibrary.ui.scenes.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.loftblog.hogwartslibrary.R
import com.loftblog.hogwartslibrary.databinding.ActivityMainBinding
import com.loftblog.hogwartslibrary.helpers.Keys
import com.loftblog.hogwartslibrary.ui.scenes.hat.HatActivity
import com.loftblog.hogwartslibrary.ui.scenes.main.MainActivity

class SplashActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val username = getSharedPreferences(getString(R.string.app_name), 0)
      .getString(Keys.Username.value, "") ?: ""

    if (username.isEmpty()) {
      startActivity(Intent(applicationContext, HatActivity::class.java))
    } else {
      startActivity(Intent(applicationContext, MainActivity::class.java))
    }

  }
}



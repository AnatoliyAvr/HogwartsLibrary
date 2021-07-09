package com.loftblog.hogwartslibrary.ui.scenes.hat

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.loftblog.hogwartslibrary.R
import com.loftblog.hogwartslibrary.databinding.ActivityHatBinding
import com.loftblog.hogwartslibrary.helpers.Keys
import com.loftblog.hogwartslibrary.ui.scenes.main.MainActivity

class HatActivity : AppCompatActivity() {

  private lateinit var binding: ActivityHatBinding
  private lateinit var hatViewModel: HatViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityHatBinding.inflate(layoutInflater)
    setContentView(binding.root)

    hatViewModel = ViewModelProvider(this).get(HatViewModel::class.java)

    binding.textWelcomeUsername.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun afterTextChanged(s: Editable?) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        hatViewModel.applyUsername(name = s.toString())
      }
    })

    binding.btnWelcomeSelect.setOnClickListener {
      binding.apply {
        if (btnWelcomeSelect.text == getString(R.string.welcome_next)) {
          val intent = Intent(applicationContext, MainActivity::class.java)
          startActivity(intent)
          finish()
        } else {
          hatViewModel.getFacultyName()
        }
      }
    }

    setupFaculty(viewModel = hatViewModel)
    setupLoading(viewModel = hatViewModel)
  }

  private fun setupFaculty(viewModel: HatViewModel) {
    viewModel.facultyName.observe(this, { facultyName ->
      if (facultyName.isNotEmpty()) {
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), 0)
        sharedPreferences.edit()
          .putString(Keys.Username.value, binding.textWelcomeUsername.text.toString())
          .putString(Keys.Faculty.value, facultyName)
          .apply()

        binding.apply {
          txtWelcomeSelected.text = getString(R.string.welcome_selected).replace("[faculty_name]", facultyName)
          txtWelcomeSelected.visibility = View.VISIBLE
          txtWelcomeSelected.isEnabled = false
          btnWelcomeSelect.text = getString(R.string.welcome_next)
        }
      }
    })
  }

  private fun setupLoading(viewModel: HatViewModel) {
    viewModel.isLoading.observe(this, { isLoading ->
      binding.apply {
        textWelcomeUsername.isEnabled = !isLoading
        btnWelcomeSelect.isEnabled = !isLoading
        if (isLoading)
          btnWelcomeSelect.text = getString(R.string.welcome_selecting)
      }
    })
  }
}
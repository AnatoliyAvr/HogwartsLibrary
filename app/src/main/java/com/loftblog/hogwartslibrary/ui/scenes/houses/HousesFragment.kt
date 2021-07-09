package com.loftblog.hogwartslibrary.ui.scenes.houses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.loftblog.hogwartslibrary.R
import com.loftblog.hogwartslibrary.databinding.FragmentHousesBinding
import com.loftblog.hogwartslibrary.helpers.Keys
import com.loftblog.hogwartslibrary.ui.scenes.house.Houses

class HousesFragment : Fragment() {

  private lateinit var housesViewModel: HousesViewModel
  private var _binding: FragmentHousesBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    housesViewModel =
      ViewModelProvider(this).get(HousesViewModel::class.java)

    _binding = FragmentHousesBinding.inflate(inflater, container, false)
    val root: View = binding.root
    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.apply {
      imgGryffindor.setOnClickListener { showDetail(sender = it) }
      imgSlytherin.setOnClickListener { showDetail(sender = it) }
      imgHufflepuff.setOnClickListener { showDetail(sender = it) }
      imgRavenclaw.setOnClickListener { showDetail(sender = it) }
    }
  }

  private fun showDetail(sender: View) {
    val house = when (sender.tag) {
      "0" -> Houses.Gryffindor
      "1" -> Houses.Hufflepuff
      "2" -> Houses.Ravenclaw
      else -> Houses.Slytherin
    }

    sender.findNavController().navigate(R.id.action_navigation_dashboard_to_houseDetailFragment,
      Bundle().apply
      { this.putSerializable(Keys.Faculty.value, house) })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
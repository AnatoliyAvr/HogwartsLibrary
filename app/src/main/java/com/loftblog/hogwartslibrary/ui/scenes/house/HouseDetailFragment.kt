package com.loftblog.hogwartslibrary.ui.scenes.house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.loftblog.hogwartslibrary.R
import com.loftblog.hogwartslibrary.databinding.FragmentHouseDetailBinding
import com.loftblog.hogwartslibrary.helpers.Keys

class HouseDetailFragment : Fragment() {

  private lateinit var viewModel: HouseDetailViewModel
  private var _binding: FragmentHouseDetailBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    viewModel = ViewModelProvider(this).get(HouseDetailViewModel::class.java)

    _binding = FragmentHouseDetailBinding.inflate(inflater, container, false)
    val root: View = binding.root

    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    configureLayout()
    viewModel.fetchData(houses = arguments?.get(Keys.Faculty.value) as? Houses)
  }

  private fun configureLayout() {
    viewModel.founder.observe(viewLifecycleOwner, {
      binding.txtHouseOwner.text = getString(R.string.house_detail_owner).replace("[FOUNDER_NAME]", it)
    })
    viewModel.ghost.observe(viewLifecycleOwner, {
      binding.txtHouseGhost.text = getString(R.string.house_detail_ghost).replace("[GHOST_NAME]", it)
    })
    viewModel.leader.observe(viewLifecycleOwner, {
      binding.txtHouseLeader.text = getString(R.string.house_detail_leader).replace("[LEADER_NAME]", it)
    })
    viewModel.houseName.observe(viewLifecycleOwner, {
      binding.txtHouseName.text = it
    })
    viewModel.houseImage.observe(viewLifecycleOwner, {
      binding.imgHouseDetail.setImageResource(it)
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
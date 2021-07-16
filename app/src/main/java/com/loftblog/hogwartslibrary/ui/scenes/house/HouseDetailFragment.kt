package com.loftblog.hogwartslibrary.ui.scenes.house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.loftblog.hogwartslibrary.R
import com.loftblog.hogwartslibrary.databinding.FragmentHouseDetailBinding
import com.loftblog.hogwartslibrary.helpers.Keys
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
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
    setupLoading()

    viewModel.fetchData(house = arguments?.get(Keys.Faculty.value) as? Houses)

    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        view.findNavController().navigate(R.id.action_houseDetailFragment_to_navigation_dashboard)
      }
    })
  }

  private fun setupLoading(){
    viewModel.isLoading.observe(viewLifecycleOwner, {
      binding.apply {
        txtHouseDetailsLoading.visibility = if (it) {
          View.VISIBLE
        } else {
          View.GONE
        }

        linerLayoutHouseDetail.visibility = if (it) {
          View.GONE
        } else {
          View.VISIBLE
        }
      }
    })
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
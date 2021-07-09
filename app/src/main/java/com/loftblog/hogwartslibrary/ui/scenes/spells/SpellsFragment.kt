package com.loftblog.hogwartslibrary.ui.scenes.spells

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loftblog.hogwartslibrary.databinding.FragmentSpellsBinding
import com.loftblog.hogwartslibrary.ui.scenes.spells.adapters.SpellAdapter

class SpellsFragment : Fragment() {

  private var _binding: FragmentSpellsBinding? = null
  private lateinit var viewModel: SpellsViewModel
  private val mAdapter = SpellAdapter()

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    viewModel =
      ViewModelProvider(this).get(SpellsViewModel::class.java)

    _binding = FragmentSpellsBinding.inflate(inflater, container, false)
    val root: View = binding.root
    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    configureRecycler()
    configureDataDisplay()

    binding.apply {
      btnSpellsCharm.setOnClickListener {
        btnSpellsCharm.isSelected = !btnSpellsCharm.isSelected
        viewModel.pressFilter(type = "Charm", isSelected = btnSpellsCharm.isSelected)
      }
      btnSpellsSpell.setOnClickListener {
        btnSpellsSpell.isSelected = !btnSpellsSpell.isSelected
        viewModel.pressFilter(type = "Spell", isSelected = btnSpellsSpell.isSelected)
      }
      btnSpellsJinx.setOnClickListener {
        btnSpellsJinx.isSelected = !btnSpellsJinx.isSelected
        viewModel.pressFilter(type = "Jinx", isSelected = btnSpellsJinx.isSelected)
      }
      btnSpellsCurse.setOnClickListener {
        btnSpellsCurse.isSelected = !btnSpellsCurse.isSelected
        viewModel.pressFilter(type = "Curse", isSelected = btnSpellsCurse.isSelected)
      }
    }
  }

  private fun configureRecycler() {
    binding.apply {
      recyclerSpells.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      recyclerSpells.adapter = mAdapter
    }
  }

  private fun configureDataDisplay() {
    viewModel.spellsDisplay.observe(viewLifecycleOwner, { data ->
      mAdapter.setData(newData = data)
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
package com.loftblog.hogwartslibrary.ui.scenes.students

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loftblog.hogwartslibrary.databinding.FragmentStudentsBinding
import com.loftblog.hogwartslibrary.ui.scenes.students.adapter.StudentsAdapter

class StudentsFragment : Fragment() {

  private lateinit var studentsViewModel: StudentsViewModel
  private var mAdapter = StudentsAdapter()
  private var _binding: FragmentStudentsBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    studentsViewModel =
      ViewModelProvider(this).get(StudentsViewModel::class.java)

    _binding = FragmentStudentsBinding.inflate(inflater, container, false)
    val root: View = binding.root

    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupData()
    setupLoading()

    binding.apply {
      context?.let {
        recyclerStudents.adapter = mAdapter
        recyclerStudents.layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
      }

      textStudentsSearch.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
          mAdapter.filter(query = s.toString())
        }

      })
    }

    studentsViewModel.fetchStudents()
  }

  private fun setupLoading() {
    studentsViewModel.isLoading.observe(viewLifecycleOwner, {
      binding.apply {
        txtStudentsLoading.visibility = if (it) {
          View.VISIBLE
        } else {
          View.GONE
        }

        recyclerStudents.visibility = if (it) {
          View.GONE
        } else {
          View.VISIBLE
        }

        textStudentsSearch.visibility = if (it) {
          View.GONE
        } else {
          View.VISIBLE
        }

      }
    })
  }

  private fun setupData() {
    studentsViewModel.students.observe(viewLifecycleOwner, {
      if (it.isNotEmpty()) {
        mAdapter.setData(newData = it)
      }
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
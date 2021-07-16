package com.loftblog.hogwartslibrary.ui.scenes.students

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loftblog.hogwartslibrary.domain.repositories.StudentsRepositoryImpl
import com.loftblog.hogwartslibrary.ui.scenes.students.adapter.StudentCellModel
import com.loftblog.hogwartslibrary.ui.scenes.students.adapter.mapToUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class StudentsViewModel : ViewModel() {

  private val studentsRepository = StudentsRepositoryImpl()

  private val _students = MutableLiveData<List<StudentCellModel>>().apply {
    value = ArrayList()
  }

  private val _isLoading = MutableLiveData<Boolean>().apply { value = false }

  val students: LiveData<List<StudentCellModel>> = _students
  val isLoading: LiveData<Boolean> = _isLoading

  fun fetchStudents() {
    viewModelScope.launch {
      _isLoading.postValue(true)
      withContext(Dispatchers.Default) {
        val students = studentsRepository.fetchStudents()
        _isLoading.postValue(false)
        students?.let { values ->
          _students.postValue(values.map { it.mapToUI() })
        }
      }
    }
  }
}
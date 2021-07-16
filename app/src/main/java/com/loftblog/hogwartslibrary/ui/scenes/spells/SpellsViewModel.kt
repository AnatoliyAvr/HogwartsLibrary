package com.loftblog.hogwartslibrary.ui.scenes.spells

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loftblog.hogwartslibrary.domain.repositories.SpellRepositoryImpl
import com.loftblog.hogwartslibrary.ui.scenes.spells.adapters.SpellCellModel
import com.loftblog.hogwartslibrary.ui.scenes.spells.adapters.mapToUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class SpellsViewModel : ViewModel() {

  private val spellRepository = SpellRepositoryImpl()

  private val _spells = MutableLiveData<MutableList<SpellCellModel>>().apply {
    value = mutableListOf()
  }

  private val _filters = MutableLiveData<MutableList<String>>().apply { value = mutableListOf() }
  private val _spellsDisplay = MutableLiveData<MutableList<SpellCellModel>>().apply { value = ArrayList() }
  private val _isLoading = MutableLiveData<Boolean>().apply { value = false }

  val spellsDisplay: LiveData<MutableList<SpellCellModel>> = _spellsDisplay
  val isLoading: LiveData<Boolean> = _isLoading

  init {
    fetchSpells()
  }

  private fun fetchSpells() {
    viewModelScope.launch {
      _isLoading.postValue(true)
      withContext(Dispatchers.Default) {
        val spells = spellRepository.getAllSpell()
        spells?.let { value ->
          _spells.postValue(value.map { it.mapToUI() }.toMutableList())
        }
        delay(1)
        _spellsDisplay.postValue(_spells.value ?: ArrayList())
        _isLoading.postValue(false)
      }
    }
  }

  fun pressFilter(type: String, isSelected: Boolean) {
    if (isSelected) {
      _filters.value?.add(type)
    } else {
      _filters.value?.remove(type)
    }

    if (_filters.value?.isEmpty() == true) {
      _spellsDisplay.postValue(_spells.value ?: ArrayList())
      return
    }

    _spellsDisplay.postValue(_spells.value?.filter { _filters.value?.contains(it.type) ?: false }?.toMutableList())
  }
}
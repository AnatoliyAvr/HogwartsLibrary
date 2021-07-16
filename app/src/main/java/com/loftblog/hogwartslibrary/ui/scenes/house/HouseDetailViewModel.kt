package com.loftblog.hogwartslibrary.ui.scenes.house

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loftblog.hogwartslibrary.R
import com.loftblog.hogwartslibrary.domain.repositories.HouseRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class HouseDetailViewModel : ViewModel() {

  private val houseRepository = HouseRepositoryImpl()

  private val _ghost: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
  private val _founder: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
  private val _leader: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
  private val _houseName: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
  private val _houseImage: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = R.drawable.img_gryffindor }

  val ghost: LiveData<String> = _ghost
  val founder: LiveData<String> = _founder
  val leader: LiveData<String> = _leader
  val houseName: LiveData<String> = _houseName
  val houseImage: LiveData<Int> = _houseImage

  private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
  val isLoading: LiveData<Boolean> = _isLoading

  fun fetchData(house: Houses?) {

    viewModelScope.launch {
      _isLoading.postValue(true)
      withContext(Dispatchers.Default) {
        house?.let {
          val details = houseRepository.getHouseDetails(house = house)
          _ghost.postValue(details?.ghost.orEmpty())
          _founder.postValue(details?.founder.orEmpty())
          _leader.postValue(details?.leader.orEmpty())
          _houseName.postValue(details?.name.orEmpty())
          when (house) {
            Houses.Gryffindor -> _houseImage.postValue(R.drawable.img_gryffindor)
            Houses.Slytherin -> _houseImage.postValue(R.drawable.img_slytherin)
            Houses.Ravenclaw -> _houseImage.postValue(R.drawable.img_ravenclaw)
            Houses.Hufflepuff -> _houseImage.postValue(R.drawable.img_hufflepuff)
          }
          delay(500)
          _isLoading.postValue(false)
        }
      }
    }
  }
}




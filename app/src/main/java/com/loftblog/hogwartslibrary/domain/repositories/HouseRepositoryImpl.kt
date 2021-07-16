package com.loftblog.hogwartslibrary.domain.repositories

import com.loftblog.hogwartslibrary.data.network.RetrofitFactory
import com.loftblog.hogwartslibrary.data.service.HousesService
import com.loftblog.hogwartslibrary.domain.models.HouseModel
import com.loftblog.hogwartslibrary.domain.models.mapToDomain
import com.loftblog.hogwartslibrary.ui.scenes.house.Houses
import kotlinx.serialization.ExperimentalSerializationApi
import java.lang.Exception

@ExperimentalSerializationApi
class HouseRepositoryImpl : HouseRepository {

  override suspend fun getHouseDetails(house: Houses): HouseModel? {
    val house = when (house) {
      Houses.Gryffindor -> HousesService.griffindor
      Houses.Hufflepuff -> HousesService.hufflepuff
      Houses.Ravenclaw -> HousesService.ravenclaw
      Houses.Slytherin -> HousesService.slytherin
    }

    return try {
      RetrofitFactory.instance.houseService.getHouseDetails(house)
        .map { it.mapToDomain() }[0]
    } catch (e: Exception) {
      return null
    }
  }
}
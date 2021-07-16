package com.loftblog.hogwartslibrary.domain.repositories

import com.loftblog.hogwartslibrary.domain.models.HouseModel
import com.loftblog.hogwartslibrary.ui.scenes.house.Houses

interface HouseRepository {
  suspend fun getHouseDetails(house: Houses): HouseModel?
}
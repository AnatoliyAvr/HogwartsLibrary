package com.loftblog.hogwartslibrary.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.loftblog.hogwartslibrary.data.service.CharacterService
import com.loftblog.hogwartslibrary.data.service.HatService
import com.loftblog.hogwartslibrary.data.service.HousesService
import com.loftblog.hogwartslibrary.data.service.SpellService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@ExperimentalSerializationApi
class RetrofitFactory {

  companion object {
    val instance = RetrofitFactory()
  }

  private fun okHttpInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
  }

  private val json = Json {
    encodeDefaults = true
    prettyPrint = true  // Читабельные отступы при показе json
    ignoreUnknownKeys = true  // Игнорировать неизвестные поля в Json объекте
    isLenient = true
  }

  private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(okHttpInterceptor())
    .build()


  private val retrofitClient: Retrofit = Retrofit.Builder()
    .baseUrl("https://hp-api.herokuapp.com/api/")
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

  private val retrofitClientHat : Retrofit = Retrofit.Builder()
    .baseUrl("https://www.potterapi.com/v1/")
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

  val charactersService: CharacterService = retrofitClient.create(CharacterService::class.java)
  val houseService: HousesService = retrofitClient.create(HousesService::class.java)
  val spellService: SpellService = retrofitClient.create(SpellService::class.java)
  val hatService: HatService = retrofitClientHat.create(HatService::class.java)
}
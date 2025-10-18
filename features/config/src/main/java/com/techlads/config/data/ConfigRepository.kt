package com.techlads.config.data

import com.techlads.config.ConfigApiService
import javax.inject.Inject

class ConfigRepository @Inject constructor(private val config: ConfigApiService) {
    suspend fun getConfig() = config.getConfig()
}
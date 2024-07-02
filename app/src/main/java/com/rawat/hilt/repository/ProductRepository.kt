package com.rawat.hilt.repository

import com.rawat.hilt.retrofit.FakerAPI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val fakerAPI: FakerAPI
) {
    fun getProducts() = flow {
        emit(fakerAPI.getProducts())
    }
}


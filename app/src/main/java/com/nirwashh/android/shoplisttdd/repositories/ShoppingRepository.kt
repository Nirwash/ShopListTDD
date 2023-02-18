package com.nirwashh.android.shoplisttdd.repositories

import androidx.lifecycle.LiveData
import com.nirwashh.android.shoplisttdd.data.local.ShoppingItem
import com.nirwashh.android.shoplisttdd.data.remote.responses.ImageResponse
import com.nirwashh.android.shoplisttdd.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}
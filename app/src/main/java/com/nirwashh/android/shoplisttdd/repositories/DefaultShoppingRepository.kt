package com.nirwashh.android.shoplisttdd.repositories

import androidx.lifecycle.LiveData
import com.nirwashh.android.shoplisttdd.data.local.ShoppingDao
import com.nirwashh.android.shoplisttdd.data.local.ShoppingItem
import com.nirwashh.android.shoplisttdd.data.remote.PixabayApi
import com.nirwashh.android.shoplisttdd.data.remote.responses.ImageResponse
import com.nirwashh.android.shoplisttdd.other.Resource
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayApi: PixabayApi
) : ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) =
        shoppingDao.insertShoppingItem(shoppingItem)

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) =
        shoppingDao.deleteShoppingItem(shoppingItem)

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> =
        shoppingDao.observeAllShoppingItems()

    override fun observeTotalPrice(): LiveData<Float> = shoppingDao.observeTotalPrice()

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> =
        try {
            val response = pixabayApi.searchForImage(imageQuery)
            if (response.isSuccessful)
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            else
                Resource.error("An unknown error occured", null)
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)

        }

}
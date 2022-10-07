package com.makeup.makeupapptest.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makeup.makeupapptest.home.models.ProductListItem

@Dao
interface MakeUpDAO {
    @Query("SELECT * FROM ProductListItem")
    fun findAll(): List<ProductListItem>

    @Query("SELECT * FROM ProductListItem")
    fun getAllData(): LiveData<List<ProductListItem>>

    @Query("SELECT * FROM ProductListItem WHERE productType IN (:productType)")
    fun getSortedData(productType: String): LiveData<List<ProductListItem>>

    @Query("DELETE FROM ProductListItem")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(makeup: List<ProductListItem>)
}
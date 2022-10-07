package com.makeup.makeupapptest.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.makeup.makeupapptest.home.models.ProductColor
import com.makeup.makeupapptest.home.models.ProductListItem
import com.makeup.makeupapptest.repository.converters.AbstractConverter
import com.makeup.makeupapptest.repository.converters.TagListConverter

@Database(
    entities = [
        ProductListItem::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ProductColorConverter::class,
    TagListConverter::class
)
abstract class MakeUpDatabase : RoomDatabase() {
    abstract val makeUpDAO: MakeUpDAO
}

private class ProductColorConverter : AbstractConverter<ProductColor>(ProductColor::class, Array<ProductColor>::class)
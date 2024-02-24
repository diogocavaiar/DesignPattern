package br.com.dmcconsulting.designpattern.features.home.di

import br.com.dmcconsulting.designpattern.features.home.data.ItemDataSource
import br.com.dmcconsulting.designpattern.features.home.data.local.datasource.ItemDatasourceLocal
import br.com.dmcconsulting.designpattern.features.home.data.local.repository.ItemRepositoryImpl
import br.com.dmcconsulting.designpattern.features.home.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun providesItemDatasourceLocal(): ItemDataSource = ItemDatasourceLocal()

    @Singleton
    @Provides
    fun providesItemRepository(
        itemDataSource: ItemDataSource
    ): ItemRepository = ItemRepositoryImpl(itemDataSource)
}

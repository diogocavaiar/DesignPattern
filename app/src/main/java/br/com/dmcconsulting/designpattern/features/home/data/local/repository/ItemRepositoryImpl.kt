package br.com.dmcconsulting.designpattern.features.home.data.local.repository

import br.com.dmcconsulting.designpattern.features.home.data.ItemDataSource
import br.com.dmcconsulting.designpattern.features.home.domain.model.ItemRegistered
import br.com.dmcconsulting.designpattern.features.home.domain.repository.ItemRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemRepositoryImpl @Inject constructor(
    itemDataSourceLocal: ItemDataSource
) : ItemRepository {

    private val _items = MutableStateFlow(itemDataSourceLocal.getItemsLocal())
    private val items: StateFlow<List<ItemRegistered>> = _items

    override suspend fun addItem(item: ItemRegistered) {
        _items.value = _items.value + item
    }

    override fun getItems(): Flow<List<ItemRegistered>> = items

}
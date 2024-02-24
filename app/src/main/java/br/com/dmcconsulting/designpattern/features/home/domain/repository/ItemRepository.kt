package br.com.dmcconsulting.designpattern.features.home.domain.repository

import br.com.dmcconsulting.designpattern.features.home.domain.model.ItemRegistered
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun addItem(item: ItemRegistered)
    fun getItems(): Flow<List<ItemRegistered>>
}
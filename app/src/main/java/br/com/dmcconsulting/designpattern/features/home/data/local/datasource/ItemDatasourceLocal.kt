package br.com.dmcconsulting.designpattern.features.home.data.local.datasource

import br.com.dmcconsulting.designpattern.features.home.data.ItemDataSource
import br.com.dmcconsulting.designpattern.features.home.domain.model.ItemRegistered
import java.util.UUID
import javax.inject.Inject

class ItemDatasourceLocal @Inject constructor() : ItemDataSource {
    override fun getItemsLocal(): List<ItemRegistered> = listOf(
        ItemRegistered(
            id = UUID.randomUUID().toString(),
            name = "Banana"
        ),
        ItemRegistered(
            id = UUID.randomUUID().toString(),
            name = "Apple"
        ),
        ItemRegistered(
            id = UUID.randomUUID().toString(),
            name = "Orange"
        )
    )
}
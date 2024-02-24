package br.com.dmcconsulting.designpattern.features.home.data

import br.com.dmcconsulting.designpattern.features.home.domain.model.ItemRegistered

interface ItemDataSource {
    fun getItemsLocal(): List<ItemRegistered>
}
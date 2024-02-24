package br.com.dmcconsulting.designpattern.features.home.domain.usecase

import br.com.dmcconsulting.designpattern.features.home.domain.model.ItemRegistered
import br.com.dmcconsulting.designpattern.features.home.domain.repository.ItemRepository
import javax.inject.Inject

class AddItemUseCase @Inject constructor(private val repository: ItemRepository) {
    suspend operator fun invoke(item: ItemRegistered) = repository.addItem(item)
}
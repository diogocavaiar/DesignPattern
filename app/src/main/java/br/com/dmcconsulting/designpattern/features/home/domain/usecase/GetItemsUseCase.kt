package br.com.dmcconsulting.designpattern.features.home.domain.usecase

import br.com.dmcconsulting.designpattern.features.home.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repository: ItemRepository) {
    operator fun invoke() = repository.getItems()
}
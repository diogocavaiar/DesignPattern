package br.com.dmcconsulting.designpattern.features.home.presentation

import androidx.compose.runtime.Stable
import br.com.dmcconsulting.designpattern.features.home.domain.model.ItemRegistered
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class HomeStateUI(
    val items: Flow<List<ItemRegistered>>,
) {
    companion object {
        fun initial() = HomeStateUI(emptyFlow())
    }
}

package br.com.dmcconsulting.designpattern.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dmcconsulting.designpattern.features.home.domain.model.ItemRegistered
import br.com.dmcconsulting.designpattern.features.home.domain.usecase.AddItemUseCase
import br.com.dmcconsulting.designpattern.features.home.domain.usecase.GetItemsUseCase
import br.com.dmcconsulting.designpattern.features.home.domain.usecase.WorkflowDiscountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addItemUseCase: AddItemUseCase,
    private val getItemsUseCase: GetItemsUseCase,
    private val workflowDiscountUseCase: WorkflowDiscountUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeStateUI> = MutableStateFlow(HomeStateUI.initial())
    val uiState: StateFlow<HomeStateUI> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(items = getItemsUseCase())
    }

    fun addItem(itemName: String) {
        val item = ItemRegistered(
            id = UUID.randomUUID().toString(),
            name = itemName,
        )
        viewModelScope.launch(Dispatchers.IO) {
            addItemUseCase(item)
        }
    }

    fun applyDiscount(discountApplied: Double) {
        workflowDiscountUseCase(discountApplied)
    }
}
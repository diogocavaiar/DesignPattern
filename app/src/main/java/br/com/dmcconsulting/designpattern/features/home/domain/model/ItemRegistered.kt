package br.com.dmcconsulting.designpattern.features.home.domain.model

data class ItemRegistered(
    override val id: String,
    override val name: String,
) : Item
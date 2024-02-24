package br.com.dmcconsulting.notificationmanager.mediator

interface Notification {
    fun createMediator(): NotificationMediator<out Notification>
}
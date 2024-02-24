package br.com.dmcconsulting.notificationmanager.mediator.normalnotification

import br.com.dmcconsulting.notificationmanager.mediator.Notification

internal class NormalNotification : Notification {
    override fun createMediator() = NormalNotificationMediator()
}
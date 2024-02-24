package br.com.dmcconsulting.notificationmanager.mediator.expandablenotification

import br.com.dmcconsulting.notificationmanager.mediator.Notification

internal class ExpandableNotification : Notification {
    override fun createMediator() = ExpandableNotificationMediator()
}
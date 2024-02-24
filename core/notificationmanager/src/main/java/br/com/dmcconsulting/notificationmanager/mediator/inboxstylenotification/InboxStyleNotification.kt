package br.com.dmcconsulting.notificationmanager.mediator.inboxstylenotification

import br.com.dmcconsulting.notificationmanager.mediator.Notification

internal class InboxStyleNotification : Notification {
    override fun createMediator() = InboxStyleNotificationMediator()
}
package br.com.dmcconsulting.notificationmanager.factorymethod

import br.com.dmcconsulting.notificationmanager.mediator.Notification

interface NotificationFactory {
    enum class Type { Normal, Expandable, InboxStyleNotification }

    fun createNotificationByType(type: Type): Notification
}

package br.com.dmcconsulting.notificationmanager

import br.com.dmcconsulting.notificationmanager.model.NotificationData

interface Notifier {
    fun postNotification(notificationData: NotificationData)
}
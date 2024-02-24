package br.com.dmcconsulting.notificationmanager.mediator

import android.content.Context
import br.com.dmcconsulting.notificationmanager.mediator.Notification
import br.com.dmcconsulting.notificationmanager.model.NotificationData

interface NotificationMediator<T : Notification> {
    fun createNotification(
        context: Context,
        notificationData: NotificationData
    ): android.app.Notification
}

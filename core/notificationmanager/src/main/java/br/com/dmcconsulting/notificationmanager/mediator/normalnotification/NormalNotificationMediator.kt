package br.com.dmcconsulting.notificationmanager.mediator.normalnotification

import android.content.Context
import androidx.core.app.NotificationCompat
import br.com.dmcconsulting.notificationmanager.mediator.NotificationMediator
import br.com.dmcconsulting.notificationmanager.model.NotificationData

internal class NormalNotificationMediator :
    NotificationMediator<NormalNotification> {
    override fun createNotification(
        context: Context,
        notificationData: NotificationData
    ): android.app.Notification = with(context) {
        return NotificationCompat.Builder(
            this,
            notificationData.channelId,
        ).setContentTitle(getString(notificationData.title))
            .setContentText(getString(notificationData.message))
            .setSmallIcon(notificationData.iconResId)
            .setPriority(notificationData.priority)
            .setAutoCancel(notificationData.autoCancel)
            .build()
    }
}

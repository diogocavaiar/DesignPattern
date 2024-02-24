package br.com.dmcconsulting.notificationmanager.mediator.inboxstylenotification

import android.content.Context
import androidx.core.app.NotificationCompat
import br.com.dmcconsulting.notificationmanager.mediator.NotificationMediator
import br.com.dmcconsulting.notificationmanager.model.NotificationData

class InboxStyleNotificationMediator :
    NotificationMediator<InboxStyleNotification> {
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
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .addLine("Line 1")
                    .addLine("Line 2")
                    .addLine("Line 3")
                    .addLine("Line 4")
                    .addLine("Line 5")
                    .addLine("Line 6")
                    .addLine("Line 7")
            )
            .setAutoCancel(notificationData.autoCancel)
            .build()
    }
}

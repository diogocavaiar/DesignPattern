package br.com.dmcconsulting.notificationmanager

import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import br.com.dmcconsulting.notificationmanager.factorymethod.NotificationByTypeFactory
import br.com.dmcconsulting.notificationmanager.model.NotificationData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotifyManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : Notifier {

    override fun postNotification(notificationData: NotificationData) = with(context) {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS,
            ) != android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            throw SecurityException("You must grant permission to POST_NOTIFICATIONS")
        }

        ensureNotificationChannelExists(
            notificationData.channelId,
            notificationData.channelName,
            notificationData.channelDescription
        )
        val notification = NotificationByTypeFactory()
            .createNotificationByType(notificationData.type)
            .createMediator()
            .createNotification(this, notificationData)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(1, notification)
    }
}

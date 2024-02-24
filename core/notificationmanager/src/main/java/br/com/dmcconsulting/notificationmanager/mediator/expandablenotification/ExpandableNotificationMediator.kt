package br.com.dmcconsulting.notificationmanager.mediator.expandablenotification

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import br.com.dmcconsulting.notificationmanager.mediator.NotificationMediator
import br.com.dmcconsulting.notificationmanager.model.NotificationData

class ExpandableNotificationMediator : NotificationMediator<ExpandableNotification> {
    override fun createNotification(
        context: Context,
        notificationData: NotificationData
    ): android.app.Notification = with(context) {
        val test = notificationData as NotificationData.ExpandableNotificationData
        val image = context.bitmapFromResource(test.image)

        return NotificationCompat.Builder(
            this,
            notificationData.channelId,
        ).setContentTitle(getString(notificationData.title))
            .setContentText(getString(notificationData.message))
            .setSmallIcon(notificationData.iconResId)
            .setPriority(notificationData.priority)
            .setLargeIcon(image)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(image)
                    .bigLargeIcon(null as Bitmap?)
            )
            .setAutoCancel(notificationData.autoCancel)
            .build()
    }
}

private fun Context.bitmapFromResource(
    @DrawableRes resId: Int
) = BitmapFactory.decodeResource(
    resources,
    resId
)
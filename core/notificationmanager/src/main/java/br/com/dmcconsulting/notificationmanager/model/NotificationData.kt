package br.com.dmcconsulting.notificationmanager.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import br.com.dmcconsulting.notificationmanager.factorymethod.NotificationFactory

private const val NOTIFICATION_CHANNEL_ID = "channelId"
private const val NOTIFICATION_CHANNEL_NAME = "channelName"
private const val NOTIFICATION_CHANNEL_DESCRIPTION = "channelDescription"

sealed class NotificationData(
    @StringRes open val title: Int,
    @StringRes open val message: Int,
    open val iconResId: Int,
    open val type: NotificationFactory.Type,
    open val channelId: String,
    open val channelName: String,
    open val channelDescription: String?,
    open val priority: Int,
    open val autoCancel: Boolean,
) {
    data class NormalNotificationData(
        override val title: Int,
        override val message: Int,
        override val iconResId: Int,
        override val channelId: String = NOTIFICATION_CHANNEL_ID,
        override val channelName: String = NOTIFICATION_CHANNEL_NAME,
        override val channelDescription: String? = NOTIFICATION_CHANNEL_DESCRIPTION,
        override val priority: Int = NotificationCompat.PRIORITY_HIGH,
        override val autoCancel: Boolean = true
    ) : NotificationData(
        title,
        message,
        iconResId,
        NotificationFactory.Type.Normal,
        channelId,
        channelName,
        channelDescription,
        priority,
        autoCancel
    )

    data class ExpandableNotificationData(
        override val title: Int,
        override val message: Int,
        override val iconResId: Int,
        @DrawableRes val image: Int,
        override val channelId: String = NOTIFICATION_CHANNEL_ID,
        override val channelName: String = NOTIFICATION_CHANNEL_NAME,
        override val channelDescription: String? = NOTIFICATION_CHANNEL_DESCRIPTION,
        override val priority: Int = NotificationCompat.PRIORITY_HIGH,
        override val autoCancel: Boolean = true
    ) : NotificationData(
        title,
        message,
        iconResId,
        NotificationFactory.Type.Expandable,
        channelId,
        channelName,
        channelDescription,
        priority,
        autoCancel
    )

    data class InboxStyleNotificationData(
        override val title: Int,
        override val message: Int,
        override val iconResId: Int,
        override val channelId: String = NOTIFICATION_CHANNEL_ID,
        override val channelName: String = NOTIFICATION_CHANNEL_NAME,
        override val channelDescription: String? = NOTIFICATION_CHANNEL_DESCRIPTION,
        override val priority: Int = NotificationCompat.PRIORITY_HIGH,
        override val autoCancel: Boolean = true
    ) : NotificationData(
        title,
        message,
        iconResId,
        NotificationFactory.Type.InboxStyleNotification,
        channelId,
        channelName,
        channelDescription,
        priority,
        autoCancel
    )
}
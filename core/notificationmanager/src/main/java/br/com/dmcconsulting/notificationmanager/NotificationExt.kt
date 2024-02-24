package br.com.dmcconsulting.notificationmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat

/**
 * Ensures that a notification channel is present if applicable
 */
internal fun Context.ensureNotificationChannelExists(
    channelId: String,
    channelName: String,
    descriptionChannel: String?
) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

    val channel = NotificationChannel(
        channelId,
        channelName,
        NotificationManager.IMPORTANCE_DEFAULT,
    ).apply {
        description = descriptionChannel
    }
    // Register the channel with the system
    NotificationManagerCompat.from(this).createNotificationChannel(channel)
}
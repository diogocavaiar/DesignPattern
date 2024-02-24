package br.com.dmcconsulting.notificationmanager.di

import br.com.dmcconsulting.notificationmanager.Notifier
import br.com.dmcconsulting.notificationmanager.NotifyManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModule {
    @Binds
    abstract fun bindsNotifyManagerImplHelper(notifyManagerImpl: NotifyManagerImpl): Notifier
}
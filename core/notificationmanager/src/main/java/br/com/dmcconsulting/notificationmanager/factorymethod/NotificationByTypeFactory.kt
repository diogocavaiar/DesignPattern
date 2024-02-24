package br.com.dmcconsulting.notificationmanager.factorymethod

import br.com.dmcconsulting.notificationmanager.mediator.Notification
import br.com.dmcconsulting.notificationmanager.mediator.expandablenotification.ExpandableNotification
import br.com.dmcconsulting.notificationmanager.mediator.inboxstylenotification.InboxStyleNotification
import br.com.dmcconsulting.notificationmanager.mediator.normalnotification.NormalNotification

class NotificationByTypeFactory : NotificationFactory {
    override fun createNotificationByType(type: NotificationFactory.Type): Notification =
        when (type) {
            NotificationFactory.Type.Normal -> NormalNotification()
            NotificationFactory.Type.Expandable -> ExpandableNotification()
            NotificationFactory.Type.InboxStyleNotification -> InboxStyleNotification()
        }
}

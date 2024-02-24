package br.com.dmcconsulting.designpattern.chainofresposability

import br.com.dmcconsulting.notificationmanager.model.NotificationData

sealed interface ResultValidationWorkflow {
    data class ApprovedRequiredByDirector(val data: NotificationData) : ResultValidationWorkflow
    data class ApprovedRequiredByVP(val data: NotificationData) : ResultValidationWorkflow
    data class ApprovedRequiredByCEO(val data: NotificationData) : ResultValidationWorkflow
    data object NoApprovalRequired : ResultValidationWorkflow
}
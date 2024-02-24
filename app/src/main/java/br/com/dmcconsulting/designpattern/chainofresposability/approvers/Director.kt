package br.com.dmcconsulting.designpattern.chainofresposability.approvers

import br.com.dmcconsulting.designpattern.R
import br.com.dmcconsulting.designpattern.chainofresposability.ApprovalWorkflow
import br.com.dmcconsulting.designpattern.chainofresposability.ResultValidationWorkflow
import br.com.dmcconsulting.notificationmanager.model.NotificationData

private const val MIN_DISCOUNT = 5.0
private const val MAX_DISCOUNT = 7.0

class Director : ApprovalWorkflow() {
    override fun check(discountApplied: Double): ResultValidationWorkflow {
        if (discountApplied in MIN_DISCOUNT..MAX_DISCOUNT) {
            val notificationData = NotificationData.NormalNotificationData(
                title = R.string.workflow_discount,
                message = R.string.must_approved_by_the_director,
                iconResId = R.drawable.ic_nia_notification,
            )
            return ResultValidationWorkflow.ApprovedRequiredByDirector(notificationData)
        }

        return checkNext(discountApplied)
    }
}

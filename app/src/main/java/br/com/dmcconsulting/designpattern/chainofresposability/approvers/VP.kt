package br.com.dmcconsulting.designpattern.chainofresposability.approvers

import br.com.dmcconsulting.designpattern.R
import br.com.dmcconsulting.designpattern.chainofresposability.ApprovalWorkflow
import br.com.dmcconsulting.designpattern.chainofresposability.ResultValidationWorkflow
import br.com.dmcconsulting.notificationmanager.model.NotificationData

private const val MIN_DISCOUNT = 7.0
private const val MAX_DISCOUNT = 14.0

class VP : ApprovalWorkflow() {
    override fun check(discountApplied: Double): ResultValidationWorkflow {
        if (discountApplied in MIN_DISCOUNT..MAX_DISCOUNT) {
            val notificationData = NotificationData.InboxStyleNotificationData(
                title = R.string.workflow_discount,
                message = R.string.must_approved_by_the_vp,
                iconResId = R.drawable.ic_nia_notification,
            )

            return ResultValidationWorkflow.ApprovedRequiredByVP(notificationData)
        }

        return checkNext(discountApplied)
    }
}
package br.com.dmcconsulting.designpattern.chainofresposability.approvers

import br.com.dmcconsulting.designpattern.R
import br.com.dmcconsulting.designpattern.chainofresposability.ApprovalWorkflow
import br.com.dmcconsulting.designpattern.chainofresposability.ResultValidationWorkflow
import br.com.dmcconsulting.notificationmanager.model.NotificationData

private const val MAX_DISCOUNT = 14.0

class CEO : ApprovalWorkflow() {
    override fun check(discountApplied: Double): ResultValidationWorkflow {
        if (discountApplied > MAX_DISCOUNT) {
            val notificationData = NotificationData.ExpandableNotificationData(
                title = R.string.workflow_discount,
                message = R.string.must_approved_by_the_ceo,
                iconResId = R.drawable.ic_nia_notification,
                image = br.com.dmcconsulting.notificationmanager.R.drawable.notification_image
            )

            return ResultValidationWorkflow.ApprovedRequiredByCEO(notificationData)
        }

        return checkNext(discountApplied)
    }
}
package br.com.dmcconsulting.designpattern.features.home.domain.usecase

import br.com.dmcconsulting.designpattern.chainofresposability.ChainResponsibilityWorkflow
import br.com.dmcconsulting.designpattern.chainofresposability.ResultValidationWorkflow
import br.com.dmcconsulting.notificationmanager.Notifier
import javax.inject.Inject

class WorkflowDiscountUseCase @Inject constructor(
    private val chainResponsibilityWorkflow: ChainResponsibilityWorkflow,
    private val notifyManagerImpl: Notifier
) {
    operator fun invoke(discountApplied: Double) {
        when (val result = chainResponsibilityWorkflow.startWorkflow(discountApplied)) {
            ResultValidationWorkflow.NoApprovalRequired -> {}
            is ResultValidationWorkflow.ApprovedRequiredByDirector -> notifyManagerImpl.postNotification(
                result.data
            )

            is ResultValidationWorkflow.ApprovedRequiredByVP -> notifyManagerImpl.postNotification(
                result.data
            )

            is ResultValidationWorkflow.ApprovedRequiredByCEO -> notifyManagerImpl.postNotification(
                result.data
            )
        }
    }
}
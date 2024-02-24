package br.com.dmcconsulting.designpattern.chainofresposability

import javax.inject.Inject

interface ChainResponsibilityWorkflow {
    fun startWorkflow(discountApplied: Double): ResultValidationWorkflow
}

class ManagerChainResponsibilityWorkflow @Inject constructor(
    private val approvalWorkflow: ApprovalWorkflow
) : ChainResponsibilityWorkflow {

    override fun startWorkflow(discountApplied: Double) = approvalWorkflow.check(discountApplied)
}
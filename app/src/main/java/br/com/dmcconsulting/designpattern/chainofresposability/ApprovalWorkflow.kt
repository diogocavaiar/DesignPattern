package br.com.dmcconsulting.designpattern.chainofresposability


abstract class ApprovalWorkflow {
    private var next: ApprovalWorkflow? = null

    abstract fun check(discountApplied: Double): ResultValidationWorkflow

    protected fun checkNext(discountApplied: Double): ResultValidationWorkflow {
        return next?.check(discountApplied) ?: ResultValidationWorkflow.NoApprovalRequired
    }

    private fun setNext(next: ApprovalWorkflow): ApprovalWorkflow {
        this.next = next
        return next
    }

    companion object {
        fun link(first: ApprovalWorkflow, vararg chain: ApprovalWorkflow): ApprovalWorkflow {
            var current = first
            chain.forEach {
                current = current.setNext(it)
            }
            return first
        }
    }
}
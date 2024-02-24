package br.com.dmcconsulting.designpattern.chainofresposability.di

import br.com.dmcconsulting.designpattern.chainofresposability.ApprovalWorkflow
import br.com.dmcconsulting.designpattern.chainofresposability.ChainResponsibilityWorkflow
import br.com.dmcconsulting.designpattern.chainofresposability.ManagerChainResponsibilityWorkflow
import br.com.dmcconsulting.designpattern.chainofresposability.approvers.CEO
import br.com.dmcconsulting.designpattern.chainofresposability.approvers.Director
import br.com.dmcconsulting.designpattern.chainofresposability.approvers.VP
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ChainOfResponsibilityModule {

    @Binds
    abstract fun bindsManagerChainResponsibilityNotification(
        managerChainResponsibilityNotification: ManagerChainResponsibilityWorkflow
    ): ChainResponsibilityWorkflow

    companion object {
        @Provides
        @Singleton
        fun providesApprovalFlow(): ApprovalWorkflow = ApprovalWorkflow.link(
            Director(),
            VP(),
            CEO()
        )
    }
}

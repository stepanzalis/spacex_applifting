package cz.stepanzalis.spacexlifts.ui.feature.company

import cz.stepanzalis.spacexlifts.io.base.Status
import cz.stepanzalis.spacexlifts.io.models.company.CompanyVo
import cz.stepanzalis.spacexlifts.ui.base.ViewState
import java.lang.Exception

data class CompanyState(
    override val status: Status = Status.Loading,
    val company: CompanyVo = CompanyVo.initial(),
) : ViewState.State(status) {
    companion object {

        fun success(company: CompanyVo) = CompanyState(status = Status.Success(), company)
        fun error(exception: Exception) = CompanyState(status = Status.Failure(exception))
    }
}


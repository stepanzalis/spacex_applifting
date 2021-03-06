package cz.stepanzalis.spacexlifts.ui.feature.company

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.viewModelScope
import cz.stepanzalis.spacexlifts.AppDebugToolsConfig
import cz.stepanzalis.spacexlifts.io.base.BaseVM
import cz.stepanzalis.spacexlifts.io.base.Failure
import cz.stepanzalis.spacexlifts.io.base.Status
import cz.stepanzalis.spacexlifts.io.base.Success
import cz.stepanzalis.spacexlifts.io.repositories.SpaceXRepo
import cz.stepanzalis.spacexlifts.io.response.CompanyInfoResponse
import cz.stepanzalis.spacexlifts.io.utils.ext.companyProtoDataStore
import cz.stepanzalis.spacexlifts.io.utils.ext.fromResponseToEntity
import cz.stepanzalis.spacexlifts.io.utils.ext.toModel
import kotlinx.coroutines.flow.*

@SuppressLint("StaticFieldLeak")
class CompanyVM(
    private val applicationContext: Context,
    private val spaceXRepo: SpaceXRepo,
) : BaseVM() {

    private val _viewState = MutableStateFlow(CompanyState())

    val viewState = _viewState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        _viewState.value
    )

    init {
        watchCompanyInfo()
        fetchCompanyInfo()
    }

    private fun watchCompanyInfo() = with(applicationContext) {
        launch(
            block = {
                companyProtoDataStore.data
                    .catch { t -> defaultStatus.update { Failure(Exception(t)) } }
                    .map { company ->
                        _viewState.update { CompanyState.success(company.toModel()) }
                    }.collect()
            },
        )
    }

    private fun fetchCompanyInfo() {
        launch(
            block = {
                val company = spaceXRepo.fetchCompanyInfo()
                saveCompanyToDataStore(company)
            },
        )
    }

    private fun saveCompanyToDataStore(company: CompanyInfoResponse) = with(applicationContext) {
        launch(block = {
            try {
                companyProtoDataStore.updateData {
                    company.fromResponseToEntity(it)
                }
            } catch (e: Exception) {
                AppDebugToolsConfig.logFailure(Status.Failure(e))
            }
        })
    }

    override fun dismissErrorDialog() {
        _viewState.update { it.copy(status = Success()) }
    }
}

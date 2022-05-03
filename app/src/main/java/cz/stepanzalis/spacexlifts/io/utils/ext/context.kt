package cz.stepanzalis.spacexlifts.io.utils.ext

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import cz.stepanzalis.spacexlifts.io.models.CompanyInfo
import cz.stepanzalis.spacexlifts.io.serializer.CompanyInfoSerializer

private const val CompanyProtoFile = "company.pb"

val Context.companyProtoDataStore: DataStore<CompanyInfo> by dataStore(
    fileName = CompanyProtoFile,
    serializer = CompanyInfoSerializer
)
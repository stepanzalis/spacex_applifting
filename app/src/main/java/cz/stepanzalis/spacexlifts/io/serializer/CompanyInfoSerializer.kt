package cz.stepanzalis.spacexlifts.io.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import cz.stepanzalis.spacexlifts.io.models.CompanyInfo
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

object CompanyInfoSerializer : Serializer<CompanyInfo> {
    override val defaultValue: CompanyInfo = CompanyInfo.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): CompanyInfo {
        try {
            return CompanyInfo.parseFrom(input)
        } catch (exception: Exception) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: CompanyInfo, output: OutputStream) = t.writeTo(output)
}
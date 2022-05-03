package cz.stepanzalis.spacexlifts.io.utils.ext

import cz.stepanzalis.spacexlifts.io.models.CompanyInfo
import cz.stepanzalis.spacexlifts.io.models.company.CompanyVo
import cz.stepanzalis.spacexlifts.io.response.CompanyInfoResponse

fun CompanyInfo.toModel(): CompanyVo {
    return CompanyVo(
        address = address,
        cto = cto,
        ceo = ceo,
        city = city,
        ctoPropulsion = ctoPropulsion,
        valuation = valuation,
        summary = summary,
        name = name,
        founder = founder,
        founded = founded
    )
}

fun CompanyInfoResponse.fromResponseToEntity(companyInfo: CompanyInfo): CompanyInfo {
    return companyInfo.toBuilder()
        .setAddress(headquarters.address)
        .setCity(headquarters.city)
        .setCeo(ceo)
        .setCto(cto)
        .setName(name)
        .setCtoPropulsion(ctoPropulsion)
        .setFounder(founder)
        .setSummary(summary)
        .setValuation(valuation)
        .build()
}
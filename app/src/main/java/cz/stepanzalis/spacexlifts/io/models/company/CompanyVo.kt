package cz.stepanzalis.spacexlifts.io.models.company

data class CompanyVo(
    val address: String,
    val city: String,
    val name: String,
    val founder: String,
    val founded: Int,
    val ceo: String,
    val cto: String,
    val ctoPropulsion: String,
    val valuation: Long,
    val summary: String,
) {

    companion object {
        fun initial(): CompanyVo {
            return CompanyVo(
                address = "",
                city = "",
                ceo = "",
                cto = "cto",
                ctoPropulsion = "",
                founded = 0,
                founder = "",
                valuation = 1,
                summary = "",
                name = "",
            )
        }
    }
}
package cz.stepanzalis.spacexlifts.io.models.rocket

data class RocketVo(
    val name: String,
    val stages: String,
    val costPerLaunch: Double,
    val description: String,
    val firstFlight: String
) {
    companion object {
        fun init(): RocketVo {
            return RocketVo(
                "",
                stages = "",
                costPerLaunch = .0,
                description = "",
                ""
            )
        }
    }
}
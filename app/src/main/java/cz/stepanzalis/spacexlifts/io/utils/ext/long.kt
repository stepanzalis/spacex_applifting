package cz.stepanzalis.spacexlifts.io.utils.ext

val Long.inMillis get() = this.times(1000)
val Long.inSeconds get() = this.div(1000)
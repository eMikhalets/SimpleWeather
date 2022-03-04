package com.emikhalets.simpleweather.ui.screens.base

import com.emikhalets.simpleweather.R

class WeatherUnits(
    val temperature: TemperatureUnits = TemperatureUnits.CELSIUS,
    val pressure: PressureUnits = PressureUnits.MILLIBARS,
    val speed: SpeedUnits = SpeedUnits.KILOMETERS,
) {
}

enum class TemperatureUnits(val unit: Int) {
    CELSIUS(R.string.app_unit_celsius),
    FAHRENHEIT(R.string.app_unit_fahrenheit);
}

enum class PressureUnits(val unit: Int) {
    MILLIBARS(R.string.app_unit_millibar),
    INCHES(R.string.app_unit_inches);
}

enum class SpeedUnits(val unit: Int) {
    KILOMETERS(R.string.app_unit_kilometer),
    MILES(R.string.app_unit_mile);
}
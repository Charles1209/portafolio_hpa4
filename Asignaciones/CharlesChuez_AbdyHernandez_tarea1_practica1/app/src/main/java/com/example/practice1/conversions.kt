package com.example.practice1

open class ConversionClass {
    open fun conversionFun (celcius: Double): Double {

        return 0.0
    }
}

class Fahrenheit : ConversionClass() {
    override fun conversionFun(celcius: Double): Double {
        return (celcius*9/5) + 32
    }
}

class Kelvin: ConversionClass() {
    override fun conversionFun(celcius: Double): Double {
        return celcius+273.15
    }
}

class Rankine: ConversionClass() {
    override fun conversionFun(celcius: Double): Double {
        return (celcius+273.15) * 9/5
    }
}
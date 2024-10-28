package com.cp1.t_p_3

open class Convert {

    open fun convertidorCel(Cel: Double): Double {
        return 0.0
    }
}

class Far : Convert(){
    override fun convertidorCel(Cel: Double): Double{
        return (Cel*9/5)+32
    }
}

class Kel : Convert(){
    override fun convertidorCel(Cel: Double): Double{
        return Cel+273.15
    }
}

class Ran : Convert(){
    override fun convertidorCel(Cel: Double): Double{
        return (Cel*9/5)+491.67
    }
}
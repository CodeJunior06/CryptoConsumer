package com.codejunior.cryptoconsumer.domain.model

data class Crypto (
    val id:Long,
    val name:String,
    val description:String,
    val symbol :String,
    val currentSupply: Double,
    val maxSupply:Double?,
    val isInfiniteSupply: Boolean,
    val dateAdd:String,
    val price:Double,
    val percent1H: Double? = 0.0,
    val percent1D: Double? = 0.0,
    val percent1S: Double? = 0.0,
    val marketCap: Double? = 0.0,
    val rank:Int,
    val routePhoto:String
)

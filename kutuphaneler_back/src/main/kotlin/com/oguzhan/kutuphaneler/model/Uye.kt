package com.oguzhan.kutuphaneler.model

data class Uye(
    val uyeId: Int,
    val uyeAd: String,
    val uyeSoyad: String,
    val cinsiyet: String,
    val telefon: String,
    val eposta: String,
    val sokak: String,
    val cadde: String
)
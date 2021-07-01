package com.oguzhan.kutuphaneler.model

data class KitapCreatePayload(
        val isbn: String,
        val kitapAdi: String,
        val yayinTarihi: String,
        val sayfaSayisi: Int,
        val yayinEviId: Int,
        val yazarId: Int
)
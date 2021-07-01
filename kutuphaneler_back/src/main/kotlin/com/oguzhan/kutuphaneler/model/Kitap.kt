package com.oguzhan.kutuphaneler.model

import java.time.LocalDateTime

data class Kitap(
        var kitap_id: Int = 0,

        val ISBN: String? = null,

        val kitapAdi: String? = null,

        val yayinTarihi: String? = null,

        val sayfaSayisi: Int = 0,

        val yazarAd: String? = null,

        val yazarSoyad: String? = null,
)
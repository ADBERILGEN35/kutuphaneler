package com.oguzhan.kutuphaneler.entity

import javax.persistence.*

@Entity
@Table(name = "Yazarlar", schema = "kutuphaneler", catalog = "")
data class YazarlarEntity(
        @Column(name = "yazarAd")
        var yazarAd: String,

        @Column(name = "yazarSoyad")
        var yazarSoyad: String

) {
    @Id
    @Column(name = "yazar_id")
    var yazarId: Int? = null
}


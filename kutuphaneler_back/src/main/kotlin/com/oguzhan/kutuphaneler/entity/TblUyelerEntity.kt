package com.oguzhan.kutuphaneler.entity

import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
@Table(name = "tbl_uyeler", schema = "kutuphaneler", catalog = "")
data class TblUyelerEntity (
    @Column(name = "uyeAd")
    var uyeAd: String,

    @Column(name = "uyeSoyad")
    var uyeSoyad: String,

    @Column(name = "cinsiyet")
    var cinsiyet: String,

    @Column(name = "telefon")
    var telefon: String,

    @Column(name = "eposta")
    var eposta: String,

    @Column(name = "adres_id")
    var adresId: Int,
){
    @ApiModelProperty(position = 1, required = false, hidden=true)
    @Id
    @Column(name = "uye_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var uyeId: Int? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @OneToMany(mappedBy = "refTblUyelerEntity")
    var refTblEmanetEntities: List<TblEmanetEntity>? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adres_id", referencedColumnName = "adres_id", insertable = false, updatable = false)
    var refAdreslerEntity: AdreslerEntity? = null
}


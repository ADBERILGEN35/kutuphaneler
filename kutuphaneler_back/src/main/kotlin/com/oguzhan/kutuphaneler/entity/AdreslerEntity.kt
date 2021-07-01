package com.oguzhan.kutuphaneler.entity

import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
@Table(name = "Adresler", schema = "kutuphaneler", catalog = "")
data class AdreslerEntity(
        @Column(name = "cadde")
        var cadde: String,
        @Column(name = "sokak")
        var sokak: String,
        @Column(name = "mahalle")
        var mahalle: String,
        @Column(name = "binaNo")
        var binaNo: String,
        @Column(name = "kat")
        var kat: String,
        @Column(name = "postaKodu")
        var postaKodu: String,
        @Column(name = "ilce")
        var ilce: String,
        @Column(name = "il")
        var il: String,
) {
    @ApiModelProperty(position = 1, required = false, hidden=true)
    @Id
    @Column(name = "adres_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var adresId: Int = 0

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @OneToMany(mappedBy = "refAdreslerEntity")
    var refTblKutuphaneEntities: List<TblKutuphaneEntity>? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @OneToMany(mappedBy = "refAdreslerEntity")
    var refTblUyelerEntities: List<TblUyelerEntity>? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @OneToMany(mappedBy = "refAdreslerEntity")
    var refTblYayinevleriEntities: List<TblYayinevleriEntity>? = null

}

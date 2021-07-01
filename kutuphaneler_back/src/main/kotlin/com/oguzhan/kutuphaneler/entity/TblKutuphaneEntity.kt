package com.oguzhan.kutuphaneler.entity

import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
@Table(name = "tbl_kutuphane", schema = "kutuphaneler", catalog = "")
class TblKutuphaneEntity(

        @Column(name = "kutuphaneAd")
        var kutuphaneAd: String,

        @Basic
        @Column(name = "adres_id")
        var adresId: Int,
) {
    @ApiModelProperty(position = 1, required = false, hidden=true)
    @Id
    @Column(name = "kutuphane_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var kutuphaneId: Int? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @OneToMany(mappedBy = "refTblKutuphaneEntity")
    var refKitaplarKutuphaneEntities: List<KitaplarKutuphaneEntity>? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @OneToMany(mappedBy = "refTblKutuphaneEntity")
    var refTblEmanetEntities: List<TblEmanetEntity>? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adres_id", referencedColumnName = "adres_id", insertable = false, updatable = false)
    var refAdreslerEntity: AdreslerEntity? = null
}


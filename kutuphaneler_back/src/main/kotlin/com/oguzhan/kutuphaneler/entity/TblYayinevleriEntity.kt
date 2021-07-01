package com.oguzhan.kutuphaneler.entity

import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
@Table(name = "tbl_yayinevleri", schema = "kutuphaneler", catalog = "")
data class TblYayinevleriEntity(
        @Column(name = "yayinEviAdi")
        var yayinEviAdi: String,

        @Column(name = "adres_id")
        var adresId: Int
) {
    @ApiModelProperty(position = 1, required = false, hidden=true)
    @Id
    @Column(name = "yayinEvi_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var yayinEviId: Int? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @OneToMany(mappedBy = "refTblYayinevleriEntity")
    var refTblKitaplarEntities: List<TblKitaplarEntity>? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adres_id", referencedColumnName = "adres_id", insertable = false, updatable = false)
    var refAdreslerEntity: AdreslerEntity? = null
}
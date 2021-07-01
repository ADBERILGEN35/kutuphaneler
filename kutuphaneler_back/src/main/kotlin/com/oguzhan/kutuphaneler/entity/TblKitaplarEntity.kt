package com.oguzhan.kutuphaneler.entity

import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
@Table(name = "tbl_Kitaplar", schema = "kutuphaneler", catalog = "")
data class TblKitaplarEntity(
        @Column(name = "isbn")
        var isbn: String,

        @Column(name = "kitapAdi")
        var kitapAdi: String,

        @Column(name = "yayinTarihi")
        var yayinTarihi: String,

        @Column(name = "sayfaSayisi")
        var sayfaSayisi: Int,

        @Column(name = "yayinEvi_id")
        var yayinEviId: Int,

        @Column(name = "yazar_id")
        var yazarId: Int,

        ) {
    @ApiModelProperty(position = 1, required = false, hidden = true)
    @Id
    @Column(name = "kitap_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var kitapId: Int? = null

    @ApiModelProperty(position = 1, required = false, hidden = true)
    @OneToMany(mappedBy = "refTblKitaplarEntity")
    var refKitaplarKategorilerEntities: List<KitaplarKategorilerEntity>? = null

    @ApiModelProperty(position = 1, required = false, hidden = true)
    @OneToMany(mappedBy = "refTblKitaplarEntity")
    var refKitaplarKutuphaneEntities: List<KitaplarKutuphaneEntity>? = null

    @ApiModelProperty(position = 1, required = false, hidden = true)
    @OneToMany(mappedBy = "refTblKitaplarEntity")
    var refTblEmanetEntities: List<TblEmanetEntity>? = null

    @ApiModelProperty(position = 1, required = false, hidden = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yayinEvi_id", referencedColumnName = "yayinEvi_id", insertable = false, updatable = false)
    var refTblYayinevleriEntity: TblYayinevleriEntity? = null

    @ApiModelProperty(position = 1, required = false, hidden = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yazar_id", referencedColumnName = "yazar_id", insertable = false, updatable = false)
    var refTblYazarEntity: YazarlarEntity? = null
}


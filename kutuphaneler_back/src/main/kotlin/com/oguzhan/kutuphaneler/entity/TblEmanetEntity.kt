package com.oguzhan.kutuphaneler.entity

import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
@Table(name = "tbl_Emanet", schema = "kutuphaneler", catalog = "")
data class TblEmanetEntity(
        @Column(name = "uye_id")
        var uyeId: Int,

        @Column(name = "kitap_id")
        var kitapId: Int,

        @Column(name = "kutuphane_id")
        var kutuphaneId: Int,

        @Column(name = "emanetTarihi")
        var emanetTarihi: String,

        @Column(name = "teslimTarihi")
        var teslimTarihi: String,
) {
    @ApiModelProperty(position = 1, required = false, hidden=true)
    @Id
    @Column(name = "emanet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var emanetId: Int? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uye_id", referencedColumnName = "uye_id", insertable = false, updatable = false)
    var refTblUyelerEntity: TblUyelerEntity? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitap_id", referencedColumnName = "kitap_id", insertable = false, updatable = false)
    var refTblKitaplarEntity: TblKitaplarEntity? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kutuphane_id", referencedColumnName = "kutuphane_id", insertable = false, updatable = false)
    var refTblKutuphaneEntity: TblKutuphaneEntity? = null

}


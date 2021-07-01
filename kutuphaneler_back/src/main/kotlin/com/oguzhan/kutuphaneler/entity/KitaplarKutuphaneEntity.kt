package com.oguzhan.kutuphaneler.entity

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "KitaplarKutuphane", schema = "kutuphaneler", catalog = "")
data class KitaplarKutuphaneEntity(
        @EmbeddedId
        val kitaplarKutuphaneId: KitaplarKutuphaneId,

        @Column(name = "adet")
        var adet: Int,
) {
    @Embeddable
    data class KitaplarKutuphaneId(
            @Column(name = "kitap_id")
            var kitapId: Int,

            @Column(name = "kutuphane_id")
            var kutuphaneId: Int,
    ) : Serializable

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitap_id", referencedColumnName = "kitap_id", insertable = false, updatable = false)
    var refTblKitaplarEntity: TblKitaplarEntity? = null

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kutuphane_id", referencedColumnName = "kutuphane_id", insertable = false, updatable = false)
    var refTblKutuphaneEntity: TblKutuphaneEntity? = null
}

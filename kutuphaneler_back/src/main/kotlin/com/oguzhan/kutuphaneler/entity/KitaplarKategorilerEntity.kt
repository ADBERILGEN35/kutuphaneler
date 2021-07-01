package com.oguzhan.kutuphaneler.entity

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "KitaplarKategoriler", schema = "kutuphaneler", catalog = "")
data class KitaplarKategorilerEntity(
        @EmbeddedId
        val kitaplarKategorilerId: KitaplarKategorilerId
) {
    @Embeddable
    data class KitaplarKategorilerId(
            @Column(name = "kitap_id")
            var kitapId: Int,
            @Column(name = "kategori_id")
            var kategoriId: Int,
    ) : Serializable

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitap_id", referencedColumnName = "kitap_id", insertable = false, updatable = false)
    var refTblKitaplarEntity: TblKitaplarEntity? = null
    @ApiModelProperty(position = 1, required = false, hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategori_id", referencedColumnName = "kategori_id", insertable = false, updatable = false)
    var refKategorilerEntity: KategorilerEntity? = null
}


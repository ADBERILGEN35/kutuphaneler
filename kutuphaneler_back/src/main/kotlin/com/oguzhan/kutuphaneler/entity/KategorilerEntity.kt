package com.oguzhan.kutuphaneler.entity

import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
@Table(name = "Kategoriler", schema = "kutuphaneler", catalog = "")
data class KategorilerEntity(
    @Column(name = "kategoriAdi")
    var kategoriAdi: String,
){
    @Id
    @Column(name = "kategori_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var kategoriId: Int = 0

    @ApiModelProperty(position = 1, required = false, hidden=true)
    @OneToMany(mappedBy = "refKategorilerEntity")
    var refKitaplarKategorilerEntities: List<KitaplarKategorilerEntity>? = null
}

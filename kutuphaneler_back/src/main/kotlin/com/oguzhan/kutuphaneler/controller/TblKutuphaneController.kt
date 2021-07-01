package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.entity.TblKutuphaneEntity
import com.oguzhan.kutuphaneler.model.Kutuphane
import com.oguzhan.kutuphaneler.model.TblKutuphanePayload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kutups")
class TblKutuphaneController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.tbl_kutuphane(kutuphaneAd,adres_id) VALUES (?,?);"
    val selectAllSql: String = "SELECT k.kutuphane_id, k.kutuphaneAd, " +
            "A.sokak, A.cadde, tK.kitapAdi, KK.adet " +
            " from kutuphaneler.tbl_kutuphane k " +
            "left join Adresler A on k.adres_id = A.adres_id " +
            "left join KitaplarKutuphane KK on k.kutuphane_id = KK.kutuphane_id " +
            "left join tbl_Kitaplar tK on KK.kitap_id = tK.kitap_id"
    val deleteById: String = "DELETE FROM kutuphaneler.tbl_kutuphane where kutuphane_id = ?"
    val selectById: String = "SELECT * from kutuphaneler.tbl_kutuphane where kutuphane_id = ?"

    @PostMapping
    fun createKutuphane(@RequestBody tblKutuphaneEntity: TblKutuphanePayload): Int {
        return jdbcTemplate.update(insertSql, tblKutuphaneEntity.kutuphaneAd, tblKutuphaneEntity.adres_id)
    }

    @GetMapping
    fun getAll(): List<Kutuphane> {
        val kutuphaneler = mutableListOf<Kutuphane>()
        val rows = jdbcTemplate.queryForList(selectAllSql)
        for (row in rows) {
            val obj = Kutuphane(
                    row.get("kutuphane_id") as Int,
                    row.get("kutuphaneAd") as String,
                    row.get("sokak") as String,
                    row.get("cadde") as String,
                    row.get("kitapAdi") as String?,
                    row.get("adet") as Int?
            )
            kutuphaneler.add(obj)
        }
        return kutuphaneler
    }

    @GetMapping("/{id}")
    fun getKutuphane(@PathVariable id: Int): TblKutuphaneEntity? {
        return jdbcTemplate.queryForObject(
                selectById, arrayOf<Any>(id),
                BeanPropertyRowMapper(TblKutuphaneEntity::class.java))

    }

    @DeleteMapping("/{id}")
    fun deleteByKutuphaneId(@PathVariable id: Int): Int {
        return jdbcTemplate.update(deleteById, id)
    }

}
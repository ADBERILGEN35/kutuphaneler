package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.entity.TblUyelerEntity
import com.oguzhan.kutuphaneler.model.Uye
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tbluyeler")
class TblUyelerController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.tbl_uyeler(uyeAd,uyeSoyad,cinsiyet,telefon,eposta,adres_id) VALUES (?,?,?,?,?,?);"
    val selectAllSql: String = "SELECT * from kutuphaneler.tbl_uyeler " +
            "left join Adresler A on tbl_uyeler.adres_id = A.adres_id"
    val deleteById: String = "DELETE FROM kutuphaneler.tbl_uyeler where uye_id = ?"
    val selectById: String = "SELECT * from kutuphaneler.tbl_uyeler where uye_id = ?"

    @PostMapping
    fun createUye(@RequestBody tblUyelerEntity: TblUyelerEntity): Int {
        return jdbcTemplate.update(insertSql, tblUyelerEntity.uyeAd, tblUyelerEntity.uyeSoyad, tblUyelerEntity.cinsiyet, tblUyelerEntity.telefon, tblUyelerEntity.eposta, tblUyelerEntity.adresId)
    }

    @GetMapping
    fun getAllUyeler(): List<Uye> {
        val uyeler = mutableListOf<Uye>()
        val rows = jdbcTemplate.queryForList(selectAllSql)
        for (row in rows) {
            val obj = Uye(
                    row.get("uye_id") as Int,
                    row.get("uyeAd") as String,
                    row.get("uyeSoyad") as String,
                    row.get("cinsiyet") as String,
                    row.get("telefon") as String,
                    row.get("eposta") as String,
                    row.get("sokak") as String,
                    row.get("cadde") as String,
                    )
            uyeler.add(obj)
        }
        return uyeler
    }

    @GetMapping("/{id}")
    fun getUye(@PathVariable id: Int): TblUyelerEntity? {
        return jdbcTemplate.queryForObject(
                selectById, arrayOf<Any>(id),
                BeanPropertyRowMapper(TblUyelerEntity::class.java))

    }

    @DeleteMapping("/{id}")
    fun deleteByUyeId(@PathVariable id: Int): Int {
        return jdbcTemplate.update(deleteById, id)
    }


}
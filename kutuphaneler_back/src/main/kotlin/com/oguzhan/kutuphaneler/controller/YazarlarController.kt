package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.entity.YazarlarEntity
import com.oguzhan.kutuphaneler.model.Kitap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/yazarlar")
class YazarlarController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.yazarlar(yazarAd,yazarSoyad) VALUES (?,?);"
    val selectAllSql: String = "SELECT * from kutuphaneler.yazarlar"
    val selectById: String = "SELECT * from kutuphaneler.yazarlar where yazar_id=?"
    val deleteById: String = "DELETE FROM kutuphaneler.yazarlar where yazar_id = ?"
    val selectBooksPretty: String = "select ISBN, kitapAdi, yayinTarihi " +
            " from kutuphaneler.yazarlar y " +
            "left join tbl_Kitaplar tK on tk.yazar_id = y.yazar_id " +
            "where y.yazar_id=?"
    val updateYazar: String = "UPDATE kutuphaneler.yazarlar set yazarAd=?, yazarSoyad=? where yazar_id=?"

    @GetMapping("/kitaps/{id}")
    fun getAllKitapsOfYazar(@PathVariable id: Int): List<Kitap>{
        val yazarlar = mutableListOf<Kitap>()
        val rows = jdbcTemplate.queryForList(selectBooksPretty, id)
        for (row in rows) {
            val obj = Kitap(
                    kitapAdi = row["kitapAdi"] as String,
                    ISBN = row["ISBN"] as String,
                    yayinTarihi = row["yayinTarihi"] as String,
            )
            yazarlar.add(obj)
        }
        return yazarlar
    }

    @PostMapping("/create")
    fun create(@RequestBody yazarEntity: YazarlarEntity): Int {
        return jdbcTemplate.update(insertSql, yazarEntity.yazarAd, yazarEntity.yazarSoyad)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int): YazarlarEntity? {
        return jdbcTemplate.queryForObject(
                selectById, arrayOf<Any>(id),
                BeanPropertyRowMapper(YazarlarEntity::class.java))
    }

    @GetMapping
    fun getAllYazarlar(): List<YazarlarEntity> {
        val yazarlar = mutableListOf<YazarlarEntity>()
        val rows = jdbcTemplate.queryForList(selectAllSql)
        for (row in rows) {
            val obj = YazarlarEntity(row["yazarAd"] as String, row["yazarSoyad"] as String)
            obj.yazarId = row["yazar_id"] as Int
            yazarlar.add(obj)
        }
        return yazarlar
    }

    @PutMapping
    fun updateYazar(@RequestBody yazarEntity: YazarlarEntity): Int {
        return jdbcTemplate.update(updateYazar, yazarEntity.yazarAd, yazarEntity.yazarSoyad, yazarEntity.yazarId)
    }

    @DeleteMapping("/{id}")
    fun deleteByUyeId(@PathVariable id: Int): Int {
        return jdbcTemplate.update(deleteById, id)
    }


}
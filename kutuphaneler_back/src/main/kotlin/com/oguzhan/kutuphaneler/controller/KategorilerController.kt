package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.entity.KategorilerEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kategoriler")
class KategorilerController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.kategoriler(kategoriAdi) VALUES (?);"
    val selectAllSql: String = "SELECT * from kutuphaneler.kategoriler"
    val deleteByKategoriId: String = "DELETE FROM kutuphaneler.kategoriler where kategori_id = ?"

    @GetMapping
    fun getAllKategoriler(): List<KategorilerEntity> {
        val kategoriler = mutableListOf<KategorilerEntity>()
        val rows = jdbcTemplate.queryForList(selectAllSql)
        for (row in rows) {
            val obj = KategorilerEntity(row["kategoriAdi"] as String)
            obj.kategoriId = row["kategori_id"] as Int
            kategoriler.add(obj)
        }
        return kategoriler
    }

    @DeleteMapping("/{id}")
    fun deleteByKategoriId(@PathVariable id: Int): Int {
        return jdbcTemplate.update(deleteByKategoriId, id)
    }

    @PostMapping
    fun createKategori(kategorilerEntity: KategorilerEntity): Int {
        return jdbcTemplate.update(insertSql, kategorilerEntity.kategoriAdi)
    }
}
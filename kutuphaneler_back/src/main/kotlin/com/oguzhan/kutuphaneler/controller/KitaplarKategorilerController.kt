package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.entity.KitaplarKategorilerEntity
import com.oguzhan.kutuphaneler.entity.KitaplarKutuphaneEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kitaplarkategoriler")
class KitaplarKategorilerController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.kitaplarkategoriler(kitap_id,kategori_id) VALUES (?,?);"
    val selectAllSql: String = "SELECT * from kutuphaneler.kitaplarkategoriler"
    val deleteById: String = "DELETE FROM kutuphaneler.kitaplarkategoriler where kategori_id = ?"

    @PostMapping
    fun create(@RequestBody kitaplarKategorilerEntity: KitaplarKategorilerEntity): Int {
        return jdbcTemplate.update(insertSql, kitaplarKategorilerEntity.kitaplarKategorilerId.kitapId, kitaplarKategorilerEntity.kitaplarKategorilerId.kategoriId)
    }

    @GetMapping
    fun getAllKitapKategori(): List<KitaplarKategorilerEntity> {
        val kitapKategoriler = mutableListOf<KitaplarKategorilerEntity>()
        val rows = jdbcTemplate.queryForList(selectAllSql)
        for (row in rows) {
            val obj = KitaplarKategorilerEntity(KitaplarKategorilerEntity.KitaplarKategorilerId(row["kitap_id"] as Int, row["kategori_id"] as Int))
            kitapKategoriler.add(obj)
        }
        return kitapKategoriler
    }

    @DeleteMapping("/{id}")
    fun deleteByKitapId(@PathVariable id: String): Int {
        return jdbcTemplate.update(deleteById, id)
    }

}
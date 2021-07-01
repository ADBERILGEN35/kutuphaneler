package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.entity.KitaplarKutuphaneEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kitaplarkutuphane")
class KitaplarKutuphaneController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.kitaplarkutuphane (kitap_id,kutuphane_id,adet) VALUES (?,?,?)"
    val selectAllSql: String = "SELECT * from kutuphaneler.kitaplarkutuphane"

    @PostMapping("/create")
    fun create(@RequestBody kitaplarKutuphaneEntity: KitaplarKutuphaneEntity): Int {
        return jdbcTemplate.update(insertSql, kitaplarKutuphaneEntity.kitaplarKutuphaneId.kitapId, kitaplarKutuphaneEntity.kitaplarKutuphaneId.kutuphaneId, kitaplarKutuphaneEntity.adet)
    }

    @GetMapping
    fun getAllStoks(): List<KitaplarKutuphaneEntity> {
        val stok = mutableListOf<KitaplarKutuphaneEntity>()
        val rows = jdbcTemplate.queryForList(selectAllSql)
        for (row in rows) {
            val obj = KitaplarKutuphaneEntity(KitaplarKutuphaneEntity.KitaplarKutuphaneId(row["kitap_id"] as Int, row["kutuphane_id"] as Int), row["adet"] as Int)
            stok.add(obj)
        }
        return stok
    }

}
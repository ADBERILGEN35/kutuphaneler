package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.model.Kitap
import com.oguzhan.kutuphaneler.model.KitapCreatePayload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/kitaplar")
class TblKitaplarController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.tbl_kitaplar(ISBN,kitapAdi,yayinTarihi,sayfaSayisi,yayinEvi_id, yazar_id) VALUES (?,?,?,?,?,?);"
    val selectAllSql: String = "SELECT * from kutuphaneler.tbl_kitaplar"
    val selectAllBooks: String = "SELECT k.kitap_id, k.ISBN, " +
            "k.kitapAdi, k.yayinTarihi, k.sayfaSayisi, y.yazarAd, y.yazarSoyad " +
            "FROM kutuphaneler.tbl_kitaplar k " +
            "left join kutuphaneler.yazarlar y on k.yazar_id = y.yazar_id "

    @PostMapping
    fun createKitap(@RequestBody k: KitapCreatePayload): Int {
        return jdbcTemplate.update(insertSql, k.isbn, k.kitapAdi, k.yayinTarihi, k.sayfaSayisi, k.yayinEviId, k.yazarId)
    }

    @GetMapping
    fun getAllBooks(): List<Kitap> {
        val kitaplar = mutableListOf<Kitap>()
        val rows = jdbcTemplate.queryForList(selectAllBooks)
        for (row in rows) {
            val obj = Kitap(row["kitap_id"] as Int, row["ISBN"] as String, row["kitapAdi"] as String,
                    row["yayinTarihi"] as String, row["sayfaSayisi"] as Int, row["yazarAd"] as String, row["yazarSoyad"] as String
            )
            kitaplar.add(obj)
        }
        return kitaplar
    }
}
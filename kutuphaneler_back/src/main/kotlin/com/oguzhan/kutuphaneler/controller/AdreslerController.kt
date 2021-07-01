package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.entity.AdreslerEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*

@RestController("/adresler")
class AdreslerController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.adresler(cadde,sokak,mahalle,binaNo,kat,postaKodu,ilce,il) VALUES (?,?,?,?,?,?,?,?);"
    val selectAllSql: String = "SELECT * from kutuphaneler.adresler"
    val selectById: String = "SELECT * FROM kutuphaneler.adresler where adres_id = ?"
    val deleteById: String = "DELETE FROM kutuphaneler.adresler where adres_id = ?"


    @PostMapping
    fun createAdress(@RequestBody adress: AdreslerEntity): Int {
        return jdbcTemplate.update(insertSql, adress.cadde, adress.sokak, adress.mahalle, adress.binaNo, adress.kat, adress.postaKodu, adress.ilce, adress.il)
    }

    @GetMapping("/{id}")
    fun getAdressInfo(@PathVariable id: Int): AdreslerEntity? {
        return jdbcTemplate.queryForObject(
                selectById, arrayOf<Any>(id),
                BeanPropertyRowMapper(AdreslerEntity::class.java))

    }

    @DeleteMapping("/{id}")
    fun deleteByAdressId(@PathVariable id: Int): Int {
        return jdbcTemplate.update(deleteById, id)
    }

    @GetMapping
    fun getAllAddresses(): List<AdreslerEntity> {
        val adresler = mutableListOf<AdreslerEntity>()
        val rows = jdbcTemplate.queryForList(selectAllSql)
        for (row in rows) {
                val obj = AdreslerEntity(row["cadde"] as String, row["sokak"] as String, row["mahalle"] as String, row["binaNo"] as String,
                        row["kat"] as String, row["postaKodu"] as String, row["ilce"] as String, row["il"] as String)
            adresler.add(obj)
        }
        return adresler
    }
}
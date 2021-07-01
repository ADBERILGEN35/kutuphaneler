package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.entity.KitaplarKutuphaneEntity
import com.oguzhan.kutuphaneler.entity.TblYayinevleriEntity
import com.oguzhan.kutuphaneler.entity.YazarlarEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tblyayinevleri")
class TblYayinevleriController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.tbl_yayinevleri(yayinEviAdi,adres_id) VALUES (?,?);"
    val selectAllSql: String = "SELECT * from kutuphaneler.tbl_yayinevleri"
    val deleteById: String = "DELETE FROM kutuphaneler.tbl_yayinevleri where yayinEvi_id = ?"
    val selectById: String = "SELECT * from kutuphaneler.tbl_yayinevleri where yayinEvi_id=?"


    @PostMapping("/create")
    fun create(@RequestBody tblYayinevleriEntity: TblYayinevleriEntity): Int {
        return jdbcTemplate.update(insertSql, tblYayinevleriEntity.yayinEviAdi, tblYayinevleriEntity.adresId)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int): TblYayinevleriEntity? {
        return jdbcTemplate.queryForObject(
                selectById, arrayOf<Any>(id),
                BeanPropertyRowMapper(TblYayinevleriEntity::class.java))
    }

    @GetMapping
    fun getAllYayinevler(): List<TblYayinevleriEntity> {
        val yayinevleri = mutableListOf<TblYayinevleriEntity>()
        val rows = jdbcTemplate.queryForList(selectAllSql)
        for (row in rows) {
            val obj = TblYayinevleriEntity(row["yayinEviAdi"] as String, row["adres_id"] as Int)
            yayinevleri.add(obj)
        }
        return yayinevleri
    }

    @DeleteMapping("/{id}")
    fun deleteByUyeId(@PathVariable id: Int): Int {
        return jdbcTemplate.update(deleteById, id)
    }

}
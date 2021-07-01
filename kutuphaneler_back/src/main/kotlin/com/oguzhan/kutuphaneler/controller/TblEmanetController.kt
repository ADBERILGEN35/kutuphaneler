package com.oguzhan.kutuphaneler.controller

import com.oguzhan.kutuphaneler.entity.Pretty
import com.oguzhan.kutuphaneler.entity.TblEmanetEntity
import com.oguzhan.kutuphaneler.entity.TblYayinevleriEntity
import com.oguzhan.kutuphaneler.model.UpdateEmanetPayload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*
import java.sql.Date

@RestController
@RequestMapping("/tblemanet")
class TblEmanetController {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    val insertSql: String = "INSERT INTO kutuphaneler.tbl_emanet(uye_id,kitap_id,kutuphane_id,emanetTarihi,teslimTarihi) VALUES (?,?,?,?,?);"
    val selectAllSql: String = "SELECT * from kutuphaneler.tbl_emanet"
    val deleteById: String = "DELETE FROM kutuphaneler.tbl_emanet where emanet_id = ?"
    val updateEmanet: String = "UPDATE kutuphaneler.tbl_emanet set emanetTarihi = ?, teslimTarihi = ? where emanet_id = ?"
    val selectById: String = "SELECT * from kutuphaneler.tbl_emanet where emanet_id = ?"
    val prettySql: String = "select e.emanet_id,e.emanetTarihi,e.teslimTarihi,k.kitapAdi,l.kutuphaneAd,u.uyeAd,u.uyeSoyad " +
            "from kutuphaneler.tbl_emanet e " +
            "left join kutuphaneler.tbl_Kitaplar k on e.kitap_id=k.kitap_id " +
            "left join kutuphaneler.tbl_kutuphane l on l.kutuphane_id = e.kutuphane_id " +
            "left join kutuphaneler.tbl_uyeler u on u.uye_id = e.uye_id"


    @PostMapping
    fun createAdress(@RequestBody tblEmanetEntity: TblEmanetEntity): Int {
        return jdbcTemplate.update(insertSql, tblEmanetEntity.uyeId, tblEmanetEntity.kitapId, tblEmanetEntity.kutuphaneId, tblEmanetEntity.emanetTarihi, tblEmanetEntity.teslimTarihi)
    }

    @PutMapping("/{id}")
    fun updateEmanet(@RequestBody tblEmanetEntity: UpdateEmanetPayload, @PathVariable id: Int): Int {
        return jdbcTemplate.update(updateEmanet, tblEmanetEntity.emanetTarihi, tblEmanetEntity.teslimTarihi, id)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int): TblEmanetEntity? {
        return jdbcTemplate.queryForObject(
                selectById, arrayOf<Any>(id),
                BeanPropertyRowMapper(TblEmanetEntity::class.java))
    }

    @GetMapping
    fun getAllEmanets(): List<TblEmanetEntity> {
        val emanetler = mutableListOf<TblEmanetEntity>()
        val rows = jdbcTemplate.queryForList(selectAllSql)
        for (row in rows) {
            val obj = TblEmanetEntity(row.get("uye_id") as Int, row.get("kitap_id") as Int, row.get("kutuphane_id") as Int, row.get("emanetTarihi") as String, row.get("teslimTarihi") as String)
            emanetler.add(obj)
        }
        return emanetler
    }

    @DeleteMapping("/{id}")
    fun deleteEmanetById(@PathVariable id: Int): Int {
        return jdbcTemplate.update(deleteById, id)
    }

    @GetMapping("/pretty")
    fun getPrettyAll(): MutableList<Pretty> {
        val pretty = mutableListOf<Pretty>()
        val rows = jdbcTemplate.queryForList(prettySql)
        for (row in rows) {
            val obj = Pretty(row.get("emanet_id") as Int, row.get("emanetTarihi") as String, row.get("teslimTarihi") as String, row.get("kitapAdi") as String, row.get("kutuphaneAd") as String, row.get("uyeAd") as String, row.get("uyeSoyad") as String)
            pretty.add(obj)
        }
        return pretty
    }

}
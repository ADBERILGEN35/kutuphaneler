package com.oguzhan.kutuphaneler.model

data class UpdateEmanetPayload(
        val emanetTarihi: String,
        val teslimTarihi: String,
        val emanetId: Int
)
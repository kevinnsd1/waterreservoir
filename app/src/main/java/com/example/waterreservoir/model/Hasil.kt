package com.example.waterreservoir.model

import com.google.gson.annotations.SerializedName

data class PenggunaanLiter(
	val senin: Int?,
	val selasa: Int?,
	val rabu: Int?,
	val kamis: Int?,
	val jumat: Int?,
	val sabtu: Int?,
	val minggu: Int?
)

data class Hasil(
	@field:SerializedName("ione/waterlevel")
	val waterLevel: String? = null,

	@field:SerializedName("ione/debit")
	val debit: String? = null,

	@field:SerializedName("ione/penggunaanLiter")
	val penggunaanLiter: PenggunaanLiter? = null, // Update tipe data

	@field:SerializedName("ione/jarak")
	val jarak: String? = null,

	@field:SerializedName("ione/kapasitas")
	val kapasitas: String? = null,

	@field:SerializedName("ione/suhu")
	val suhu: String? = null,

	@field:SerializedName("ione/statuspompa")
	val statusPompa: Int? = null
)

data class Waterone(
	@field:SerializedName("Hasil")
	val Hasil: Hasil? = null
)

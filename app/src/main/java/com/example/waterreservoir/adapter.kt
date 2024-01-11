package com.example.waterreservoir

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.waterreservoir.activity_bmkg
import com.example.waterreservoir.R
import com.example.waterreservoir.model.GempaItem
import com.example.waterreservoir.model.modelbmkg

class adapter(val data: modelbmkg?, val context: activity_bmkg, val _g: List<GempaItem?>)
    : ArrayAdapter<GempaItem>(context, R.layout.costum_listview, _g) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.costum_listview, null, true)

        //indexing
        var _idx = rowView.findViewById<TextView>(R.id.lst_nomor)
        //tanggal
        var _tgl = rowView.findViewById<TextView>(R.id.lst_tanggal)
        //koordinat
        var _koordinat = rowView.findViewById<TextView>(R.id.lst_koordinat)
        //wilayah
        var _wilayah = rowView.findViewById<TextView>(R.id.lst_wilayah)

        //pengisian data
        _idx.setText("#" + (position + 1).toString())
        Log.d("rachmafadhillah", "posisi: " + (position + 1))

        _tgl.setText(data?.infogempa?.gempa?.get(position)?.tanggal)
        Log.d("rachmafadhillah", "Tanggal: " + data?.infogempa?.gempa?.get(position)?.tanggal)

        _koordinat.setText(data?.infogempa?.gempa?.get(position)?.coordinates)
        Log.d("rachmafadhillah", "Koordinat: " + data?.infogempa?.gempa?.get(position)?.coordinates)

        _wilayah.setText(data?.infogempa?.gempa?.get(position)?.wilayah)
        Log.d("rachmafadhillah", "Wilayah: " + data?.infogempa?.gempa?.get(position)?.wilayah)

        return rowView
        }
}
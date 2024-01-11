package com.example.waterreservoir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.waterreservoir.config.configbmkg
import com.example.waterreservoir.model.modelbmkg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class activity_bmkg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmkg)
        val _listview = findViewById<ListView>(R.id.list_gempa)

        configbmkg().getService()
            .getgempa()
            .enqueue(object : Callback<modelbmkg>{
                override fun onResponse(
                    call: Call<modelbmkg>,
                    response: Response<modelbmkg>
                ) {
                    Log.d("rachmafadhillah", "data json: " + response.body())
                    _listview.adapter = adapter(response.body(), this@activity_bmkg,
                        response.body()?.infogempa?.gempa!!
                    )
                }

                override fun onFailure(call: Call<modelbmkg>, t: Throwable) {
                    Log.d("rachmafadhillah", "error: " + t.message.toString())
                }
            })
        }
}
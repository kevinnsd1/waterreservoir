package com.example.waterreservoir

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.waterreservoir.model.Hasil
import com.example.waterreservoir.config.dataconfig
import com.example.waterreservoir.databinding.FragmentPompaBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class fragment_pompa : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentPompaBinding? = null
    private val binding get() = _binding!!

    private lateinit var persenTextView: TextView
    private lateinit var celcius: TextView
    private lateinit var centimeter: TextView
    private lateinit var debitair: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPompaBinding.inflate(inflater, container, false)
        val view = binding.root

        persenTextView = binding.shape1
        celcius = binding.shape2
        centimeter = binding.shape3
        debitair = binding.shape4

        getDataWater()

        return view
    }

    private fun getDataWater() {
        dataconfig().getWaterReservoirService()
            .getDataWater()
            .enqueue(object : Callback<Hasil> {
                override fun onResponse(
                    call: Call<Hasil>,
                    response: Response<Hasil>
                ) {
                    Log.d("RifqiLuthfiA", "data json : " + response.body())

                    // Mengubah nilai kapasitas dari string ke integer (contoh)
                    val kapasitasString = response.body()?.kapasitas
                    try {
                        persenTextView.text = kapasitasString ?: "N/A"
                    } catch (e: NumberFormatException) {
                        Log.e("RifqiLuthfiA", "Error converting kapasitas to integer: ${e.message}")
                        persenTextView.text = "N/A"
                    }

                    // Mengatur teks pada TextView
                    celcius.text = response.body()?.suhu
                    centimeter.text = response.body()?.waterLevel
                    debitair.text = response.body()?.debit

                }

                override fun onFailure(call: Call<Hasil>, t: Throwable) {
                    Log.d("RifqiLuthfiA", "Error : " + t.message.toString())
                    // Menampilkan pesan error jika terjadi kegagalan
                    persenTextView.text = "N/A"
                    celcius.text = "N/A"
                    centimeter.text = "N/A"
                    debitair.text = "N/A"
                }
            })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_pompa().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
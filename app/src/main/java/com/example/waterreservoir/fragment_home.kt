package com.example.waterreservoir

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.db.williamchart.view.BarChartView
import com.example.waterreservoir.config.configbayu
import com.example.waterreservoir.config.dataconfig
import com.example.waterreservoir.databinding.FragmentHomeBinding
import com.example.waterreservoir.model.Hasil
import com.example.waterreservoir.model.modelbayu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class fragment_home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val lineSet = mutableListOf<Pair<String, Float>>()
    private val barSet = mutableListOf<Pair<String, Float>>()

    private lateinit var barChartView: BarChartView

    private var param1: String? = null
    private var param2: String? = null

    private val animationDuration = 1000L

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCharts()
        fetchDataFromApi()
    }

    private fun setupCharts() {
        binding.apply {
            barChart.animation.duration = animationDuration
            barChart.animate(barSet)
            lineChart.gradientFillColors = intArrayOf(
                Color.parseColor("#64B5F6"),
                Color.TRANSPARENT
            )
            lineChart.animation.duration = animationDuration
            lineChart.animate(lineSet)
            lineChart.onDataPointTouchListener = { index, _, _ ->
                tvChartData.text = lineSet.toList()[index].second.toString()
            }
        }
    }

    private fun fetchDataFromApi() {
        configbayu().getService()
            .getDataWaterG()
            .enqueue(object : Callback<modelbayu> {
                override fun onResponse(
                    call: Call<modelbayu>,
                    response: Response<modelbayu>
                ) {
                    if (response.isSuccessful) {
                        parseData(response.body())
                        updateCharts()
                    }
                }

//                override fun onFailure(call: Call<Hasil>, t: Throwable) {
//                    // Handle failure
//                }

                override fun onFailure(call: Call<modelbayu>, t: Throwable) {
                }
            })
    }

    private fun updateCharts() {
        binding.apply {
            // Add new data
            lineChart.animate(lineSet)
            barChart.animate(barSet)
        }
    }

    private fun parseData(modelAverage: modelbayu?) {
        modelAverage?.let {
            it.data?.let { data ->
                val senin = data.senin ?: 0
                val selasa = data.selasa ?: 0
                val rabu = data.rabu ?: 0
                val kamis = data.kamis ?: 0
                val jumat = data.jumat ?: 0
                val sabtu = data.sabtu ?: 0
                val minggu = data.minggu ?: 0

                lineSet.apply {
                    clear()
                    add("Senin" to senin.toFloat())
                    add("Selasa" to selasa.toFloat())
                    add("Rabu" to rabu.toFloat())
                    add("Kamis" to kamis.toFloat())
                    add("Jumat" to jumat.toFloat())
                    add("Sabtu" to sabtu.toFloat())
                    add("Minggu" to minggu.toFloat())
                }

                barSet.apply {
                    clear()
                    add("Senin" to senin.toFloat())
                    add("Selasa" to selasa.toFloat())
                    add("Rabu" to rabu.toFloat())
                    add("Kamis" to kamis.toFloat())
                    add("Jumat" to jumat.toFloat())
                    add("Sabtu" to sabtu.toFloat())
                    add("Minggu" to minggu.toFloat())
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_home().apply {
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
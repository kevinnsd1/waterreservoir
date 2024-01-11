package com.example.waterreservoir

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class profilefragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_profilefragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNavigateToActivity: Button = view.findViewById(R.id.databmkg)
        val btnNavigateToActivity2: Button = view.findViewById(R.id.CRUD)

        btnNavigateToActivity.setOnClickListener {
            navigateToActivity()
        }

        btnNavigateToActivity2.setOnClickListener {
            navigateToActivity2()
        }
    }

    private fun navigateToActivity() {
        val intent = Intent(activity, activity_bmkg::class.java)
        startActivity(intent)
    }

    private fun navigateToActivity2() {
        val intent = Intent(activity, Main2Activity::class.java)
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profilefragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

package com.example.itsmungapplication.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.itsmungapplication.R

class DeviceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_device, container, false)

      /*  val btnReg : Button = view.findViewById(R.id.btnReg)
        btnReg.setOnClickListener {
            val devicewifiFragment = DevicewifiFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, devicewifiFragment)
                .addToBackStack(null)
                .commit()
        }
*/

        return view
    }


}

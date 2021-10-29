package com.example.zippyservice.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.zippyservice.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var et: EditText
    private lateinit var sb: Button
    private lateinit var rt: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("Frank", "Inside onCreateView... about to inflate")

        var myroot = inflater.inflate(R.layout.main_fragment, container, false)
        et = myroot!!.findViewById(R.id.input_edittext)
        sb = myroot!!.findViewById(R.id.submit_button)
        rt = myroot!!.findViewById(R.id.results_textview)

        return myroot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        et.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(myview: View?, keyCode: Int, event: KeyEvent): Boolean {
                Log.i("Frank", "Inside onKey()")
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                    var zinfo = viewModel.getZipInfo(et.getText().toString())
                    Log.i("Frank", "onKey() - zinfo: [" + zinfo + "]")

                    return true
                } else
                    return false
            }
        })
        sb.setOnClickListener {
            Log.i("Frank", "Inside onClick()")

            var serviceReturn = viewModel.getZipInfo(et.getText().toString())
            Log.i("Frank", "SUBMIT: [" + serviceReturn.toString() + "]")
        }

        viewModel.thezipinfo.observe(viewLifecycleOwner, {
            rt.text = it
        })
    }
}
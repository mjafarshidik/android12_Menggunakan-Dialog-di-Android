package com.penatabahasa.chapter5topic6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.penatabahasa.chapter5topic6.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_view.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        sharedViewModel.name.observe(this) {
            tvName.text = it
        }

        showAlertDialogStandard()
        showAlertDialogWithAction()
        showAlertDialogCustomLayout()
        showDialogFragment()
    }

    private fun showDialogFragment() {
        binding.apply {
            btnDialogFragment.setOnClickListener {
                DialogWithDataFragment().show(supportFragmentManager, DialogWithDataFragment.TAG)
            }
        }
    }

    private fun showAlertDialogCustomLayout() {
        val view = View.inflate(this@MainActivity, R.layout.dialog_view, null)
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        binding.btnCustomLayout.setOnClickListener {
            dialog.show()
        }
        view.btnOke.setOnClickListener { dialog.dismiss() }
    }

    private fun showAlertDialogWithAction() {
        binding.apply {
            val alertWithActionBuilder = AlertDialog.Builder(this@MainActivity)
            alertWithActionBuilder.setTitle("AlertDialog With Action")
            alertWithActionBuilder.setMessage("Ini message AlertDialog With Action")
            alertWithActionBuilder.setCancelable(false)
            alertWithActionBuilder.setPositiveButton(
                "Oke"
            ) { _, _ -> Toast.makeText(this@MainActivity, "Oke sip", Toast.LENGTH_SHORT).show() }
            val alertWithAction = alertWithActionBuilder.create()

            btnWithAction.setOnClickListener {
                alertWithAction.show()
            }
        }
    }

    private fun showAlertDialogStandard() {
        binding.apply {
            val alertStandardBuilder = AlertDialog.Builder(this@MainActivity)
            alertStandardBuilder.setTitle("AlertDialog Standard")
            alertStandardBuilder.setMessage("Ini message AlertDialog Standard")
            val alertStandard = alertStandardBuilder.create()
            btnStandard.setOnClickListener {
                alertStandard.show()
            }
        }
    }
}
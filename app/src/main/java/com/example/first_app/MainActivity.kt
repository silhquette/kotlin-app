package com.example.first_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Register Component
    private lateinit var etBilangan1: EditText
    private lateinit var etBilangan2: EditText
    private lateinit var btnProcess: Button
    private lateinit var btnHome: Button
    private lateinit var btnScroll: Button
    private lateinit var btnRecycle: Button
    private lateinit var tvResult: TextView

    companion object{
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBilangan1 = findViewById(R.id.et_bilangan1)
        etBilangan2 = findViewById(R.id.et_bilangan2)
        btnProcess = findViewById(R.id.btn_process)
        btnHome = findViewById(R.id.btn_home)
        btnScroll = findViewById(R.id.btn_scroll)
        btnRecycle = findViewById(R.id.btn_recycle)
        tvResult = findViewById(R.id.result)

        btnProcess.setOnClickListener(this)
        btnHome.setOnClickListener(this)
        btnScroll.setOnClickListener(this)
        btnRecycle.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_process -> {
                var isEmpty = false
                var value1: Int?
                var value2: Int?
                value1 = etBilangan1.text.toString().toIntOrNull()
                value2 = etBilangan2.text.toString().toIntOrNull()

                //error fields
                if (value1 == null) {
                    isEmpty = true
                    etBilangan1.error = "field ini tidak boleh kososng"
                } else if (value2 == null) {
                    isEmpty = true
                    etBilangan2.error = "field ini tidak boleh kososng"
                }

                if (!isEmpty) {
                    tvResult.text =sum(value1, value2).toString()
                }
            }

            R.id.btn_home -> {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_scroll -> {
                val intent = Intent(this@MainActivity, ScrollableActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_recycle -> {
                val intent = Intent(this@MainActivity, RecycleViewActivity::class.java)
                startActivity(intent)
            }
        }
    }

    public fun sum(val1: Int? = 0, val2: Int? = 0): Int? = val2?.let { val1?.plus(it) }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}


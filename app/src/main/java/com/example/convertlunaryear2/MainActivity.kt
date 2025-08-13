package com.example.convertlunaryear2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextYear = findViewById<EditText>(R.id.editTextYear)
        val buttonConvert = findViewById<Button>(R.id.button1)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        val can = arrayOf("Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ", "Canh", "Tân", "Nhâm", "Quý")
        val chi = arrayOf("Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi")

        buttonConvert.setOnClickListener {
            val yearInput = editTextYear.text.toString().trim()

            if (yearInput.isEmpty()) {
                textViewResult.text = "Vui lòng nhập năm dương lịch!"
                return@setOnClickListener
            }

            val year = try {
                yearInput.toInt()
            } catch (e: NumberFormatException) {
                textViewResult.text = "Năm nhập không hợp lệ!"
                return@setOnClickListener
            }

            val canIndex = (year + 6) % 10
            val chiIndex = (year + 8) % 12
            val lunarYear = "${can[canIndex]} ${chi[chiIndex]}"

            textViewResult.text = "Năm âm lịch: $lunarYear"
        }
    }
}

package com.example.convertlunaryear

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.convertlunaryear.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bước 1: Ánh xạ View từ XML
        val editTextYear = findViewById<EditText>(R.id.editTextYear)
        val buttonConvert = findViewById<Button>(R.id.button1)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        // Bảng Can & Chi
        val can = arrayOf("Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ", "Canh", "Tân", "Nhâm", "Quý")
        val chi = arrayOf("Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi")

        // Bước 2: Gán sự kiện click cho nút
        buttonConvert.setOnClickListener {
            val yearInput = editTextYear.text.toString().trim()

            // Kiểm tra nhập trống
            if (yearInput.isEmpty()) {
                textViewResult.text = "Vui lòng nhập năm dương lịch!"
                return@setOnClickListener
            }

            // Kiểm tra nhập số hợp lệ
            val year = try {
                yearInput.toInt()
            } catch (e: NumberFormatException) {
                textViewResult.text = "Năm nhập không hợp lệ!"
                return@setOnClickListener
            }

            // Bước 3: Tính Can - Chi
            val canIndex = (year + 6) % 10
            val chiIndex = (year + 8) % 12
            val lunarYear = "${can[canIndex]} ${chi[chiIndex]}"

            // Hiển thị kết quả
            textViewResult.text = "Năm âm lịch: $lunarYear"
        }
    }
}

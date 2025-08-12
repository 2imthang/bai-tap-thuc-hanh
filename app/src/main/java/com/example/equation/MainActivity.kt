package com.example.equation  // <-- đổi cho khớp với package project của bạn

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var edta: EditText
    private lateinit var edtb: EditText
    private lateinit var edtc: EditText
    private lateinit var btnGiai: Button
    private lateinit var btnTieptuc: Button
    private lateinit var btnThoat: Button
    private lateinit var txtkq: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view
        edta = findViewById(R.id.edta)
        edtb = findViewById(R.id.edtb)
        edtc = findViewById(R.id.edtc)
        btnGiai = findViewById(R.id.btngiaipt)
        btnTieptuc = findViewById(R.id.btntieptuc)
        btnThoat = findViewById(R.id.btnthoat)
        txtkq = findViewById(R.id.txtkq)

        val dcf = DecimalFormat("0.00")   // format 2 chữ số thập phân
        val eps = 1e-9                    // ngưỡng so sánh số thực = 0

        // Nút "Giải PT"
        btnGiai.setOnClickListener {
            // Lấy chuỗi nhập
            val sa = edta.text.toString().trim()
            val sb = edtb.text.toString().trim()
            val sc = edtc.text.toString().trim()

            // Kiểm tra rỗng
            if (sa.isEmpty() || sb.isEmpty() || sc.isEmpty()) {
                txtkq.text = "Vui lòng nhập đầy đủ a, b, c!"
                return@setOnClickListener
            }

            // Chuyển sang Double an toàn
            val a = sa.toDoubleOrNull()
            val b = sb.toDoubleOrNull()
            val c = sc.toDoubleOrNull()

            if (a == null || b == null || c == null) {
                txtkq.text = "Các hệ số phải là số hợp lệ!"
                return@setOnClickListener
            }

            var result = ""

            // Xử lý trường hợp a == 0 (phương trình bậc nhất hoặc vô nghiệm)
            if (abs(a) < eps) {
                // a == 0 => bx + c = 0
                if (abs(b) < eps) {
                    // b == 0
                    result = if (abs(c) < eps) {
                        "PT vô số nghiệm"
                    } else {
                        "PT vô nghiệm"
                    }
                } else {
                    // nghiệm bậc nhất x = -c / b
                    val x = -c / b
                    result = "PT có 1 nghiệm: x = ${dcf.format(x)}"
                }
            } else {
                // a != 0 -> phương trình bậc 2
                val delta = b * b - 4.0 * a * c
                when {
                    delta < -eps -> {
                        result = "PT vô nghiệm"
                    }
                    abs(delta) <= eps -> {
                        val x = -b / (2.0 * a)
                        result = "PT có nghiệm kép: x1 = x2 = ${dcf.format(x)}"
                    }
                    else -> {
                        val sqrtDelta = sqrt(delta)
                        val x1 = (-b + sqrtDelta) / (2.0 * a)
                        val x2 = (-b - sqrtDelta) / (2.0 * a)
                        result = "PT có 2 nghiệm:\n x1 = ${dcf.format(x1)}\n x2 = ${dcf.format(x2)}"
                    }
                }
            }

            txtkq.text = result
            hideKeyboard()
        }

        // Nút "Tiếp tục": xóa trắng và focus tới ô a
        btnTieptuc.setOnClickListener {
            edta.setText("")
            edtb.setText("")
            edtc.setText("")
            txtkq.text = ""
            edta.requestFocus()
            showKeyboard()
        }

        // Nút "Thoát": đóng Activity
        btnThoat.setOnClickListener {
            finish()
        }
    }

    // Ẩn bàn phím
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.windowToken?.let { imm.hideSoftInputFromWindow(it, 0) }
    }

    // Hiện bàn phím (khi focus vào edta)
    private fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        edta.post {
            edta.requestFocus()
            imm.showSoftInput(edta, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}

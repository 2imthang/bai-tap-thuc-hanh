package com.example.bmi; // đổi tên theo package của bạn

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText edtTen, edtChieuCao, edtCanNang, edtBMI, edtChuanDoan;
    Button btnTinhBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // file XML vừa tạo

        // Ánh xạ view
        edtTen = findViewById(R.id.edtTen);
        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);
        edtBMI = findViewById(R.id.edtBMI);
        edtChuanDoan = findViewById(R.id.edtChuanDoan);
        btnTinhBMI = findViewById(R.id.btnTinhBMI);

        btnTinhBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhBMI();
            }
        });
    }

    private void tinhBMI() {
        try {
            String ten = edtTen.getText().toString().trim();
            String caoStr = edtChieuCao.getText().toString().trim();
            String nangStr = edtCanNang.getText().toString().trim();

            if (ten.isEmpty() || caoStr.isEmpty() || nangStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            double cao = Double.parseDouble(caoStr);
            double nang = Double.parseDouble(nangStr);

            if (cao <= 0 || nang <= 0) {
                Toast.makeText(this, "Chiều cao và cân nặng phải > 0!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tính BMI
            double bmi = nang / (cao * cao);
            DecimalFormat dcf = new DecimalFormat("#.##");
            edtBMI.setText(dcf.format(bmi));

            // Chuẩn đoán
            String chuanDoan;
            if (bmi < 18.5) {
                chuanDoan = "Gầy";
            } else if (bmi < 24.9) {
                chuanDoan = "Bình thường";
            } else if (bmi < 29.9) {
                chuanDoan = "Thừa cân";
            } else {
                chuanDoan = "Béo phì";
            }

            edtChuanDoan.setText(chuanDoan);

            Toast.makeText(this, "Kết quả BMI của " + ten + " đã được tính!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Lỗi dữ liệu nhập!", Toast.LENGTH_SHORT).show();
        }
    }
}

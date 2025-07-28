package com.example.projectcal; // đổi theo package của bạn

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edta, edtb, edtc;
    Button btncong, btntru, btnnhan, btnchia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // tên file XML của bạn

        // Ánh xạ các view
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtc = findViewById(R.id.edtc);

        btncong = findViewById(R.id.btncong);
        btntru = findViewById(R.id.btntru);
        btnnhan = findViewById(R.id.btnnhan);
        btnchia = findViewById(R.id.btnchia);

        // Xử lý nút Cộng
        btncong.setOnClickListener(v -> {
            if (kiemTraInput()) {
                double a = Double.parseDouble(edta.getText().toString());
                double b = Double.parseDouble(edtb.getText().toString());
                edtc.setText(String.valueOf(a + b));
            }
        });

        // Xử lý nút Trừ
        btntru.setOnClickListener(v -> {
            if (kiemTraInput()) {
                double a = Double.parseDouble(edta.getText().toString());
                double b = Double.parseDouble(edtb.getText().toString());
                edtc.setText(String.valueOf(a - b));
            }
        });

        // Xử lý nút Nhân
        btnnhan.setOnClickListener(v -> {
            if (kiemTraInput()) {
                double a = Double.parseDouble(edta.getText().toString());
                double b = Double.parseDouble(edtb.getText().toString());
                edtc.setText(String.valueOf(a * b));
            }
        });

        // Xử lý nút Chia
        btnchia.setOnClickListener(v -> {
            if (kiemTraInput()) {
                double a = Double.parseDouble(edta.getText().toString());
                double b = Double.parseDouble(edtb.getText().toString());

                if (b == 0) {
                    Toast.makeText(this, "Không thể chia cho 0!", Toast.LENGTH_SHORT).show();
                } else {
                    edtc.setText(String.valueOf(a / b));
                }
            }
        });
    }

    // Hàm kiểm tra input hợp lệ
    private boolean kiemTraInput() {
        if (edta.getText().toString().isEmpty() || edtb.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập A và B", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

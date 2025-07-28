package com.example.democonstrain;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtA, edtB, edtKQ;
    Button btnTong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // tên file XML của bạn

        // Ánh xạ view
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtKQ);
        btnTong = findViewById(R.id.btntong);

        // Xử lý sự kiện bấm nút
        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Lấy dữ liệu từ EditText
                    String strA = edtA.getText().toString().trim();
                    String strB = edtB.getText().toString().trim();

                    // Kiểm tra rỗng
                    if (strA.isEmpty() || strB.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Vui lòng nhập đủ A và B", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Chuyển sang số
                    double a = Double.parseDouble(strA);
                    double b = Double.parseDouble(strB);

                    // Tính tổng
                    double tong = a + b;

                    // Hiển thị kết quả
                    edtKQ.setText(String.valueOf(tong));

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Dữ liệu nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

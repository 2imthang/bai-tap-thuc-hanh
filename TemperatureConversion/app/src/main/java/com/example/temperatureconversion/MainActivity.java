package com.example.temperatureconversion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.DecimalFormat;

public class MainActivity extends Activity {

    EditText edtdoC, edtdoF;
    Button btncf, btnfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view
        edtdoC = findViewById(R.id.edtdoC);
        edtdoF = findViewById(R.id.edtdoF);
        btncf = findViewById(R.id.btncf);
        btnfc = findViewById(R.id.btnfc);

        // Nút chuyển từ °C sang °F
        btncf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dcf = new DecimalFormat("#.00");
                String doC = edtdoC.getText().toString();

                if (!doC.isEmpty()) {
                    double C = Double.parseDouble(doC);
                    double F = C * 1.8 + 32;
                    edtdoF.setText(dcf.format(F));
                }
            }
        });

        // Nút chuyển từ °F sang °C
        btnfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dcf = new DecimalFormat("#.00");
                String doF = edtdoF.getText().toString();

                if (!doF.isEmpty()) {
                    double F = Double.parseDouble(doF);
                    double C = (F - 32) / 1.8;
                    edtdoC.setText(dcf.format(C));
                }
            }
        });
    }
}

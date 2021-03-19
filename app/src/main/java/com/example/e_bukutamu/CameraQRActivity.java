package com.example.e_bukutamu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class CameraQRActivity extends AppCompatActivity {

    private Button btScan,bt_export;
    private TextView tvScanResult;
    DatabaseHelper databaseHelper;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_q_r);

        databaseHelper = new DatabaseHelper(this);
        databaseHandler = new DatabaseHandler(this);
        btScan = findViewById(R.id.bt_scan);
        bt_export = findViewById(R.id.bt_export);
        tvScanResult = findViewById(R.id.tv_scanresult);


        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat intent baru untuk memanggil CaptureActivity bawaan ZXing
                Intent captureIntent = new Intent(CameraQRActivity.this, CaptureActivity.class);

                // Kemudian kita mengeset pesan yang akan ditampilkan ke user saat menjalankan QRCode scanning
//                CaptureActivityIntents.setPromptMessage(captureIntent, "Barcode scanning...");

                // Melakukan startActivityForResult, untuk menangkap balikan hasil dari QR Code scanning
                startActivityForResult(captureIntent, 0);
            }
        });
        bt_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHandler.exportDB();

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                String value = data.getStringExtra("SCAN_RESULT");
                tvScanResult.setText(value);
                UserModel userModel = new UserModel();
                userModel.setId(0);
                userModel.setName(value);
                userModel.setTall("1000");
                databaseHandler.addRecord(userModel);
//                TamuModel tamuModel =new TamuModel();
//                tamuModel.setNama("value");
//                databaseHelper.addRecord(tamuModel);
                for (int i=0;i<databaseHandler.getAllRecord().size();i++){
                    Log.v("isiTamu","tamu: "+databaseHandler.getAllRecord().get(i).getName());
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                tvScanResult.setText("Scanning Gagal, mohon coba lagi.");
            }
        } else {

        }
    }


}
package com.example.soundwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnMeasure = (Button) findViewById(R.id.btnMeasure); //측정
        Button btnGroup = (Button) findViewById(R.id.btnGroup); //기록
        Button btnRecord = (Button) findViewById(R.id.btnRecord); //그룹

        btnMeasure.setOnClickListener(new View.OnClickListener() { //측정
            @Override
            public void onClick(View view) { // 새 창 MesureDecibel.java
                Intent intent = new Intent(getApplicationContext(), MesureDecibel.class);
                startActivity(intent);
            }

        });

        btnRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { // 새 창 RecodeActivity.java
                Intent intent = new Intent(getApplicationContext(), RecodeActivity.class);
                startActivity(intent);
            }
        });

        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "업데이트 예정", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
package com.example.sound_test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sound_test.model.NoiseLog;
import com.example.sound_test.repository.NoiseRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    NoiseRepository noiseRepository;
    TextView text1;
    Button button1;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noiseRepository = new NoiseRepository();
        text1 = (TextView) findViewById(R.id.text1);
        button1 = (Button) findViewById(R.id.button1);

        // test-http
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNoiseLogsToText1();
            }
        });





    }



    // test-http
    int test_userId = 1;
    int test_year = 2025;
    int test_month = 4;
    private void addNoiseLogsToText1() {
        /*
        // insert test
        NoiseLog noiseLog = new NoiseLog();
        noiseLog.setNoiseLevel(10);
        noiseLog.setAvgDb(20);
        noiseLog.setUserId(2);
        noiseLog.setLocation("여기");
        noiseLog.setLogTime(Timestamp.valueOf("2025-09-10 00:00:00"));
        noiseLog.setMaxDb(213);
        noiseRepository.insertNoiseLog(noiseLog).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Log.d("insert-test_id: ", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                Log.e("API_ERR", "request failure: "+ throwable.getMessage());
            }
        });

        Log.d("insert-test: ", noiseLog.toString());
*/
        //noiseRepository.getNoiseLogsByUserId(test_userId).enqueue(new Callback<List<NoiseLog>>() {
        noiseRepository.getMaxDecibelsForMonth(test_userId, test_year, test_month).enqueue(new Callback<List<NoiseLog>>() {
            @Override
            public void onResponse(Call<List<NoiseLog>> call, Response<List<NoiseLog>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<NoiseLog> noiseLogList = response.body();
                    // 전달받은 noiseLogs 데이터를 사용하는 UI 구현
                    // test-http-get code: textview에 전달받은 noiselog들을 기존 문자열 밑에 추가
                    StringBuilder result = new StringBuilder();
                    for (NoiseLog noiseLog : noiseLogList) {
                        result.append(noiseLog);
                    }
                    text1.setText(text1.getText()+"\n"+ result.toString());

                    // log
                    Log.d("test-http-get", i+"");
                    Log.d("RES", "server response"+i+": "+ result.toString());
                    i++;
                }
            }

            @Override
            public void onFailure(Call<List<NoiseLog>> call, Throwable throwable) {
                Log.e("API_ERR", "request failure: "+ throwable.getMessage());
            }
        });
    }
}
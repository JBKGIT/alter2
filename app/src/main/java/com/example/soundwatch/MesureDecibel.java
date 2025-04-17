package com.example.soundwatch;

import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.media.AudioRecord;

public class MesureDecibel extends MainActivity {

    boolean isRecording = false; // 측정 중인지 판단
    private static final int SAMPLE_RATE = 44100; //모든 장치에서 작동이 보장되는 유일한 속도. 헤르츠로 표현된 샘플링 속도
    private AudioRecord audioRecord; //오디오 API
    private int bufferSize; //오디오 녹음 저장 버퍼
    private TextView decibelText; // 현재 데시벨
    private Handler uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesure);

        Button btnMesureStart = (Button) findViewById(R.id.btnMesureStart); // 측정 시작

        btnMesureStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MesureDecibel.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    // 권한 요청
                    ActivityCompat.requestPermissions(MesureDecibel.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                } else {
                    if (!isRecording) {
                        startMesure(); // 측정 시작
                        btnMesureStart.setText("STOP");
                    } else {
                        stopMesure(); // 측정 중단
                        btnMesureStart.setText("START");
                    }
                }
            }
        });
    }

    private void startMesure() {
        bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        // 권한 요청2
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(MesureDecibel.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
            return;
        }
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, //마이크로 들어오는 음성을 받기 위해선 1
                SAMPLE_RATE, //샘플 헤르츠
                AudioFormat.CHANNEL_IN_MONO, //상수 값: 16(0x00000010)
                AudioFormat.ENCODING_PCM_16BIT, //샘플당 16비트 PCM
                bufferSize); // 객체 생성

        isRecording = true;
        audioRecord.startRecording(); // 녹음 시작


        Thread recordingThread = new Thread(() -> {
            short[] buffer = new short[bufferSize];

            while (isRecording) {
                int read = audioRecord.read(buffer, 0, buffer.length); // 버퍼에 저장
                double sum = 0; //RMS 계산을 위해 전체 제곱 더함
                for (int i = 0; i < read; i++) {
                    sum += buffer[i] * buffer[i];
                }

                double rms = Math.sqrt(sum / read); //RMS 계산
                double decibel = 20 * Math.log10(rms); //RMS -> dB 변환
                if (decibel < 0) decibel = 0; //음수 오류 방지

                decibelText = (TextView) findViewById(R.id.txtNowdB);
                double finalDecibel = decibel; //최종 데시벨 값
                uiHandler.post(() -> decibelText.setText(String.format("%.2f dB", finalDecibel)));

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        recordingThread.start();
    }

    private void stopMesure() { //측정 종료
        isRecording = false;
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }

    @Override
    protected void onDestroy() { //화면 종료 or 앱 종료 시
        super.onDestroy();
        isRecording = false;
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startMesure();
        } else {
            decibelText.setText("데시벨 측정을 위해 녹음 권한이 필요합니다.");
        }
    }
}

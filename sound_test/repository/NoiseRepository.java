package com.example.sound_test.repository;

import com.example.sound_test.RetrofitClient;
import com.example.sound_test.api.NoiseLogApi;
import com.example.sound_test.model.NoiseLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
// repository: API 인터페이스 생성 및 네트워크 요청 정의
public class NoiseRepository {
    private NoiseLogApi noiseLogApi;
    public NoiseRepository() {
        // API 인터페이스 생성
        noiseLogApi = RetrofitClient.getInstance().create(NoiseLogApi.class);
    }

    /* SELECT */
    // 특정 user가 가진 전체 noiseLog 가져오기
    public Call<List<NoiseLog>> getNoiseLogsByUserId(int userId) {
        return noiseLogApi.getNoiseLogsByUserId(userId);
    }

    // 캘린더에 max_db 표시를 위한 noiseLogs(log_time, max_db) 가져오기
    public Call<List<NoiseLog>> getMaxDecibelsForMonth(int userId, int year, int month) {
        Map<String, Integer> filter = new HashMap<>();
        filter.put("userId", userId);
        filter.put("year", year);
        filter.put("month", month);
        return noiseLogApi.getMaxDecibelsForMonth(filter);
    }

    /* INSERT */
    // 측정한 NoiseLog db에 저장
    public Call<Integer> insertNoiseLog(NoiseLog noiseLog) {
        return noiseLogApi.insertNoiseLog(noiseLog);
    }
}

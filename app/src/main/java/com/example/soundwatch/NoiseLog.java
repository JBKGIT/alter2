package com.example.soundwatch;

import java.sql.Timestamp;

    public class NoiseLog {
        private int id;
        private double noiseLevel;
        private Timestamp logTime; // 현재 데시벨 측정 시간
        private Timestamp startTime; // 측정 시작 시간
        private Timestamp endTime; // 측정 종료 시간
        private String location;
        private double maxDb;
        private int userId; // 사용자 ID 추가


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoiseLevel(double noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getMaxDb() {
        return maxDb;
    }

    public void setMaxDb(double maxDb) {
        this.maxDb = maxDb;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NoiseLog{" +
                "id=" + id +
                ", noiseLevel=" + noiseLevel +
                ", logTime=" + logTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", location='" + location + '\'' +
                ", maxDb=" + maxDb +
                ", userId=" + userId +
                '}';
    }
}
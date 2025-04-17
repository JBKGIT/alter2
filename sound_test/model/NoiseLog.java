package com.example.sound_test.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class NoiseLog {
    private int id;
    @SerializedName("noise_level")
    private double noiseLevel;
    @SerializedName("log_time")
    private Timestamp logTime;
    private String location;
    @SerializedName("max_db")
    private double maxDb;
    @SerializedName("avg_db")
    private double avgDb;
    @SerializedName("user_id")
    private int userId;

    // getter && setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public double getNoiseLevel() {
        return noiseLevel;
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

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public double getMaxDb() { return maxDb; }

    public void setMaxDb(double maxDb) { this.maxDb = maxDb; }

    public double getAvgDb() { return avgDb; }

    public void setAvgDb(double avgDb) { this.avgDb = avgDb; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    // toString
    @Override
    public String toString() {
        return "NoiseLog{" +
                "id=" + id +
                ", noiseLevel=" + noiseLevel +
                ", logTime=" + logTime +
                ", maxDb=" + maxDb +
                ", avgDb=" + avgDb +
                ", user_id=" + userId +
                '}';
    }
}

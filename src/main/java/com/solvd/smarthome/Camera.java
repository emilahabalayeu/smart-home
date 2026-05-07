package com.solvd.smarthome;

public class Camera {
    private Long id;
    private int resolutionMp;
    private boolean isRecording;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getResolutionMp() { return resolutionMp; }
    public void setResolutionMp(int resolutionMp) { this.resolutionMp = resolutionMp; }

    public boolean isRecording() { return isRecording; }
    public void setRecording(boolean recording) { isRecording = recording; }
}

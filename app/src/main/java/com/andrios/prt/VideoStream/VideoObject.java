package com.andrios.prt.VideoStream;

/**
 * Created by Corey on 1/3/2017.
 */
public class VideoObject {

    private String filepath;
    private String title;
    private String url;

    public VideoObject(String url, String title){

        this.url = url;
        this.title = title;
    }

    public String getFilePath() {
        return filepath;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}

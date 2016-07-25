package com.kangladevelopers.android.combodemo.downloader;

/**
 * Created by DARSHAN HUIDROJM on 7/25/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

//new YouTubePageStreamUriGetter().execute("https://www.youtube.com/watch?v=4GuqB1BQVr4");


abstract public class YouTubePageStreamUriGetter extends
        AsyncTask<String, String, String> {
    ProgressDialog progressDialog;
    Activity activity;

    public YouTubePageStreamUriGetter(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(activity, "", "Connecting to YouTube...", true);
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        try {
            ArrayList<Video> videos = CustomDownloader.getStreamingUrisFromYouTubePage(url);
            if (videos != null && !videos.isEmpty()) {
                String retVidUrl = null;
                for (Video video : videos) {
                    if (video.ext.toLowerCase().contains("mp4")
                            && video.type.toLowerCase().contains("medium")) {
                        retVidUrl = video.url;
                        break;
                    }
                }
                if (retVidUrl == null) {
                    for (Video video : videos) {
                        if (video.ext.toLowerCase().contains("3gp")
                                && video.type.toLowerCase().contains(
                                "medium")) {
                            retVidUrl = video.url;
                            break;

                        }
                    }
                }
                if (retVidUrl == null) {

                    for (Video video : videos) {
                        if (video.ext.toLowerCase().contains("mp4")
                                && video.type.toLowerCase().contains("low")) {
                            retVidUrl = video.url;
                            break;

                        }
                    }
                }
                if (retVidUrl == null) {
                    for (Video video : videos) {
                        if (video.ext.toLowerCase().contains("3gp")
                                && video.type.toLowerCase().contains("low")) {
                            retVidUrl = video.url;
                            break;
                        }
                    }
                }

                return retVidUrl;
            }
        } catch (Exception e) {
            Log.e(">>>>>>", "Couldn't get YouTube streaming URL", e);
        }
        Log.e(">>>>>>", "Couldn't get stream URI for " + url);
        return null;
    }

    @Override
    protected void onPostExecute(String streamingUrl) {
        super.onPostExecute(streamingUrl);
        progressDialog.dismiss();
        if (streamingUrl != null) {
            onCompleted(streamingUrl);
        }
    }

    abstract public void onCompleted(String url);
}

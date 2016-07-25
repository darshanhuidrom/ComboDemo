package com.kangladevelopers.android.combodemo.downloader;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by DARSHAN HUIDROJM on 7/25/2016.
 */
public class CustomDownloader {


    /*public String DownloadText(String url) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0.1)";
        HttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, userAgent);

        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        String html = "";
        InputStream in = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder str = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null)
        {
            str.append(line);
        }
        in.close();
        html = str.toString();

        return html;
    }*/

   /* public String DownloadText1(String url) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0.1)";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();


        return html;
    }*/


    public static  ArrayList<Video> getStreamingUrisFromYouTubePage(String ytUrl)
            throws IOException {
        if (ytUrl == null) {
            return null;
        }

        // Remove any query params in query string after the watch?v=<vid> in
        // e.g.
        // http://www.youtube.com/watch?v=0RUPACpf8Vs&feature=youtube_gdata_player
        int andIdx = ytUrl.indexOf('&');
        if (andIdx >= 0) {
            ytUrl = ytUrl.substring(0, andIdx);
        }

        // Get the HTML response
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0.1)";
        URL obj = new URL(ytUrl);
       // HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        /// do some hacks
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers()
                    {
                        return null;
                    }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                }
        };

        // Install the all-trusting trust manager
        try
        {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }



        ////
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
     //   con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0.1)");

        String html = "";
        InputStream in = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder str = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            str.append(line.replace("\\u0026", "&"));
        }
        in.close();
        html = str.toString();

        // Parse the HTML response and extract the streaming URIs
        if (html.contains("verify-age-thumb")) {
            Log.d(">>>>>>", "YouTube is asking for age verification. We can't handle that sorry.");
            return null;
        }

        if (html.contains("das_captcha")) {
            Log.d(">>>>>>", "Captcha found, please try with different IP address.");
            return null;
        }

        Pattern p = Pattern.compile("stream_map\": \"(.*?)?\"");
        // Pattern p = Pattern.compile("/stream_map=(.[^&]*?)\"/");
        Matcher m = p.matcher(html);
        List<String> matches = new ArrayList<String>();
        while (m.find()) {
            matches.add(m.group());
        }

        if (matches.size() != 1) {
            Log.d(">>>>>>", "Found zero or too many stream maps.");
            return null;
        }

        String urls[] = matches.get(0).split(",");
        HashMap<String, String> foundArray = new HashMap<String, String>();
        for (String ppUrl : urls) {
            String url = URLDecoder.decode(ppUrl, "UTF-8");

            Pattern p1 = Pattern.compile("itag=([0-9]+?)[&]");
            Matcher m1 = p1.matcher(url);
            String itag = null;
            if (m1.find()) {
                itag = m1.group(1);
            }

            Pattern p2 = Pattern.compile("sig=(.*?)[&]");
            Matcher m2 = p2.matcher(url);
            String sig = null;
            if (m2.find()) {
                sig = m2.group(1);
            }

            Pattern p3 = Pattern.compile("url=(.*?)[&]");
            Matcher m3 = p3.matcher(ppUrl);
            String um = null;
            if (m3.find()) {
                um = m3.group(1);
            }

            if (itag != null && sig != null && um != null) {
                foundArray.put(itag, URLDecoder.decode(um, "UTF-8") + "&"
                        + "signature=" + sig);
            }
        }

        if (foundArray.size() == 0) {
            Log.d(">>>>>>", "Couldn't find any URLs and corresponding signatures");
            return null;
        }

        HashMap<String, Meta> typeMap = new HashMap<String, Meta>();
        typeMap.put("13", new Meta("13", "3GP", "Low Quality - 176x144"));
        typeMap.put("17", new Meta("17", "3GP", "Medium Quality - 176x144"));
        typeMap.put("36", new Meta("36", "3GP", "High Quality - 320x240"));
        typeMap.put("5", new Meta("5", "FLV", "Low Quality - 400x226"));
        typeMap.put("6", new Meta("6", "FLV", "Medium Quality - 640x360"));
        typeMap.put("34", new Meta("34", "FLV", "Medium Quality - 640x360"));
        typeMap.put("35", new Meta("35", "FLV", "High Quality - 854x480"));
        typeMap.put("43", new Meta("43", "WEBM", "Low Quality - 640x360"));
        typeMap.put("44", new Meta("44", "WEBM", "Medium Quality - 854x480"));
        typeMap.put("45", new Meta("45", "WEBM", "High Quality - 1280x720"));
        typeMap.put("18", new Meta("18", "MP4", "Medium Quality - 480x360"));
        typeMap.put("22", new Meta("22", "MP4", "High Quality - 1280x720"));
        typeMap.put("37", new Meta("37", "MP4", "High Quality - 1920x1080"));
        typeMap.put("33", new Meta("38", "MP4", "High Quality - 4096x230"));

        ArrayList<Video> videos = new ArrayList<Video>();

        for (String format : typeMap.keySet()) {
            Meta meta = typeMap.get(format);

            if (foundArray.containsKey(format)) {
                Video newVideo = new Video(meta.ext, meta.type,
                        foundArray.get(format));
                videos.add(newVideo);
                Log.d(">>>>>>", "YouTube Video streaming details: ext:" + newVideo.ext
                        + ", type:" + newVideo.type + ", url:" + newVideo.url);
            }
        }

        return videos;
    }




    protected String[] extractLinks(String result) {
        //The title of the downloaded file
        String title = "bla bla bla";
        // An array of strings that will hold the links (in case of error, it will hold 1 string)
        String[] temper = new String[1];

        try{
            // Extract the "url_encoded_fmt_stream_map" attr from the flash object
            Pattern p = Pattern.compile("url_encoded_fmt_stream_map(.*?);");
            Matcher m = p.matcher(result);
            List<String> matches = new ArrayList<String>();
            while(m.find()){
                matches.add(m.group().replace("url_encoded_fmt_stream_map=", "").replace(";", ""));
            }
            String[] streamMap = null;
            List<String> links = new ArrayList<String>();

            if(matches.get(0) != null &&  matches.get(0) != ""){
                // Split the "url_encoded_fmt_stream_map" into an array of strings that hold the link values
                streamMap = matches.get(0).split("%2C");
                for (int i = 0; i < streamMap.length; i++){
                    String url = "";
                    String sig = "";

                    //Using regex, we will get the video url.
                    Pattern p2 = Pattern.compile("url%3D(.*?)%26");
                    Matcher m2 = p2.matcher(streamMap[i]);
                    List<String> matches2 = new ArrayList<String>();
                    while(m2.find()){
                        // We don't need the "url=...&" part.
                        matches2.add(m2.group().substring(6, m2.group().length() - 3));
                    }
                    url = matches2.get(0);

                    //Using regex again, we will get the video signature.
                    p2 = Pattern.compile("sig%3D(.*?)%26");
                    m2 = p2.matcher(streamMap[i]);
                    matches2 = new ArrayList<String>();
                    while(m2.find()){
                        // We don't need the "sig=...&" part.
                        matches2.add(m2.group().substring(6, m2.group().length() - 3));
                    }
                    sig = matches2.get(0);

                    //Now lets add a link to our list. Link = url + sig + title.
                    links.add(URLDecoder.decode(URLDecoder.decode(url + "&signature=", "UTF-8") + sig + "%26title%3D" + URLDecoder.decode(title, "UTF-8"), "UTF-8"));
                }
            }
            else{
                links.add("No download Links");
            }
            //Now lets make temper point to a array that holds the list items (aka links).
            temper = links.toArray(new String[links.size()]);
        }
        catch(Exception ex){
            temper[0] = ex.getMessage();
        }
        // That's it! temper has direct download links from youtube!
        return temper;
    }
}

package com.example.myapplication.API;

import java.net.*;
import java.io.*;

public class Send {
    public static void post(String url) throws IOException {
        URL reqURL = new URL(url);
        HttpURLConnection request = (HttpURLConnection) reqURL.openConnection();
        String post = "this will be the post data that you will send";
        request.setDoOutput(true);
        request.addRequestProperty("Content-Length", Integer.toString(post.length()));
        request.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // most post data is of this type
        request.setRequestMethod("POST");
        request.connect();
        OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream()); //we will write our request data here
        writer.write(post);
        writer.flush();
    }

    public static void get(String url) throws IOException {
        URL reqURL = new URL(url);
        HttpURLConnection request = (HttpURLConnection) reqURL.openConnection();
        request.setRequestMethod("GET");
        request.connect();
    }
}

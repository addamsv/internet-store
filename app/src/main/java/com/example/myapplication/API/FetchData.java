package com.example.myapplication.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class FetchData implements Callable<String> {
    private volatile String value;
    private String uri;
    public FetchData(String url) {
        uri = url;
    }

    @Override
    public String call() {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        value = "";
        try {
            URL url = new URL(uri);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer stringBuffer = new StringBuffer();

            while ((value = bufferedReader.readLine()) != null) {
                stringBuffer.append(value).append("\n");
            }

            value = stringBuffer.toString();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {}
            }
        }
        return value;
    }

}
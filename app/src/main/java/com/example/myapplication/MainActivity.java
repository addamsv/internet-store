package com.example.myapplication;

import static com.example.myapplication.API.Constants.HOST_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.API.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> userList;
    private ArrayAdapter<String> arrayAdapter;
    private Handler mainHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializerUserList();

        Button brandButton = findViewById(R.id.button_brands);
        brandButton.setOnClickListener(v -> {
            userList.clear();
            ExecutorService service = Executors.newFixedThreadPool(3);
            Future<String> stringData = service.submit(new FetchData(HOST_NAME + "brand"));
            try {
                System.out.println("Trying");
                System.out.println(stringData.get());
                JSONObject jsonObject;
                JSONArray jsonArray = null;
                try {
                    jsonObject = new JSONObject(stringData.get());
                    jsonArray = jsonObject.getJSONArray("rows");
                } catch (JSONException e) {}
                try {
                    if (jsonArray == null) {
                        jsonArray = new JSONArray(stringData.get());
                    }
                    String line = "";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        line = new JSONObject(jsonArray.get(i).toString()).getString("name");
                        userList.add(line);
                    }
                } catch (JSONException e) {}
            } catch (ExecutionException e) {
            } catch (InterruptedException e) {
            }
            service.shutdown();
            mainHandler.post(() -> arrayAdapter.notifyDataSetChanged());
        });

        Button typeButton = findViewById(R.id.button_types);
        typeButton.setOnClickListener(v -> new FetchDataa(HOST_NAME, "type").start());

        Button deviceButton = findViewById(R.id.button_device);
        deviceButton.setOnClickListener(v -> new FetchDataa(HOST_NAME, "device").start());

        listView.setOnItemClickListener((parent, view, position, id) -> System.out.println(userList.get(position) + ' ' + position));
    }

    private void initializerUserList() {
        listView = findViewById(R.id.userList);
        userList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userList);
        listView.setAdapter(arrayAdapter);
    }

    class FetchDataa extends Thread {
        private String host;
        private String endPoint;
        public FetchDataa(String _host, String _endPoint) {
            host = _host;
            endPoint = _endPoint;
        }

        @Override
        public void run() {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(host + endPoint);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                System.out.println(endPoint);

                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                String line = "";

                userList.clear();

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line).append("\n");
                }

                JSONObject jsonObject;
                JSONArray jsonArray = null;

                try {
                    jsonObject = new JSONObject(stringBuffer.toString());
                    jsonArray = jsonObject.getJSONArray("rows");
                } catch (JSONException e) {}
                try {
                    if (jsonArray == null) {
                        jsonArray = new JSONArray(stringBuffer.toString());
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        line = new JSONObject(jsonArray.get(i).toString()).getString("name");
                        System.out.println(line);
                        userList.add(line);
                    }
                } catch (JSONException e) {}

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

            mainHandler.post(() -> arrayAdapter.notifyDataSetChanged());

            /**
             mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    arrayAdapter.notifyDataSetChanged();
                }
            });
            */
        }
    }
}
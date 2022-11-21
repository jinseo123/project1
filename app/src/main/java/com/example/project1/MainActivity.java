package com.example.project1;//이 액티비티는 메인 화면

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(requestQueue == null){//큐 비어있을때 새로운 큐 생성
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        TextView city = findViewById(R.id.textView_city);
        TextView weather = findViewById(R.id.textView_weather);
        TextView tmpr = findViewById(R.id.textView_temperature);


    }

    private void CurrentWeatherCall(){
        String url = "https://api.openweathermap.org/data/2.5/weather?lat={Seoul}&lon={lon}&appid={ea24fd3871c23312ad7a1d411e67b4c5}";//lat lon 위도 경도
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String ct = jsonObject.getString("city");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
    }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }
}
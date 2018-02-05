package com.htnsoft.libraryvolley;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //volleyJsonObjectRequest();
        //volleyJsonArrayRequest();
        volleyImageRequest();
    }

    //Volley JsonObjectRequest

    private void volleyJsonObjectRequest(){
        String url = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";

        //Khởi tạo JsonObject;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String monhoc = response.getString("monhoc").toString();
                    String noihoc = response.getString("noihoc").toString();
                    String website = response.getString("website").toString();
                    String fanpage = response.getString("fanpage").toString();
                    String logo = response.getString("logo").toString();
                    Toast.makeText(MainActivity.this, monhoc + "\n" + noihoc + "\n" + website + "\n" + website +"\n" + fanpage +"\n" + logo, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);

    }

    // volleyJsonArrayRequest

    private void volleyJsonArrayRequest(){

        String url ="https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json";
        // Khởi tạo
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                for (int i=0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        String khoahoc = jsonObject.getString("khoahoc");
                        Integer hocphi = jsonObject.getInt("hocphi");

                        Toast.makeText(MainActivity.this, khoahoc + "\n" + hocphi, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Instantiate the RequestQueue;
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }

    //Volley Image Request

    private void volleyImageRequest(){

        String url ="https://2dev4u.com/wp-content/uploads/2016/10/take-a-selfie-with-js.png";
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(imageRequest);

    }

    //String request Method.Post

    private void volleyPostStringRequest(final String name, final  String email, final String avatar){
        String url ="http://dev.2dev4u.com/news/api.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
          protected Map<String, String> getParams() throws AuthFailureError {
              Map<String, String> params = new HashMap<String, String>();
              params.put("email",email);
              params.put("name",name);
              params.put("avatar", avatar);
              return params;
          }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

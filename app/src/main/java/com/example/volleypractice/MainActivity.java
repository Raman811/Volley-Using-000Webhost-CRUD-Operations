package com.example.volleypractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText id,password;
    Button login,register,update,delete,fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    id = findViewById(R.id.id);
    password = findViewById(R.id.password);
    login = findViewById(R.id.login);
    register = findViewById(R.id.register);
    update = findViewById(R.id.update);
    delete  = findViewById(R.id.delete);
    fetch = findViewById(R.id.fetch);


    login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final String userid = id.getText().toString().trim();
        final String pwd = password.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://shopingapp1.000webhostapp.com/login1.php?userid=" + userid + "&pwd=" + pwd,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        try {
                            System.out.println("+++++++++++++++++++++++++++++++" + response.toString());
                            JSONObject oo = new JSONObject(response.toString());
                            String res = oo.optString("success");
// String msg = oo.optString("message");
                            if (res.equals("1")) {
                                System.out.println("Message****" + res);
// System.out.println("Message****"+msg);
// JSONArray contacts = oo.getJSONArray("response");
                                Toast.makeText(getApplicationContext(), "valid Login", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(MainActivity.this, Insert.class);
                                i.putExtra("userid", userid);
                                startActivity(i);
                                finish();
                            } else {
// progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userid", userid);
                params.put("pwd", pwd);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    });



    register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,Register.class);
        startActivity(intent);
    }
      });
    update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,Update.class);
        startActivity(intent);
    }
     });
     delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,Delete.class);
        startActivity(intent);
    }
    });

     fetch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(MainActivity.this,Fetch_Activity.class);
             startActivity(intent);
         }
     });
    }
}

package com.example.volleypractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Update extends AppCompatActivity {
    Button btn, Update;
    EditText user, pwd1, name1, birth1, phone1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btn = (Button) findViewById(R.id.login);
        user = (EditText) findViewById(R.id.edit1);
        pwd1 = (EditText) findViewById(R.id.edit2);
        name1 = (EditText) findViewById(R.id.edit3);
        birth1 = (EditText) findViewById(R.id.edit4);
        phone1 = (EditText) findViewById(R.id.edit5);
        Update = (Button) findViewById(R.id.update);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userid = user.getText().toString();

                final String pwd = pwd1.getText().toString();

                final String name = name1.getText().toString();

                final String birth = birth1.getText().toString();

                final String phone = phone1.getText().toString();

// enab(); //edit esxt ko desable krne k liye

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://shopingapp1.000webhostapp.com/update1.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject oo = new JSONObject(response.toString());
                            String res = oo.optString("success_msg");
                            String msg = oo.optString("message");
                            Log.i("chek888888", res);
                            if (res.equals("1")) {
                                Toast.makeText(getApplicationContext(), "not updated", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "successfully updated ", Toast.LENGTH_LONG).show();

                            }


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "successfully updated", Toast.LENGTH_LONG).show();


// Toast.makeText(getApplicationContext(), "not updated "+e, Toast.LENGTH_LONG).show();


                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Update.this, "error " + error, Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();

                        params.put("userid", userid);
                        params.put("pwd", pwd);
                        params.put("name", name);
                        params.put("birth", birth);
                        params.put("phone", phone);


                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });

    }
}
package com.example.android_front;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url="https://concal-test.herokuapp.com/";//1650 0 143.6  163.8 0 1005 900.9 3
        Button btn=findViewById(R.id.btnPredict);
        EditText input=findViewById(R.id.editTextInput);
        TextView output=findViewById(R.id.txtAnswer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject((response));
                            String data= jsonObject.getString("output");
                            output.setText(data.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage().toString(),Toast.LENGTH_LONG);
                    }

                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params=new HashMap<String,String>();
                        params.put("inputs",input.getText().toString());
                        return params;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);

            }
        });

    }
}
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

        TextView output=findViewById(R.id.txtAnswer);
        EditText input1=findViewById(R.id.editTextInput);
        EditText input2=findViewById(R.id.editTextInput2);
        EditText input3=findViewById(R.id.editTextInput3);
        EditText input4=findViewById(R.id.editTextInput4);
        EditText input5=findViewById(R.id.editTextInput5);
        EditText input6=findViewById(R.id.editTextInput6);
        EditText input7=findViewById(R.id.editTextInput7);
        EditText input8=findViewById(R.id.editTextInput8);


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
                        String input=input1.getText().toString()+" "+input2.getText().toString()+" "+input3.getText().toString()+" "+input4.getText().toString()+" "+input5.getText().toString()+" "+input6.getText().toString()+" "+input7.getText().toString()+" "+input8.getText().toString();
                        params.put("inputs",input);
                        return params;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);

            }
        });

    }
}
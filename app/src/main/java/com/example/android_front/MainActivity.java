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

        String url="https://concal-backend-dp.herokuapp.com/";//1650 0 143.6  163.8 0 1005 900.9 3
        Button btn=findViewById(R.id.btnPredict);//https://concal-backend-dp.herokuapp.com/

        TextView output=findViewById(R.id.txtAnswer);
        EditText input1=findViewById(R.id.editTextInput);
        EditText input2=findViewById(R.id.editTextInput2);
        EditText input3=findViewById(R.id.editTextInput3);
        EditText input4=findViewById(R.id.editTextInput4);
        EditText input5=findViewById(R.id.editTextInput5);
        EditText input6=findViewById(R.id.editTextInput6);
        EditText input7=findViewById(R.id.editTextInput7);
        EditText input8=findViewById(R.id.editTextInput8);

        //buttons for + and -
        Button minCement=findViewById(R.id.minCement);
        Button maxCement=findViewById(R.id.maxCement);
        Button minBlast=findViewById(R.id.minBlast);
        Button maxBlast=findViewById(R.id.maxBlast);
        Button minFly=findViewById(R.id.minFly);
        Button maxFly=findViewById(R.id.maxFly);
        Button minWater=findViewById(R.id.minWater);
        Button maxWater=findViewById(R.id.maxWater);
        Button minSuper=findViewById(R.id.minSuper);
        Button maxSuper=findViewById(R.id.maxSuper);
        Button minCoarse=findViewById(R.id.minCoarse);
        Button maxCoarse=findViewById(R.id.maxCoarse);
        Button minFine=findViewById(R.id.minFineAggregate);
        Button maxFine=findViewById(R.id.maxFineAggregate);
        Button minAge=findViewById(R.id.minAge);
        Button maxAge=findViewById(R.id.maxAge);


        LoadingDialog loadingDialog=new LoadingDialog(MainActivity.this);//loading


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input1.getText().toString().equals("0") && input2.getText().toString().equals("0") && input3.getText().toString().equals("0") && input4.getText().toString().equals("0") && input5.getText().toString().equals("0") && input6.getText().toString().equals("0") && input7.getText().toString().equals("0") && input8.getText().toString().equals("0")) {
                    Toast toast=Toast.makeText(getApplicationContext(),"Empty Field",Toast.LENGTH_LONG);
                    toast.show();

                }else if (notANumInRange(input1.getText().toString()) || notANumInRange(input2.getText().toString()) || notANumInRange(input3.getText().toString()) || notANumInRange(input4.getText().toString()) || notANumInRange(input5.getText().toString()) || notANumInRange(input6.getText().toString()) || notANumInRange(input7.getText().toString()) || notANumInRange(input8.getText().toString())) {
                    Toast toast=Toast.makeText(getApplicationContext(),"All should be a number",Toast.LENGTH_LONG);
                    toast.show();

                }else if(input8.getText().toString().equals("0")||input8.getText().toString().equals("0.0")){
                    Toast toast=Toast.makeText(getApplicationContext(),"Age can't be zero",Toast.LENGTH_LONG);
                    toast.show();
                } else{
                    loadingDialog.startLoadingDialog();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject((response));
                                String data = jsonObject.getString("output");
                                output.setText(data.toString() + " MPa");
                                loadingDialog.dismissDialog();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG);
                        }

                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            String input = input1.getText().toString() + " " + input2.getText().toString() + " " + input3.getText().toString() + " " + input4.getText().toString() + " " + input5.getText().toString() + " " + input6.getText().toString() + " " + input7.getText().toString() + " " + input8.getText().toString();
                            params.put("inputs", input);
                            return params;
                        }
                    };
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);
            }

            }
        });

        //increment textfield value
        maxCement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementNum(input1);
            }
        });
        maxBlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementNum(input2);
            }
        });
        maxFly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementNum(input3);
            }
        });
        maxWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementNum(input4);
            }
        });
        maxSuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementNum(input5);
            }
        });
        maxCoarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementNum(input6);
            }
        });
        maxFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementNum(input7);
            }
        });
        maxAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementNum(input8);
            }
        });

        //decrement textfield
        minCement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementNum(input1);
            }
        });
        minBlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementNum(input2);
            }
        });
        minFly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementNum(input3);
            }
        });
        minWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementNum(input4);
            }
        });
        minSuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementNum(input5);
            }
        });
        minCoarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementNum(input6);
            }
        });
        minFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementNum(input7);
            }
        });
        minAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementNum(input8);
            }
        });


    }

    private boolean notANumInRange(String strNum){
        if (strNum == null) {
            return true;//validate num is not null
        }
        try {
            double d = Double.parseDouble(strNum);//validate string is a num
            if(d<0){
                return true;//validate num cant be minus
            }
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }
    private void incrementNum(TextView textView){
        Double newNum=Double.parseDouble(textView.getText().toString())+1;
        textView.setText(newNum.toString());
    }
    private void decrementNum(TextView textView){
        Double newNum=Double.parseDouble(textView.getText().toString())-1;
        textView.setText(newNum.toString());
    }
}
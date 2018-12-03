package com.example.thecobra.colors;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView, txtColor;
    private View view;
    private Button btnGo;
    private final String URL = "http://nce.up.edu.br/juca/colors.json";
    private JSONArray jsonArray, arrayFord, arrayBMW, arrayFiat;
    private JSONObject jsonObject, objFord, objBMW, objFiat;
    private List<Color> colors;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        txtColor = findViewById(R.id.txtColor);
        view = findViewById(R.id.view);
        btnGo = findViewById(R.id.btnGo);

        try {
            jsonObject = new JSONObject();
            jsonArray = new JSONArray();

            objFord = new JSONObject();
            objBMW = new JSONObject();
            objFiat = new JSONObject();

            arrayFord = new JSONArray();
            arrayFord.put("Fiesta");
            arrayFord.put("Focus");
            arrayFord.put("Mustang");
            objFord.put("name", "Ford").put("models", arrayFord);

            arrayBMW = new JSONArray();
            arrayBMW.put("320");
            arrayBMW.put("X2");
            arrayBMW.put("X5");
            objBMW.put("name", "BMW").put("models", arrayBMW);

            arrayFiat = new JSONArray();
            arrayFiat.put("500");
            arrayFiat.put("Panda");
            objFiat.put("name", "Fiat").put("models", arrayFiat);

            jsonArray.put(objFord);
            jsonArray.put(objBMW);
            jsonArray.put(objFiat);

            jsonObject.put("name", "John");
            jsonObject.put("age", 30);
            jsonObject.put("cars", jsonArray);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        /*String myObj = "{
                /"name/"/:/"John",
                "age":30,
                "cars": [
        { "name":"Ford", "models":[ "Fiesta", "Focus", "Mustang" ] },
        { "name":"BMW", "models":[ "320", "X3", "X5" ] },
        { "name":"Fiat", "models":[ "500", "Panda" ] }
    ]
 }"*/


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(jsonObject!= null){
                    view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    textView.setText(jsonObject.toString());

                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                /*AndroidNetworking.get(URL)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response != null) {
                                    jsonArray = response.optJSONArray("colors");
                                }
                                textView.setText(jsonArray.toString());
                                txtColor.setText(response.toString());
                            }

                            @Override
                            public void onError(ANError anError) {
                            }
                        });

                if (jsonArray != null) {
                    Toast.makeText(getApplicationContext(), "ta dando boa, eu acho", Toast.LENGTH_SHORT).show();
                    try {
                        colors = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject color = jsonArray.getJSONObject(i);
                            String name = jsonArray.getJSONObject(i).getString("color");
                            String category = jsonArray.getJSONObject(i).getString("category");
                            String type;
                            if (jsonArray.toString().contains("type")) {
                                type = jsonArray.getJSONObject(i).getString("type");
                            } else {
                                type = "null";
                            }
                            JSONArray codes = jsonArray.getJSONObject(i).getJSONArray("code");
                            ArrayList<String> code = new ArrayList<>();
                            code.add(codes.getString(0));
                            code.add(codes.getString(1));
                            Color color1 = new Color(name, category, type, code);
                            colors.add(color1);
                        }
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }

                if(colors != null){
                    txtColor.setText(colors.get(0).toString());
                }*/
            }
        });
    }
}

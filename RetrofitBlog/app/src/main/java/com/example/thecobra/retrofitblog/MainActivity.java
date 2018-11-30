package com.example.thecobra.retrofitblog;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnSend;
    private EditText unitIn, unitOut, valueIn;
    private TextView valueOutConvert, unitOutConvert, valueOutConverted, unitOutConverted;
    private ProgressDialog progress;
    private ServerResponse responseMain = new ServerResponse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitIn = findViewById(R.id.edittext_unidade_entrada);
        unitOut = findViewById(R.id.edittext_unidade_saida);
        valueIn = findViewById(R.id.edittext_valor);

        valueOutConvert = findViewById(R.id.textview_valorconverter);
        valueOutConverted = findViewById(R.id.textview_valorconvertido);

        unitOutConvert = findViewById(R.id.textview_unidadeconverter);
        unitOutConverted = findViewById(R.id.textview_unidadeconvertida);

        btnSend = findViewById(R.id.button_enviar);

        listenerButtons();
    }

    public void listenerButtons() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("Enviando...");
                progress.show();

                String unitI = unitIn.getText().toString();
                String unitO = unitOut.getText().toString();
                String value = valueIn.getText().toString();

                retrofitConverter(unitI, unitO, value);
            }
        });
    }

    public void setValues() {
        unitOutConvert.setText(unitIn.getText().toString());
        valueOutConvert.setText(valueIn.getText().toString());
        valueOutConverted.setText(unitOut.getText().toString());
        unitOutConverted.setText(responseMain.getResult());
    }

    public void retrofitConverter(String unitI, String unitO, String value) {
        final RetrofitService  retrofitService = ServiceGenerator.createService(RetrofitService.class);

        Call<ServerResponse> call = retrofitService.converterUnidade(unitI, value, unitO);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){
                    ServerResponse serverResponse = response.body();

                    if(serverResponse != null){
                        if(serverResponse.isValid()) {
                            responseMain.setFrom_type(serverResponse.getFrom_type());
                            responseMain.setFrom_value(serverResponse.getFrom_value());
                            responseMain.setResult(serverResponse.getResult());
                            responseMain.setTo_type(serverResponse.getTo_type());
                            responseMain.setValid(serverResponse.isValid());

                            progress.dismiss();
                            setValues();
                        } else {
                            Toast.makeText(getApplicationContext(), "Insira dados válidos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Resposta não encontrada", Toast.LENGTH_SHORT).show();
                    ResponseBody errorBody = response.errorBody();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na chamada", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

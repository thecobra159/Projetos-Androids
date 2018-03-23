package com.example.hansschedel.gasolinaoualcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText precoAlcool, precoGasolina;
    private Button btnVerificar;
    private TextView txtResultado;
    private String precoAlcohol = "";
    private String precoGasosa = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precoAlcool = findViewById(R.id.editxtPrecoAlcool);
        precoGasolina = findViewById(R.id.editxtPrecoGasolina);
        btnVerificar = findViewById(R.id.btnVerificar);
        txtResultado = findViewById(R.id.txtResultado);

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    precoAlcohol = precoAlcool.getText().toString();
                    precoGasosa = precoGasolina.getText().toString();
                } catch (Exception e) {
                    e.printStackTrace();

                    if(precoAlcohol.isEmpty() && precoGasosa.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Digite valores para Álcool e Gasolina!", Toast.LENGTH_SHORT).show();
                    } else if(precoAlcohol.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Digite valores para Álcool!", Toast.LENGTH_SHORT).show();
                    } else if(precoGasosa.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Digite valores para Gasolina!", Toast.LENGTH_SHORT).show();
                    }
                }

                Double valorAlcool = Double.parseDouble(precoAlcohol);
                Double valorGasolina = Double.parseDouble(precoGasosa);

                double resultado = valorAlcool / valorGasolina;

                if(resultado >= 0.7) {
                    txtResultado.setText("É melhor utilizar a Gasolina!");
                } else {
                    txtResultado.setText("É melhor utilizar o Álcool!");
                }

            }
        });
    }
}

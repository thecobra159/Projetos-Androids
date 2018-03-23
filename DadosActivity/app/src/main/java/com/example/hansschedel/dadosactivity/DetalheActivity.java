package com.example.hansschedel.dadosactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

public class DetalheActivity extends Activity {
    private ImageView btnVoltar, moeda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        btnVoltar = findViewById(R.id.btnVoltarId);
        moeda = findViewById(R.id.moedaId);

        Bundle extra = getIntent().getExtras();

        if(extra != null) {
            String opcaoEscolhida = extra.getString("opcao");

            if(opcaoEscolhida.equals("cara")) {
                moeda.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.moeda_cara));
            } else {
                moeda.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.moeda_coroa));
            }
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}

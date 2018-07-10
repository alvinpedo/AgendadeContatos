package com.example.alvin.agendadecontatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Avaliacao extends AppCompatActivity {

    private EditText txtData, txtAtual, txtPeso, txtGordura, txtMusculo, txtVisceral, txtMetabolismo, txtIdadeCorporal;
    private Button btSalvar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        btSalvar1 = findViewById(R.id.btSalvar1);
        txtData = findViewById(R.id.txtData);
        txtAtual = findViewById(R.id.txtAtual);
        txtPeso = findViewById(R.id.txtPeso);
        txtGordura = findViewById(R.id.txtGordura);
        txtMusculo = findViewById(R.id.txtMusculo);
        txtVisceral = findViewById(R.id.txtVisceral);
        txtMetabolismo = findViewById(R.id.txtMetabolismo);
        txtIdadeCorporal = findViewById(R.id.txtIdadeCorporal);

    }
}

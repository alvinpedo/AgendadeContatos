package com.example.alvin.agendadecontatos;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class Cadastro extends AppCompatActivity {

    private Cliente cliente;

    private View cadastroLayout;
    private EditText txtNome;
    private EditText txtAltura;
    private EditText txtNascimento;
    private EditText txtEmail;
    private EditText txtTelefone;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //pegando os dados que enviamos da MainActivity
        cliente = getIntent().getParcelableExtra("cliente");

        cadastroLayout = findViewById(R.id.cadastroLayout);
        txtNome        = findViewById(R.id.txtNome);
        txtAltura      = findViewById(R.id.txtAltura);
        txtNascimento  = findViewById(R.id.txtNascimento);
        txtEmail       = findViewById(R.id.txtEmail);
        txtTelefone    = findViewById(R.id.txtTelefone);
        rbMasculino    = findViewById(R.id.rbMasculino);
        rbFeminino     = findViewById(R.id.rbFeminino);
        btSalvar       = findViewById(R.id.btSalvar);

        txtNome.setText(cliente.getNome());
        txtAltura.setText(cliente.getAltura());
        txtNascimento.setText(cliente.getNascimento());
        txtEmail.setText(cliente.getEmail());
        txtTelefone.setText(cliente.getTelefone());

        if(cliente.getGenero().equals("1")){
            rbMasculino.toggle();
        }else if(cliente.getGenero().equals("2")){
            rbFeminino.toggle();
        }


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cliente.setNome(txtNome.getText().toString());
                cliente.setAltura(txtAltura.getText().toString());
                cliente.setNascimento(txtNascimento.getText().toString());
                cliente.setEmail(txtEmail.getText().toString());
                cliente.setTelefone(txtTelefone.getText().toString());

                if(rbMasculino.isChecked()){
                    cliente.setGenero("1");
                }else if(rbFeminino.isChecked()){
                    cliente.setGenero("2");
                }

                //VERIFICA SE OS CAMPOS ESTÃO VAZIOS
                if(cliente.getNome().equals("")){
                    campoVazio();
                    txtNome.requestFocus();
                    return;
                } else if(cliente.getAltura().equals("")){
                    campoVazio();
                    txtAltura.requestFocus();
                    return;
                } else if(cliente.getTelefone().equals("")){
                    campoVazio();
                    txtTelefone.requestFocus();
                    return;
                } else if(cliente.getNascimento().equals("")){
                    campoVazio();
                    txtNascimento.requestFocus();
                    return;
                } else if(cliente.getEmail().equals("")){
                    campoVazio();
                    txtEmail.requestFocus();
                    return;
                } else if(!rbMasculino.isChecked() && !rbFeminino.isChecked()) {
                    campoVazio();
                    return;
                }


                Intent i = new Intent();
                i.putExtra("cliente", cliente);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    public void campoVazio(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage("Há campos inválidos!");
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

}

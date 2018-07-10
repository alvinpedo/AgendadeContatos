package com.example.alvin.agendadecontatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

        private BancoDados helper;
        private RecyclerView clientesRecycler;
        private ClienteAdapter clienteAdapter;

        private List<Cliente> listaClientes;

        private final int REQUEST_NEW = 1;   //novo cliente
        private final int REQUEST_ALTER = 2; //alterando cliente

        private String order = "ASC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tela = new Intent(MainActivity.this, Cadastro.class);
                tela.putExtra("cliente", new Cliente());
                startActivityForResult(tela, REQUEST_NEW);
            }
        });

        helper = new BancoDados(this);
        listaClientes = helper.buscarTodos(order);

        clientesRecycler = findViewById(R.id.clientesRecycler);
        clientesRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        clientesRecycler.setLayoutManager(llm);

        clienteAdapter = new ClienteAdapter(listaClientes);
        clientesRecycler.setAdapter(clienteAdapter);

        clientesRecycler.addOnItemTouchListener(new RecyclerClickListener(getApplicationContext(),
                new RecyclerClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        abrirOpcoes(listaClientes.get(position));
                    }
                }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //criar cliente
        if(requestCode == REQUEST_NEW && resultCode == RESULT_OK){

            Cliente cliente = data.getParcelableExtra("cliente");
            helper.inserirCliente(cliente);
            listaClientes = helper.buscarTodos(order); //carrega a lista
            clienteAdapter = new ClienteAdapter(listaClientes);
            clientesRecycler.setAdapter(clienteAdapter);

            //alterar cliente
        }else if(requestCode == REQUEST_ALTER && resultCode == RESULT_OK){

            Cliente cliente = data.getParcelableExtra("cliente");
            helper.alterarCliente(cliente);
            listaClientes = helper.buscarTodos("ASC"); //carrega a lista
            clienteAdapter = new ClienteAdapter(listaClientes);
            clientesRecycler.setAdapter(clienteAdapter);
        }
    }

    private void abrirOpcoes(final Cliente c){

        final AlertDialog.Builder opcoes = new AlertDialog.Builder(this);
        opcoes.setTitle(c.getNome());
        opcoes.setItems(new CharSequence[]{"Fazer Nova Avaliação", "Editar", "Excluir", "Cancelar"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:
                                //Fazer Nova Avaliação
                                Intent avaliacao = new Intent(MainActivity.this, GraphView.class);
                                startActivity(avaliacao);
                                break;

                            case 1:
                                //Editar
                                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                                intent.putExtra("cliente", c);
                                startActivityForResult(intent, REQUEST_ALTER);
                                break;

                            case 2:
                                //Excluir
                                listaClientes.remove(c);//retiramos da lista do Recycler
                                helper.apagarCliente(c);//excluímos do banco de dados
                                clienteAdapter.notifyDataSetChanged();//atualiza os itens
                                break;

                            case 3:
                                //Cancelar
                                opcoes.setCancelable(true);
                                break;
                        }

                    }
                });
        opcoes.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.order_az) {
            order = "ASC";
        } else if(id == R.id.order_za){
            order = "DESC";
        }

        listaClientes = helper.buscarTodos(order);
        clienteAdapter = new ClienteAdapter(listaClientes);
        clientesRecycler.setAdapter(clienteAdapter);

        return true;
    }
}

package com.example.alvin.agendadecontatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private final String TABELA = "Cliente";
    private static final String DATABASE = "DadosCliente";

    public BancoDados(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT,  "
                + " nome TEXT NOT NULL, "
                + " genero TEXT NOT NULL, "
                + " altura TEXT NOT NULL, "
                + " nascimento TEXT NOT NULL, "
                + " email TEXT NOT NULL, "
                + " telefone TEXT NOT NULL);";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Cliente> buscarTodos (String order){

        List<Cliente> cliente = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + " ORDER BY nome " + order + ";", null);

        while(cursor.moveToNext()){

            Cliente cli = new Cliente();

            cli.setId(cursor.getLong(cursor.getColumnIndex("id")));
            cli.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cli.setGenero(cursor.getString(cursor.getColumnIndex("genero")));
            cli.setAltura(cursor.getString(cursor.getColumnIndex("altura")));
            cli.setNascimento(cursor.getString(cursor.getColumnIndex("nascimento")));
            cli.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            cli.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));

            cliente.add(cli);
        }

        cursor.close();
        return cliente;
    }

    public void inserirCliente (Cliente c){

        ContentValues values = new ContentValues();

        values.put("nome", c.getNome());
        values.put("genero", c.getGenero());
        values.put("altura", c.getAltura());
        values.put("nascimento", c.getNascimento());
        values.put("email", c.getEmail());
        values.put("telefone", c.getTelefone());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void alterarCliente(Cliente c){

        ContentValues values = new ContentValues();

        values.put("id", c.getId());
        values.put("nome", c.getNome());
        values.put("genero", c.getGenero());
        values.put("altura", c.getAltura());
        values.put("nascimento", c.getNascimento());
        values.put("email", c.getEmail());
        values.put("telefone", c.getTelefone());

        String[] idParaSerAlterado = {c.getId().toString()};
        getWritableDatabase().update(TABELA, values, "id=?", idParaSerAlterado);
    }

    public void apagarCliente(Cliente c){

        SQLiteDatabase db = getWritableDatabase();
        String[] args = {c.getId().toString()};
        db.delete(TABELA, "id=?", args);
    }
}

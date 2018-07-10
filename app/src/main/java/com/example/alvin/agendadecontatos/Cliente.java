package com.example.alvin.agendadecontatos;

import android.os.Parcel;
import android.os.Parcelable;

public class Cliente implements Parcelable {

    private String nome = "";
    private String genero = "";
    private String altura = "";
    private String nascimento = "";
    private String email = "";
    private String telefone = "";

    private Long id = -1L;

    Cliente(){
    }


    private Cliente(Parcel in){

        String[] data = new String[7];
        in.readStringArray(data);

        setNome(data[0]);
        setGenero(data[1]);
        setAltura(data[2]);
        setNascimento(data[3]);
        setEmail(data[4]);
        setTelefone(data[5]);
        setId(Long.parseLong(data[6]));

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
               getNome(), getGenero(), getAltura(), getNascimento(), getEmail(), getTelefone(), String.valueOf(id)
        } );
    }

    public static final Parcelable.Creator<Cliente> CREATOR= new Parcelable.Creator<Cliente>(){

        @Override
        public Cliente createFromParcel(Parcel source) {
            return new Cliente(source);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };
}

package com.example.alvin.agendadecontatos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>{

    private List<Cliente> listaClientes;

    ClienteAdapter(List<Cliente> lista){
        listaClientes = lista;
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_clientes, parent, false);
        return new ClienteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {

        Cliente c = listaClientes.get(position);

        holder.nome.setText(c.getNome());
        holder.telefone.setText(c.getTelefone());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = null;
        try {
            dataNascimento = sdf.parse(c.getNascimento());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int anos = calculaIdade(dataNascimento);
        holder.idade.setText(Integer.toString(anos) + " Anos");
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    static class ClienteViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView idade;
        TextView telefone;

        ClienteViewHolder(View view){
            super(view);

            nome = view.findViewById(R.id.Nome);
            idade = view.findViewById(R.id.Idade);
            telefone = view.findViewById(R.id.Telefone);
        }
    }

    //CALCULAR OS ANOS DO CLIENTE
    public static int calculaIdade(java.util.Date dataNasc) {

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(dataNasc);
        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            idade--;
        }
        else
        {
            if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                idade--;
            }
        }

        return idade;
    }
}

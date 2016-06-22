package br.edu.ifba.mobile.controlamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.controlamake.bd.CompraMake;
import br.edu.ifba.mobile.controlamake.bd.FachadaBD;

/**
 * Created by Letícia Porto on 27/05/2016.
 */
public class ListagemCompraMake extends AsyncTask<Void, Void, List<CompraMake>> {//paradigma generics

    private Context contexto = null;
    private ListView listaCompras = null;

    public ListagemCompraMake(Context contexto, ListView listaCompras){
        this.listaCompras= listaCompras;
        this.contexto = contexto;
    }

    @Override
    protected List<CompraMake> doInBackground(Void... params) {
        List<CompraMake> compraMakes = FachadaBD.getInstance().listarComprasMake();
        return compraMakes;
    }

    @Override
    protected void onPostExecute(List<CompraMake> compraMakes){
        if(compraMakes.isEmpty()) {
            Toast.makeText(contexto, "Lista vazia. Insira uma campra.", Toast.LENGTH_LONG).show();
        }
        else{
            ArrayAdapter<CompraMake> adaptador=new ArrayAdapter<CompraMake>(contexto,android.R.layout.simple_list_item_single_choice, compraMakes);//colcoar a lista de disciplina dentro do listview
            listaCompras.setAdapter(adaptador);
        }
    }
}

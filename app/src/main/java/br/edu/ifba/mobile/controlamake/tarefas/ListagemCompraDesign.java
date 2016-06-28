package br.edu.ifba.mobile.controlamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.controlamake.bd.CompraDesign;
import br.edu.ifba.mobile.controlamake.bd.FachadaBD;

/**
 * Created by Letícia Porto on 27/05/2016.
 */
public class ListagemCompraDesign extends AsyncTask<Void, Void, List<CompraDesign>> {//paradigma generics

    private Context contexto = null;
    private ListView listaCompras = null;

    public ListagemCompraDesign(Context contexto, ListView listaCompras){
        this.listaCompras= listaCompras;
        this.contexto = contexto;
    }

    @Override
    //Chama a lista do BD
    protected List<CompraDesign> doInBackground(Void... params) {
        List<CompraDesign> comprasDesign = FachadaBD.getInstance().listarComprasDesign();
        return comprasDesign;
    }

    @Override
    protected void onPostExecute(List<CompraDesign> compraMakes){
        if(compraMakes.isEmpty()) {
            Toast.makeText(contexto, "Lista vazia. Insira uma compra.", Toast.LENGTH_LONG).show();
        }
        else{
            ArrayAdapter<CompraDesign> adaptador=new ArrayAdapter<CompraDesign>(contexto,android.R.layout.simple_list_item_single_choice, compraMakes);//colcoar a lista de disciplina dentro do listview
            listaCompras.setAdapter(adaptador);
        }
    }
}

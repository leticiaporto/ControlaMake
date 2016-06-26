package br.edu.ifba.mobile.controlamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.controlamake.bd.CompraDesign;
import br.edu.ifba.mobile.controlamake.bd.FachadaBD;

/**
 * Created by Letícia Porto on 27/05/2016.
 */
public class RemocaoCompraDesign extends AsyncTask<Void, Void, String> {//paradigma generics

    private Context contexto = null;
    private CompraDesign compraDesign = null;

    public RemocaoCompraDesign(Context contexto, CompraDesign compraDesign){
        this.compraDesign = compraDesign;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(compraDesign.getCodigo()!=-1){
            //remove no banco de dados
            if(FachadaBD.getInstance().removerDesign(compraDesign)==0){
                mensagem="Problemas de remoção!";
            }else
                mensagem="Compra para Design removida!";
        }else{
            mensagem="Selecione uma compra para Design!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}

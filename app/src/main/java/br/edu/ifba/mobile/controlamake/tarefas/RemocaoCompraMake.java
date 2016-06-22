package br.edu.ifba.mobile.controlamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.controlamake.bd.CompraMake;
import br.edu.ifba.mobile.controlamake.bd.FachadaBD;

/**
 * Created by Letícia Porto on 27/05/2016.
 */
public class RemocaoCompraMake extends AsyncTask<Void, Void, String> {//paradigma generics

    private Context contexto = null;
    private CompraMake compraMake = null;

    public RemocaoCompraMake(Context contexto, CompraMake compraMake){
        this.compraMake = compraMake;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(compraMake.getCodigo()!=-1){
            if(FachadaBD.getInstance().removerMake(compraMake)==0){
                mensagem="Problemas de remoção!";
            }else
                mensagem="Compra para Make removida!";
        }else{
            mensagem="Selecione uma compra para Make!";
        }


        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}

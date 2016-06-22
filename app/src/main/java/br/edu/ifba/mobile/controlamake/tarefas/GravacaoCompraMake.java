package br.edu.ifba.mobile.controlamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.controlamake.bd.CompraMake;
import br.edu.ifba.mobile.controlamake.bd.FachadaBD;

/**
 * Created by Letícia Porto on 27/05/2016.
 */
public class GravacaoCompraMake extends AsyncTask<Void, Void, String> {//paradigma generics

    private Context contexto = null;
    private CompraMake compraMake = null;

    public GravacaoCompraMake(Context contexto, CompraMake compraMake){
        this.compraMake = compraMake;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo=-1;
        if(compraMake.getCodigo()==-1){
            codigo=FachadaBD.getInstance().inserirMake(compraMake);
        }else{
            codigo=FachadaBD.getInstance().atualizarMake(compraMake);
        }
        if (codigo > 0) {
            mensagem="Compra gravada com sucesso!";
        }
        else{
            mensagem="Erro de gravação!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}

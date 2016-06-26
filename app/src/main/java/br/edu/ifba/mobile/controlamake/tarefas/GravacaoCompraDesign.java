package br.edu.ifba.mobile.controlamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.controlamake.bd.CompraDesign;
import br.edu.ifba.mobile.controlamake.bd.FachadaBD;

/**
 * Created by Letícia Porto on 27/05/2016.
 */
public class GravacaoCompraDesign extends AsyncTask<Void, Void, String> {//paradigma generics

    private Context contexto = null;
    private CompraDesign compraDesign = null;

    public GravacaoCompraDesign(Context contexto, CompraDesign compraDesign){
        this.compraDesign = compraDesign;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo=-1;
        if(compraDesign.getCodigo()==-1){
            //insersão no banco de dados
            codigo=FachadaBD.getInstance().inserirDesign(compraDesign);
        }else{
            codigo=FachadaBD.getInstance().atualizarDesign(compraDesign);
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

package br.edu.ifba.mobile.controlamake.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.edu.ifba.mobile.controlamake.R;
import br.edu.ifba.mobile.controlamake.bd.CompraMake;
import br.edu.ifba.mobile.controlamake.tarefas.GravacaoCompraMake;

/**
 * Created by alunoifba on 13/05/2016.
 */
public class FragmentoCadastroMake extends Fragment {


    private static FragmentoCadastroMake instancia = null;

    public static FragmentoCadastroMake getInstancia() {
        if(instancia==null) {
            instancia= new FragmentoCadastroMake();
        }
        return instancia;
    }

    private View tela = null;

    private EditText produto =null;
    private EditText preco =null;
    private EditText data =null;

    private Button botaoGravar = null;

    private CompraMake compraMake =null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle)    {
        tela= inflador.inflate(R.layout.fragmento_cadastro_compras_make,vgrupo, false);
        preparar();
        return tela;
    }

    private void preparar(){
        produto =(EditText)tela.findViewById(R.id.produto);
        preco =(EditText) tela. findViewById(R.id.preco);
        data=(EditText) tela. findViewById(R.id.data);

        botaoGravar = (Button) tela.findViewById(R.id.botaoGravar);
        botaoGravar.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {
                                                GravacaoCompraMake gravacao=new GravacaoCompraMake(getContexto(), getCompraMake());
                                                gravacao.execute();
                                                limparCampos();
                                        }
                                    });
    }

    private Context getContexto(){
        return this.getContext();
    }

    private CompraMake getCompraMake(){
        compraMake.setProduto(produto.getText().toString());
        compraMake.setPreco(Double.valueOf(preco.getText().toString()));
        compraMake.setData(data.getText().toString());

        return compraMake;
    }

    public void exibirCompraSelecionada(){
        compraMake = FragmentoListaComprasMake.getInstancia().getCompraSelecionada();
        if(compraMake.getCodigo()==-1){
            limparCampos();
        }else{
            carregarCampos();
        }
    }

    private void limparCampos(){
        produto.setText("");
        preco.setText("0.00");
        data.setText("//");
    }

    private void carregarCampos(){
        produto.setText(compraMake.getProduto());
        preco.setText(compraMake.getPreco()+"");
        data.setText(compraMake.getData()+"");
    }
}

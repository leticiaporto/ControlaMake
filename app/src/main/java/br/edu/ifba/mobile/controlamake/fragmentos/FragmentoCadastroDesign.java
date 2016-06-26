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
import br.edu.ifba.mobile.controlamake.bd.CompraDesign;
import br.edu.ifba.mobile.controlamake.tarefas.GravacaoCompraDesign;

/**
 * Created by alunoifba on 13/05/2016.
 */
public class FragmentoCadastroDesign extends Fragment {


    private static FragmentoCadastroDesign instancia = null;

    public static FragmentoCadastroDesign getInstancia()
    {
        if(instancia==null)        {
            instancia= new FragmentoCadastroDesign();
        }
        return instancia;
    }

    private View tela = null;

    private EditText produto =null;
    private EditText preco =null;
    private EditText data =null;

    private Button botaoGravar = null;

    private CompraDesign compraDesign =null;



    @Override
    //infla a tela
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle)    {
        tela= inflador.inflate(R.layout.fragmento_cadastro_compras_design,vgrupo, false);

        preparar();
        return tela;
    }

    private void preparar(){
        //1 recebe os dados inseridos nas caixas de texto
        produto =(EditText)tela.findViewById(R.id.produto);
        preco =(EditText) tela. findViewById(R.id.preco);
        data=(EditText) tela. findViewById(R.id.data);

        botaoGravar = (Button) tela.findViewById(R.id.botaoGravar);
        botaoGravar.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {//3 manda o contexto e os dados para a gravação
                                                GravacaoCompraDesign gravacao=new GravacaoCompraDesign(getContexto(), getCompraDesign());
                                                gravacao.execute();
                                                limparCampos();
                                        }
                                    });
    }

    private Context getContexto(){
        return this.getContext();
    }

    private CompraDesign getCompraDesign(){
        //2 coloca os dados recebidos em um objeto do tipo CompraDesign
        compraDesign.setProduto(produto.getText().toString());
        compraDesign.setPreco(Double.valueOf(preco.getText().toString()));
        compraDesign.setData(data.getText().toString());

        return compraDesign;
    }

    public void exibirCompraSelecionada(){
        compraDesign = FragmentoListaComprasDesign.getInstancia().getCompraDesignSelecionada();
        if(compraDesign.getCodigo()==-1){
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
        produto.setText(compraDesign.getProduto());
        preco.setText(compraDesign.getPreco()+"");
        data.setText(compraDesign.getData()+"");
    }
}

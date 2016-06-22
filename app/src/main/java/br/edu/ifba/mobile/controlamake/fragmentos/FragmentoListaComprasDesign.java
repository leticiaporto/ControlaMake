package br.edu.ifba.mobile.controlamake.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.controlamake.R;
import br.edu.ifba.mobile.controlamake.bd.CompraDesign;
import br.edu.ifba.mobile.controlamake.tarefas.ListagemCompraDesign;
import br.edu.ifba.mobile.controlamake.tarefas.RemocaoCompraDesign;
import br.edu.ifba.mobile.controlamake.tarefas.RemocaoCompraMake;

;

/**
 * Created by Letícia Porto on 20/05/2016.
 */
public class FragmentoListaComprasDesign extends Fragment {

    private static FragmentoListaComprasDesign instancia = null; //transformando em sington

    public static FragmentoListaComprasDesign getInstancia() {
        if(instancia == null){
            instancia = new FragmentoListaComprasDesign();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle savedInstance){ //pega o arquivo .xml e transforma em arquivo na memoria
        tela = inflador.inflate(R.layout.fragmento_lista_compras_design, vgrupo, false);
        preparar();
        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflador){//cria o menu graficamente
        super.onCreateOptionsMenu(menu,inflador);
        inflador.inflate(R.menu.menu_controla_make, menu);

    }
//REMOÇÃO---------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item){//qual a ação a depender do clic
        long id= item.getItemId();
        if (id != AdapterView.INVALID_ROW_ID){
            if (id==R.id.cadastro_remover){
                RemocaoCompraDesign remocao=new RemocaoCompraDesign(this.getContext(), this.getCompraDesignSelecionada());
                remocao.execute();
                atualizar();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar() {
        lista = (ListView) tela.findViewById(R.id.listaComprasDesign);

        this.setHasOptionsMenu(true); //quero que tenha menu
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //especifica o modo de escolha

    }

    public void atualizar(){
        ListagemCompraDesign listagem = new ListagemCompraDesign(this.getContext(),lista);
        listagem.execute();
    }

    public CompraDesign getCompraDesignSelecionada(){
        CompraDesign compraDesign =new CompraDesign();
        int posicao=lista.getCheckedItemPosition();
        if(posicao!= ListView.INVALID_POSITION){
            compraDesign = (CompraDesign)lista.getItemAtPosition(posicao);
        }

        return compraDesign;
    }
}

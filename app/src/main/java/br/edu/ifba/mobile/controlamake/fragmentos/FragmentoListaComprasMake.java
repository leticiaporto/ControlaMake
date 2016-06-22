package br.edu.ifba.mobile.controlamake.fragmentos;

import android.support.v4.app.Fragment;;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.controlamake.R;
import br.edu.ifba.mobile.controlamake.bd.CompraMake;
import br.edu.ifba.mobile.controlamake.tarefas.ListagemCompraMake;
import br.edu.ifba.mobile.controlamake.tarefas.RemocaoCompraMake;

/**
 * Created by Letícia Porto on 20/05/2016.
 */
public class FragmentoListaComprasMake extends Fragment {

    private static FragmentoListaComprasMake instancia = null; //transformando em sington

    public static FragmentoListaComprasMake getInstancia() {
        if(instancia == null){
            instancia = new FragmentoListaComprasMake();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle savedInstance){ //pega o arquivo .xml e transforma em arquivo na memoria
        tela = inflador.inflate(R.layout.fragmento_lista_compras_make, vgrupo, false);
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
                RemocaoCompraMake remocao=new RemocaoCompraMake(this.getContext(), this.getCompraSelecionada());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar() {
        lista = (ListView) tela.findViewById(R.id.listaComprasMake);
        this.setHasOptionsMenu(true); //quero que tenha menu
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //especifica o modo de escolha

    }

    public void atualizar(){
        ListagemCompraMake listagem = new ListagemCompraMake(this.getContext(),lista);
        listagem.execute();
    }

    public CompraMake getCompraSelecionada(){
        CompraMake compraMake =new CompraMake();
        int posicao=lista.getCheckedItemPosition();
        if(posicao!= ListView.INVALID_POSITION){
            compraMake = (CompraMake)lista.getItemAtPosition(posicao);
        }

        return compraMake;
    }
}

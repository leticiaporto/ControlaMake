package br.edu.ifba.mobile.controlamake.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FachadaBD extends SQLiteOpenHelper {
	private static FachadaBD instancia = null;
	private static String NOME_BANCO = "ControlaMake";
	private static int VERSAO_BANCO = 1;

	public static FachadaBD criarInstancia(Context context){
		if(instancia == null){
			instancia = new FachadaBD(context);
		}
		return instancia;
	}

	public static FachadaBD getInstance(){
		return instancia;
	}

	public FachadaBD(Context context) {
		super(context, NOME_BANCO, null, VERSAO_BANCO);
	}

	private static String COMANDO_CRIACAO_TABELA_MAKE =
			"CREATE TABLE MAKE(" +
					"CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "PRODUTO TEXT, PRECO REAL, DATA TEXT)";


	private static String COMANDO_CRIACAO_TABELA_DESIGN =
			"CREATE TABLE DESIGN(" +
					"CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "PRODUTO TEXT, PRECO REAL, DATA TEXT)";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(COMANDO_CRIACAO_TABELA_MAKE);
		db.execSQL(COMANDO_CRIACAO_TABELA_DESIGN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {//para atualização de versões do software
		// TODO Auto-generated method stub
	}

	// metodos de criacao de um CRUD utilizando o SQLite
	
	public long inserirMake(CompraMake compraMake) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("PRODUTO", compraMake.getProduto());
		valores.put("PRECO", compraMake.getPreco());
		valores.put("DATA", compraMake.getData());
		long codigo = db.insert("MAKE", null, valores);
		return codigo;

	}

	public long atualizarMake(CompraMake compraMake) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("PRODUTO", compraMake.getProduto());
		valores.put("PRECO", compraMake.getPreco());
		valores.put("DATA", compraMake.getData());
		long codigo = db.update("MAKE", valores, "CODIGO = " + compraMake.getCodigo(), null);
		return codigo;
	}

	public int removerMake(CompraMake compraMake) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("MAKE","CODIGO = " + compraMake.getCodigo(), null);
	}

	public List<CompraMake> listarComprasMake() {
		List<CompraMake> comprasMake = new ArrayList<CompraMake>();
		SQLiteDatabase db = this.getReadableDatabase();
		String selecao = "SELECT CODIGO, PRODUTO, PRECO, DATA FROM MAKE";
		Cursor cursor = db.rawQuery(selecao, null);
		if (cursor != null){
			boolean temProximo=cursor.moveToFirst();
			while (temProximo){
				CompraMake compraMake = new CompraMake();
				compraMake.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
				compraMake.setProduto(cursor.getString(cursor.getColumnIndex("PRODUTO")));
				compraMake.setPreco(cursor.getFloat(cursor.getColumnIndex("PRECO")));
				compraMake.setData(cursor.getString(cursor.getColumnIndex("DATA")));
				comprasMake.add(compraMake);
				temProximo = cursor.moveToNext();
			}
		}
		return comprasMake;
	}

	public long inserirDesign(CompraDesign compraDesign) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("PRODUTO", compraDesign.getProduto());
		valores.put("PRECO", compraDesign.getPreco());
		valores.put("DATA", compraDesign.getData());
		long codigo = db.insert("DESIGN", null, valores);
		return codigo;

	}

	public long atualizarDesign(CompraDesign compraDesign) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("PRODUTO", compraDesign.getProduto());
		valores.put("PRECO", compraDesign.getPreco());
		valores.put("DATA", compraDesign.getData());
		long codigo = db.update("DESIGN", valores, "CODIGO = " + compraDesign.getCodigo(), null);
		return codigo;
	}

	public int removerDesign(CompraDesign compraDesign) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("DESIGN","CODIGO = " + compraDesign.getCodigo(), null);
	}

	public List<CompraDesign> listarComprasDesign() {
		List<CompraDesign> comprasDesign = new ArrayList<CompraDesign>();
		SQLiteDatabase db = this.getReadableDatabase();
		String selecao = "SELECT CODIGO, PRODUTO, PRECO, DATA FROM DESIGN";
		Cursor cursor = db.rawQuery(selecao, null);
		if (cursor != null){
			boolean temProximo=cursor.moveToFirst();
			while (temProximo){
				CompraDesign compraDesign = new CompraDesign();
				compraDesign.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
				compraDesign.setProduto(cursor.getString(cursor.getColumnIndex("PRODUTO")));
				compraDesign.setPreco(cursor.getFloat(cursor.getColumnIndex("PRECO")));
				compraDesign.setData(cursor.getString(cursor.getColumnIndex("DATA")));
				comprasDesign.add(compraDesign);
				temProximo = cursor.moveToNext();
			}
		}
		return comprasDesign;
	}
	//metodo chamado em ControlaMakeActivity ao clicar no envelope
	public String somaGastos(){
		SQLiteDatabase db = this.getReadableDatabase();
		String selecao1 = "SELECT SUM(PRECO) FROM MAKE AS GASTOSMAKE";
		String selecao2 = "SELECT SUM(PRECO) FROM DESIGN AS GASTOSDESIGN";
		Cursor cursor1 = db.rawQuery(selecao1, null);
		Cursor cursor2= db.rawQuery(selecao2, null);
		String gastosMake="", gastosDesign ="";
		if (cursor1 != null) {
			cursor1.moveToFirst();
			gastosMake= cursor1.getString(0);
			if (gastosMake==null)
				gastosMake="0.0";
		}
		if (cursor2 != null) {
			cursor2.moveToFirst();
			gastosDesign= cursor2.getString(0);
			if (gastosDesign==null)
				gastosDesign="0.0";
		}
		return "Make: R$"+gastosMake+" | Design: R$"+gastosDesign;
	}

	
}
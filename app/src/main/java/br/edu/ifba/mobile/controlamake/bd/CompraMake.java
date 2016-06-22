package br.edu.ifba.mobile.controlamake.bd;

public class CompraMake {
	private long codigo=-1;
	private String produto;
	private double preco = 0.0;
	private String data;

	public long getCodigo() { return codigo;	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getPreco() { return preco; }

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return produto+" - R$"+preco+" - "+data;
	}

}

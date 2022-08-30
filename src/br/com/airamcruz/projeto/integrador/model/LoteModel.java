package br.com.airamcruz.projeto.integrador.model;

import java.util.Date;

public class LoteModel {
	private int id;
	private String descricao;
	private Date dataCompra;
	private int quantidadeFrangos;
	private Date previsaoAbate;
	private AviarioModel aviarioModel;
	
	public LoteModel() {
		super();
	}
	
	public LoteModel(int id) {
		super();
		this.id = id;
	}
	
	public LoteModel(int id, String descricao, Date dataCompra, int quantidadeFrangos, Date previsaoAbate, AviarioModel aviarioModel) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCompra = dataCompra;
		this.quantidadeFrangos = quantidadeFrangos;
		this.previsaoAbate = previsaoAbate;
		this.aviarioModel = aviarioModel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public int getQuantidadeFrangos() {
		return quantidadeFrangos;
	}

	public void setQuantidadeFrangos(int quantidadeFrangos) {
		this.quantidadeFrangos = quantidadeFrangos;
	}

	public Date getPrevisaoAbate() {
		return previsaoAbate;
	}

	public void setPrevisaoAbate(Date previsaoAbate) {
		this.previsaoAbate = previsaoAbate;
	}

	public AviarioModel getAviarioModel() {
		return aviarioModel;
	}

	public void setAviarioModel(AviarioModel aviarioModel) {
		this.aviarioModel = aviarioModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoteModel other = (LoteModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

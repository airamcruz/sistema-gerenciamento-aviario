package br.com.airamcruz.projeto.integrador.model;

import java.util.Date;

public class LoteModel {
	private int id;
	private String descricao;
	private Date dataCompra;
	private int quantidadeFrangos;
	private boolean finalizado;
	private double custoCompra;
	private Date previsaoAbate;
	private AviarioModel aviarioModel;
	
	public LoteModel() {
		super();
	}
	public LoteModel(int id) {
		super();
		this.id = id;
	}
	public LoteModel(int id, String descricao, Date dataCompra, int quantidadeFrangos, boolean finalizado,
			double custoCompra, Date previsaoAbate, AviarioModel aviarioModel) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCompra = dataCompra;
		this.quantidadeFrangos = quantidadeFrangos;
		this.finalizado = finalizado;
		this.custoCompra = custoCompra;
		this.previsaoAbate = previsaoAbate;
		this.aviarioModel = aviarioModel;
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
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public double getCustoCompra() {
		return custoCompra;
	}
	public void setCustoCompra(double custoCompra) {
		this.custoCompra = custoCompra;
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
	public int getId() {
		return id;
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

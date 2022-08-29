package br.com.airamcruz.projeto.integrador.model;

import java.util.Date;

public class MortalidadeModel {
	private int id;
	private String motivoMortalidade;
	private Date dataMortalidade;
	private int quantidadeFrangos;
	private LoteModel loteModel;
	
	public MortalidadeModel() {
		super();
	}
	public MortalidadeModel(int id) {
		super();
		this.id = id;
	}
	public MortalidadeModel(int id, String motivoMortalidade, Date dataMortalidade, int quantidadeFrangos,
			LoteModel loteModel) {
		super();
		this.id = id;
		this.motivoMortalidade = motivoMortalidade;
		this.dataMortalidade = dataMortalidade;
		this.quantidadeFrangos = quantidadeFrangos;
		this.loteModel = loteModel;
	}
	public String getMotivoMortalidade() {
		return motivoMortalidade;
	}
	public void setMotivoMortalidade(String motivoMortalidade) {
		this.motivoMortalidade = motivoMortalidade;
	}
	public Date getDataMortalidade() {
		return dataMortalidade;
	}
	public void setDataMortalidade(Date dataMortalidade) {
		this.dataMortalidade = dataMortalidade;
	}
	public int getQuantidadeFrangos() {
		return quantidadeFrangos;
	}
	public void setQuantidadeFrangos(int quantidadeFrangos) {
		this.quantidadeFrangos = quantidadeFrangos;
	}
	public LoteModel getLoteModel() {
		return loteModel;
	}
	public void setLoteModel(LoteModel loteModel) {
		this.loteModel = loteModel;
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
		MortalidadeModel other = (MortalidadeModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

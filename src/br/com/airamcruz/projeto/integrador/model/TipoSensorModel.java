package br.com.airamcruz.projeto.integrador.model;

public class TipoSensorModel {
	private int id;
	private String descricao;
	
	public TipoSensorModel() {
		super();
	}

	public TipoSensorModel(int id) {
		super();
		this.id = id;
	}

	public TipoSensorModel(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public TipoSensorModel(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		TipoSensorModel other = (TipoSensorModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

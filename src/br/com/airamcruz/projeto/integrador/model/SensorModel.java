package br.com.airamcruz.projeto.integrador.model;

import java.util.Date;

public class SensorModel {
	private int id;
	private String descricao;
	private Date dataInstalacao;
	private AviarioModel aviarioModel;
	private TipoSensorModel tipoSensorModel;
	
	public SensorModel() {
		super();
	}
	
	public SensorModel(int id) {
		super();
		this.id = id;
	}
	
	public SensorModel(int id, String descricao, Date dataInstalacao, AviarioModel aviarioModel,
			TipoSensorModel tipoSensorModel) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataInstalacao = dataInstalacao;
		this.aviarioModel = aviarioModel;
		this.tipoSensorModel = tipoSensorModel;
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

	public Date getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(Date dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public AviarioModel getAviarioModel() {
		return aviarioModel;
	}

	public void setAviarioModel(AviarioModel aviarioModel) {
		this.aviarioModel = aviarioModel;
	}

	public TipoSensorModel getTipoSensorModel() {
		return tipoSensorModel;
	}

	public void setTipoSensorModel(TipoSensorModel tipoSensorModel) {
		this.tipoSensorModel = tipoSensorModel;
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
		SensorModel other = (SensorModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SensorModel [descricao=" + descricao + "]";
	}
	
	
}

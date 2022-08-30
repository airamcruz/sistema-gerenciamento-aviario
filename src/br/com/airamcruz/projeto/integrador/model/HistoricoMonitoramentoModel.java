package br.com.airamcruz.projeto.integrador.model;

import java.util.Date;

public class HistoricoMonitoramentoModel {
	private int id;
	private String valor;
	private Date horarioMonitoramento;
	private SensorModel sensorModel;
	
	public HistoricoMonitoramentoModel() {
		super();
	}
	public HistoricoMonitoramentoModel(int id) {
		super();
		this.id = id;
	}
	public HistoricoMonitoramentoModel(int id, String valor, SensorModel sensorModel) {
		super();
		this.id = id;
		this.valor = valor;
		this.sensorModel = sensorModel;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public SensorModel getSensorModel() {
		return sensorModel;
	}
	public void setSensorModel(SensorModel sensorModel) {
		this.sensorModel = sensorModel;
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
		HistoricoMonitoramentoModel other = (HistoricoMonitoramentoModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

package br.com.airamcruz.projeto.integrador.model;

import java.util.Date;

import br.com.airamcruz.projeto.integrador.util.enums.TipoFluxoCaixaEnum;


public class FluxoCaixaModel {
	private int id;
	private Date data;
	private TipoFluxoCaixaEnum tipoFluxoCaixa;
	private double valor;
	private UsuarioModel usuarioModel;
	
	public FluxoCaixaModel() {
		super();
	}

	public FluxoCaixaModel(int id) {
		super();
		this.id = id;
	}

	public FluxoCaixaModel(int id, Date data, TipoFluxoCaixaEnum tipoFluxoCaixa, double valor,
			UsuarioModel usuarioModel) {
		super();
		this.id = id;
		this.data = data;
		this.tipoFluxoCaixa = tipoFluxoCaixa;
		this.valor = valor;
		this.usuarioModel = usuarioModel;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public TipoFluxoCaixaEnum getTipoFluxoCaixa() {
		return tipoFluxoCaixa;
	}
	public void setTipoFluxoCaixa(TipoFluxoCaixaEnum tipoFluxoCaixa) {
		this.tipoFluxoCaixa = tipoFluxoCaixa;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
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
		FluxoCaixaModel other = (FluxoCaixaModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

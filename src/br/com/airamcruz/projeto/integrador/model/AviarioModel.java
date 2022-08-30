package br.com.airamcruz.projeto.integrador.model;

import br.com.airamcruz.projeto.integrador.util.enums.EstadoAviarioEnum;

public class AviarioModel {
	private int id;
	private String descricao;
	private EstadoAviarioEnum estadoAviario;
	private UsuarioModel usuarioModel;
	
	public AviarioModel() {
		super();
	}
	
	public AviarioModel(int id) {
		super();
		this.id = id;
	}

	public AviarioModel(int id, String descricao, EstadoAviarioEnum estadoAviario, UsuarioModel usuarioModel) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.estadoAviario = estadoAviario;
		this.usuarioModel = usuarioModel;
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

	public EstadoAviarioEnum getEstadoAviario() {
		return estadoAviario;
	}

	public void setEstadoAviario(EstadoAviarioEnum estadoAviario) {
		this.estadoAviario = estadoAviario;
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
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
		AviarioModel other = (AviarioModel) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}

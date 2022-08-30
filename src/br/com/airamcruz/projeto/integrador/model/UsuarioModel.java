package br.com.airamcruz.projeto.integrador.model;

import java.util.List;

import br.com.airamcruz.projeto.integrador.util.enums.PerfilUsuarioEnum;

public class UsuarioModel {
	private int id;
	private String nome;
	private String email;
	private String cpf;
	private PerfilUsuarioEnum perfilUsuario;
	private String senha;
	private List<AviarioModel> listaAviarioModel;
	
	public UsuarioModel() {
		super();
	}
	
	public UsuarioModel(int id) {
		super();
		this.id = id;
	}
	
	public UsuarioModel(int id, String nome, String email, String cpf, PerfilUsuarioEnum perfilUsuario, String senha,
			List<AviarioModel> listaAviarioModel) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.perfilUsuario = perfilUsuario;
		this.senha = senha;
		this.listaAviarioModel = listaAviarioModel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public PerfilUsuarioEnum getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(PerfilUsuarioEnum perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<AviarioModel> getListaAviarioModel() {
		return listaAviarioModel;
	}

	public void setListaAviarioModel(List<AviarioModel> listaAviarioModel) {
		this.listaAviarioModel = listaAviarioModel;
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
		UsuarioModel other = (UsuarioModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

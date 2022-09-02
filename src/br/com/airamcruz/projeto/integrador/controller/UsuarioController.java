package br.com.airamcruz.projeto.integrador.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.airamcruz.projeto.integrador.dao.UsuarioDAO;
import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.enums.PerfilUsuarioEnum;

public class UsuarioController {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public void Inserir(String nome, String email, String cpf, PerfilUsuarioEnum perfilUsuario, String senha) {
		
	}
	
	public void Obter(int id) {
		UsuarioModel model = this.usuarioDAO.Obter(new UsuarioModel(id));
	}
	
	public ArrayList<String[]> ObterTodos() {
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		for(UsuarioModel model : usuarioDAO.ObterTodos()) {
			
		}
		
		return result;
	}
	
	public void Atualizar(int id, String nome, String email, String cpf, PerfilUsuarioEnum perfilUsuario, String senha) {

		UsuarioModel model = new UsuarioModel(id);
		model.setNome(nome);
		model.setCpf(cpf);
		model.setEmail(email);
		model.setPerfilUsuario(perfilUsuario);
		model.setSenha(senha);

		int result = this.usuarioDAO.Atualizar(model);
		
	}
	
	public void Excluir(int id) {
		int result = this.usuarioDAO.Excluir(new UsuarioModel(id));
	}

}

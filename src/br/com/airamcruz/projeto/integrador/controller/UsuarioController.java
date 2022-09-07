package br.com.airamcruz.projeto.integrador.controller;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;
import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.enums.PerfilUsuarioEnum;

public class UsuarioController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	public int Inserir(String nome, String email, String cpf, String perfilUsuario, String senha) {
		UsuarioModel model = new UsuarioModel();
		model.setNome(nome);
		model.setCpf(cpf);
		model.setEmail(email);
		model.setPerfilUsuario(PerfilUsuarioEnum.valueOf(perfilUsuario));

		if (senha.isEmpty())
			model.setSenha(AuthManager.encryptPassword("123456"));
		else
			model.setSenha(AuthManager.encryptPassword(senha));

		int result = this.managerDAO.getUsuarioDAO().Inserir(model);

		return result;
	}

	public UsuarioModel Obter(int id) {
		UsuarioModel model = this.managerDAO.getUsuarioDAO().Obter(new UsuarioModel(id));

		if (model == null)
			return null;

		return model;
	}

	public ArrayList<UsuarioModel> ObterTodos() {
		ArrayList<UsuarioModel> result = new ArrayList<UsuarioModel>();

		for (UsuarioModel model : this.managerDAO.getUsuarioDAO().ObterTodos()) {
			result.add(model);
		}

		return result;
	}

	public ArrayList<UsuarioModel> ObterPorNome(String nome) {
		ArrayList<UsuarioModel> result = new ArrayList<UsuarioModel>();

		UsuarioModel temp = new UsuarioModel();
		temp.setNome(nome);

		for (UsuarioModel model : this.managerDAO.getUsuarioDAO().ObterPorNome(temp)) {
			result.add(model);
		}

		return result;
	}

	public boolean Atualizar(int id, String nome, String email, String cpf, String perfilUsuario, String senha) {

		UsuarioModel model = new UsuarioModel(id);
		model.setNome(nome);
		model.setCpf(cpf);
		model.setEmail(email);
		model.setPerfilUsuario(PerfilUsuarioEnum.valueOf(perfilUsuario));

		if (!senha.isEmpty())
			model.setSenha(AuthManager.encryptPassword(senha));

		int result = this.managerDAO.getUsuarioDAO().Atualizar(model);

		return result > 0;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getUsuarioDAO().Excluir(new UsuarioModel(id));

		return result > 0;
	}

}

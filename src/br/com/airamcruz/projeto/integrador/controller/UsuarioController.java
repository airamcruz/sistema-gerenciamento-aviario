package br.com.airamcruz.projeto.integrador.controller;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;
import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.enums.PerfilUsuarioEnum;

public class UsuarioController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	public boolean Inserir(String nome, String email, String cpf, String perfilUsuario, String senha) {
		UsuarioModel model = new UsuarioModel();
		model.setNome(nome);
		model.setCpf(cpf);
		model.setEmail(email);
		model.setPerfilUsuario(PerfilUsuarioEnum.valueOf(perfilUsuario));
		model.setSenha(AuthManager.encryptPassword(senha));

		int result = this.managerDAO.getUsuarioDAO().Inserir(model);

		return result > 0;
	}

	public String[] Obter(int id) {
		UsuarioModel model = this.managerDAO.getUsuarioDAO().Obter(new UsuarioModel(id));

		return new String[] { String.valueOf(model.getId()), model.getNome(), model.getCpf(), model.getEmail(),
				String.valueOf(model.getPerfilUsuario()) };
	}

	public ArrayList<String[]> ObterTodos() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		for (UsuarioModel model : this.managerDAO.getUsuarioDAO().ObterTodos()) {
			result.add(new String[] { String.valueOf(model.getId()), model.getNome(), model.getCpf(), model.getEmail(),
					String.valueOf(model.getPerfilUsuario()) });
		}

		return result;
	}

	public boolean Atualizar(int id, String nome, String email, String cpf, PerfilUsuarioEnum perfilUsuario,
			String senha) {

		UsuarioModel model = new UsuarioModel(id);
		model.setNome(nome);
		model.setCpf(cpf);
		model.setEmail(email);
		model.setPerfilUsuario(perfilUsuario);
		model.setSenha(senha);

		int result = this.managerDAO.getUsuarioDAO().Atualizar(model);

		return result > 0;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getUsuarioDAO().Excluir(new UsuarioModel(id));

		return result > 0;
	}

}

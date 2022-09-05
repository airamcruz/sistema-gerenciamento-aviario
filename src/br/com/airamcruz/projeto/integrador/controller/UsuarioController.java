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
		
		if(senha.isEmpty())
			model.setSenha(AuthManager.encryptPassword("123456"));
		else 
			model.setSenha(AuthManager.encryptPassword(senha));

		int result = this.managerDAO.getUsuarioDAO().Inserir(model);

		return result;
	}

	public String[] Obter(int id) {
		UsuarioModel model = this.managerDAO.getUsuarioDAO().Obter(new UsuarioModel(id));
		
		if (model == null) {
			return null;
		}
		
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

	public ArrayList<String[]> ObterPorNome(String nome) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		UsuarioModel temp = new UsuarioModel();
		temp.setNome(nome);

		for (UsuarioModel model : this.managerDAO.getUsuarioDAO().ObterPorNome(temp)) {
			result.add(new String[] { String.valueOf(model.getId()), model.getNome(), model.getCpf(), model.getEmail(),
					String.valueOf(model.getPerfilUsuario()) });
		}

		return result;
	}

	public boolean Atualizar(int id, String nome, String email, String cpf, String perfilUsuario,
			String senha) {

		UsuarioModel model = new UsuarioModel(id);
		model.setNome(nome);
		model.setCpf(cpf);
		model.setEmail(email);
		model.setPerfilUsuario(PerfilUsuarioEnum.valueOf(perfilUsuario));
		
		if(!senha.isEmpty())
			model.setSenha(AuthManager.encryptPassword(senha));

		int result = this.managerDAO.getUsuarioDAO().Atualizar(model);

		return result > 0;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getUsuarioDAO().Excluir(new UsuarioModel(id));

		return result > 0;
	}

}

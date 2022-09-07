package br.com.airamcruz.projeto.integrador.controller;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;
import br.com.airamcruz.projeto.integrador.util.enums.EstadoAviarioEnum;

public class AviarioController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	public int Inserir(String descricao, String estadoAviario, int usuarioId) {
		AviarioModel model = new AviarioModel();
		model.setDescricao(descricao);
		model.setEstadoAviario(EstadoAviarioEnum.valueOf(estadoAviario));
		model.setUsuarioModel(new UsuarioModel(usuarioId));

		int result = this.managerDAO.getAviarioDAO().Inserir(model);

		return result;
	}

	public AviarioModel Obter(int id) {
		AviarioModel model = this.managerDAO.getAviarioDAO().Obter(new AviarioModel(id));

		if (model == null)
			return null;

		carregarRelacionamento(model);

		return model;
	}

	public ArrayList<AviarioModel> ObterTodos() {
		ArrayList<AviarioModel> result = new ArrayList<AviarioModel>();

		for (AviarioModel model : this.managerDAO.getAviarioDAO().ObterTodos()) {

			carregarRelacionamento(model);
			
			result.add(model);
		}

		return result;
	}

	public ArrayList<AviarioModel> ObterPorDescricao(String descricao) {
		ArrayList<AviarioModel> result = new ArrayList<AviarioModel>();
		
		AviarioModel temp = new AviarioModel();
		temp.setDescricao(descricao);

		for (AviarioModel model : this.managerDAO.getAviarioDAO().ObterPorDescricao(temp)) {
			
			carregarRelacionamento(model);
			
			result.add(model);
		}

		return result;
	}

	public ArrayList<AviarioModel> ObterPorUsuarioDescricao(String descricao) {
		ArrayList<AviarioModel> result = new ArrayList<AviarioModel>();
		
		AviarioModel temp = new AviarioModel();
		temp.setDescricao(descricao);
		temp.setUsuarioModel(AuthManager.getInstance().getUsuario());

		for (AviarioModel model : this.managerDAO.getAviarioDAO().ObterPorUsuarioDescricao(temp)) {
			
			result.add(model);
		}

		return result;
	}

	public ArrayList<AviarioModel> ObterPorUsuario() {
		ArrayList<AviarioModel> result = new ArrayList<AviarioModel>();
		
		AviarioModel temp = new AviarioModel();
		temp.setUsuarioModel(AuthManager.getInstance().getUsuario());

		for (AviarioModel model : this.managerDAO.getAviarioDAO().ObterPorUsuario(temp)) {
			
			result.add(model);
		}

		return result;
	}

	public ArrayList<AviarioModel> ObterPorUsuario(int usuarioId) {
		ArrayList<AviarioModel> result = new ArrayList<AviarioModel>();
		
		AviarioModel temp = new AviarioModel();
		temp.setUsuarioModel(new UsuarioModel(usuarioId));

		for (AviarioModel model : this.managerDAO.getAviarioDAO().ObterPorUsuario(temp)) {
			
			result.add(model);
		}

		return result;
	}

	public boolean Atualizar(int id, String descricao, String estadoAviario, int usuarioId) {

		AviarioModel model = new AviarioModel(id);
		model.setDescricao(descricao);
		model.setEstadoAviario(EstadoAviarioEnum.valueOf(estadoAviario));

		int result = this.managerDAO.getAviarioDAO().Atualizar(model);

		return result > 0;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getAviarioDAO().Excluir(new AviarioModel(id));

		return result > 0;
	}
	
	private void carregarRelacionamento(AviarioModel model) {
		model.setUsuarioModel(this.managerDAO.getUsuarioDAO().Obter(model.getUsuarioModel()));
	}

}

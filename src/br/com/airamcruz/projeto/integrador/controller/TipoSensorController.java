package br.com.airamcruz.projeto.integrador.controller;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.TipoSensorModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;

public class TipoSensorController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	public boolean Inserir(String descricao) {
		TipoSensorModel model = new TipoSensorModel();
		model.setDescricao(descricao);

		int result = this.managerDAO.getTipoSensorDAO().Inserir(model);

		return result > 0;
	}

	public TipoSensorModel Obter(int id) {
		TipoSensorModel model = this.managerDAO.getTipoSensorDAO().Obter(new TipoSensorModel(id));

		if (model == null)
			return null;
		
		return model;
	}

	public ArrayList<TipoSensorModel> ObterTodos() {
		ArrayList<TipoSensorModel> result = new ArrayList<TipoSensorModel>();

		for (TipoSensorModel model : this.managerDAO.getTipoSensorDAO().ObterTodos()) {
			result.add(model);
		}

		return result;
	}

	public ArrayList<TipoSensorModel> ObterPorDescricao(String descricao) {
		ArrayList<TipoSensorModel> result = new ArrayList<TipoSensorModel>();

		for (TipoSensorModel model : this.managerDAO.getTipoSensorDAO().ObterPorDescricao(new TipoSensorModel(descricao))) {
			result.add(model);
		}

		return result;
	}

	public boolean Atualizar(int id, String descricao) {

		TipoSensorModel model = new TipoSensorModel(id);
		model.setDescricao(descricao);

		int result = this.managerDAO.getTipoSensorDAO().Atualizar(model);

		return result > 0;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getTipoSensorDAO().Excluir(new TipoSensorModel(id));

		return result > 0;
	}
}

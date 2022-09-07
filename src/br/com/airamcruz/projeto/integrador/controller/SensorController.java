package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.SensorModel;
import br.com.airamcruz.projeto.integrador.model.TipoSensorModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;

public class SensorController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	private SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

	public boolean Inserir(String descricao, String dataInstalacao, int aviarioId, int tipoSensorId) {
		try {
			SensorModel model = new SensorModel();

			model.setDescricao(descricao);
			model.setDataInstalacao(formato.parse(dataInstalacao));
			model.setAviarioModel(new AviarioModel(aviarioId));
			model.setTipoSensorModel(new TipoSensorModel(tipoSensorId));

			int result = this.managerDAO.getSensorDAO().Inserir(model);

			return result > 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public SensorModel Obter(int id) {
		SensorModel model = this.managerDAO.getSensorDAO().Obter(new SensorModel(id));

		if (model == null)
			return null;

		carregarRelacionamento(model);

		return model;
	}

	public ArrayList<SensorModel> ObterTodos() {
		ArrayList<SensorModel> result = new ArrayList<SensorModel>();

		for (SensorModel model : this.managerDAO.getSensorDAO().ObterTodos()) {

			carregarRelacionamento(model);

			result.add(model);
		}

		return result;
	}

	public ArrayList<SensorModel> ObterPorAviario(int idAviario) {
		ArrayList<SensorModel> result = new ArrayList<SensorModel>();


		SensorModel temp = new SensorModel();
		temp.setAviarioModel(new AviarioModel(idAviario));

		for (SensorModel model : this.managerDAO.getSensorDAO().ObterPorAviario(temp)) {

			carregarRelacionamento(model);

			result.add(model);
		}

		return result;
	}

	public ArrayList<SensorModel> ObterPorTipoSensor(int tipoSensor) {
		ArrayList<SensorModel> result = new ArrayList<SensorModel>();

		SensorModel temp = new SensorModel();
		temp.setTipoSensorModel(new TipoSensorModel(tipoSensor));

		for (SensorModel model : this.managerDAO.getSensorDAO().ObterPorTipoSensor(temp)) {

			carregarRelacionamento(model);

			result.add(model);
		}

		return result;
	}

	public boolean Atualizar(int id, String descricao, String dataInstalacao, int aviarioId) {
		try {
			SensorModel model = new SensorModel(id);

			model.setDescricao(descricao);
			model.setDataInstalacao(formato.parse(dataInstalacao));
			model.setAviarioModel(new AviarioModel(aviarioId));

			int result = this.managerDAO.getSensorDAO().Atualizar(model);

			return result > 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getSensorDAO().Excluir(new SensorModel(id));

		return result > 0;
	}

	private void carregarRelacionamento(SensorModel model) {
		model.setTipoSensorModel(this.managerDAO.getTipoSensorDAO().Obter(model.getTipoSensorModel()));
		model.setAviarioModel(this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel()));
	}

}

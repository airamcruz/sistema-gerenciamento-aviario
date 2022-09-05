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

	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

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

	public String[] Obter(int id) {
		SensorModel model = this.managerDAO.getSensorDAO().Obter(new SensorModel(id));

		TipoSensorModel tipoSensorTemp = this.managerDAO.getTipoSensorDAO().Obter(model.getTipoSensorModel());

		AviarioModel aviarioTemp = this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel());

		return new String[] { String.valueOf(model.getId()), model.getDescricao(),
				this.formato.format(model.getDataInstalacao()), aviarioTemp.getDescricao(),
				tipoSensorTemp.getDescricao() };
	}

	public ArrayList<String[]> ObterTodos() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		for (SensorModel model : this.managerDAO.getSensorDAO().ObterTodos()) {

			TipoSensorModel tipoSensorTemp = this.managerDAO.getTipoSensorDAO().Obter(model.getTipoSensorModel());

			AviarioModel aviarioTemp = this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel());

			result.add(new String[] { String.valueOf(model.getId()), model.getDescricao(),
					this.formato.format(model.getDataInstalacao()), aviarioTemp.getDescricao(),
					tipoSensorTemp.getDescricao() });
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

}

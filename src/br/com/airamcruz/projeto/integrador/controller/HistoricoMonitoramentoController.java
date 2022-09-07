package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.HistoricoMonitoramentoModel;
import br.com.airamcruz.projeto.integrador.model.SensorModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;

public class HistoricoMonitoramentoController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	private SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	public boolean Inserir(String valor, String horarioMonitoramento, int sensorId) {
		try {
			HistoricoMonitoramentoModel model = new HistoricoMonitoramentoModel();

			model.setValor(valor);

			model.setHorarioMonitoramento(formato.parse(horarioMonitoramento));

			model.setSensorModel(new SensorModel(sensorId));

			int result = this.managerDAO.getHistoricoMonitoramentoDAO().Inserir(model);

			return result > 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public HistoricoMonitoramentoModel Obter(int id) {
		HistoricoMonitoramentoModel model = this.managerDAO.getHistoricoMonitoramentoDAO()
				.Obter(new HistoricoMonitoramentoModel(id));

		if (model == null)
			return null;

		carregarRelacionamento(model);

		return model;
	}

	public ArrayList<HistoricoMonitoramentoModel> ObterTodos() {
		ArrayList<HistoricoMonitoramentoModel> result = new ArrayList<HistoricoMonitoramentoModel>();

		for (HistoricoMonitoramentoModel model : this.managerDAO.getHistoricoMonitoramentoDAO().ObterTodos()) {

			carregarRelacionamento(model);

			result.add(model);
		}

		return result;
	}

	private void carregarRelacionamento(HistoricoMonitoramentoModel model) {
		model.setSensorModel(this.managerDAO.getSensorDAO().Obter(model.getSensorModel()));
	}

}

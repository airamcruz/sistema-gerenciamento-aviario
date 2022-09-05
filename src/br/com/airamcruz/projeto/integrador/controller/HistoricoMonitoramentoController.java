package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.HistoricoMonitoramentoModel;
import br.com.airamcruz.projeto.integrador.model.SensorModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;

public class HistoricoMonitoramentoController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

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

	public String[] Obter(int id) {
		HistoricoMonitoramentoModel model = this.managerDAO.getHistoricoMonitoramentoDAO()
				.Obter(new HistoricoMonitoramentoModel(id));

		SensorModel sensorTemp = this.managerDAO.getSensorDAO().Obter(model.getSensorModel());

		return new String[] { String.valueOf(model.getId()), model.getValor(),
				this.formato.format(model.getHorarioMonitoramento()), sensorTemp.getDescricao() };
	}

	public ArrayList<String[]> ObterTodos() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		for (HistoricoMonitoramentoModel model : this.managerDAO.getHistoricoMonitoramentoDAO().ObterTodos()) {

			SensorModel sensorTemp = this.managerDAO.getSensorDAO().Obter(model.getSensorModel());

			result.add(new String[] { String.valueOf(model.getId()), model.getValor(),
					this.formato.format(model.getHorarioMonitoramento()), sensorTemp.getDescricao() });
		}

		return result;
	}

}

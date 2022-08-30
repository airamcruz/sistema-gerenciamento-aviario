package br.com.airamcruz.projeto.integrador.dao;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.HistoricoMonitoramentoModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class HistoricoMonitoramentoDAO {
	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(HistoricoMonitoramentoModel model) {
		broker.setQuery("historico_monitoramento.create");
		
		broker.setQueryParameters(model, "valor", "horarioMonitoramento", "sensorModel.id");
		
		return broker.executeUpdate();
	}
	
	public HistoricoMonitoramentoModel Obter(HistoricoMonitoramentoModel model) {
		broker.setQuery("historico_monitoramento.read");
		
		broker.setQueryParameters(model, "id");
		
		return broker.getObject(HistoricoMonitoramentoModel.class, "id", "valor", "horarioMonitoramento", "sensorModel.id");
	}
	
	public ArrayList<HistoricoMonitoramentoModel> ObterTodos() {
		broker.setQuery("historico_monitoramento.readAll");
		
		return (ArrayList<HistoricoMonitoramentoModel>)broker.getListObject(HistoricoMonitoramentoModel.class, "id", "valor", "horarioMonitoramento", "sensorModel.id");
	}
	
	public ArrayList<HistoricoMonitoramentoModel> ObterTodosPorSensor(HistoricoMonitoramentoModel model) {
		broker.setQuery("historico_monitoramento.readAllBySensor");
		
		broker.setQueryParameters(model, "sensorModel.id");
		
		return (ArrayList<HistoricoMonitoramentoModel>)broker.getListObject(HistoricoMonitoramentoModel.class, "id", "valor", "horarioMonitoramento", "sensorModel.id");
	}

}

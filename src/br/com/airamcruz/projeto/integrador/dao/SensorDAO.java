package br.com.airamcruz.projeto.integrador.dao;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.SensorModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class SensorDAO {
	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(SensorModel model) {
		broker.setQuery("sensor.create");
		
		broker.setQueryParameters(model, "descricao", "dataInstalacao", "aviarioModel.id", "tipoSensorModel.id");
		
		return broker.executeUpdate();
	}
	
	public SensorModel Obter(SensorModel model) {
		broker.setQuery("sensor.read");
		
		broker.setQueryParameters(model, "id");
		
		return broker.getObject(SensorModel.class, "id", "descricao", "dataInstalacao", "aviarioModel.id", "tipoSensorModel.id");
	}
	
	public ArrayList<SensorModel> ObterTodos() {
		broker.setQuery("sensor.readAll");
		
		return (ArrayList<SensorModel>)broker.getListObject(SensorModel.class, "id", "descricao", "dataInstalacao", "aviarioModel.id", "tipoSensorModel.id");
	}
	
	public ArrayList<SensorModel> ObterTodosPorAviario(SensorModel model) {
		broker.setQuery("sensor.readAllByAviario");
		
		broker.setQueryParameters(model, "aviarioModel.id");
		
		return (ArrayList<SensorModel>)broker.getListObject(SensorModel.class, "id", "descricao", "dataInstalacao", "aviarioModel.id", "tipoSensorModel.id");
	}
	
	public int Atualizar(SensorModel model) {
		broker.setQuery("sensor.update");
		
		broker.setQueryParameters(model, "descricao", "dataInstalacao", "aviarioModel.id", "id");
		
		return broker.executeUpdate();
	}
	
	public int Excluir(SensorModel model) {
		broker.setQuery("sensor.delete");
		
		broker.setQueryParameters(model, "id");
		
		return broker.executeUpdate();
	}

}

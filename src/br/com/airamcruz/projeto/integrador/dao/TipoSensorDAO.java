package br.com.airamcruz.projeto.integrador.dao;

import java.util.List;

import br.com.airamcruz.projeto.integrador.model.TipoSensorModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class TipoSensorDAO {
	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(TipoSensorModel model) {
		broker.setQuery("tipo_sensor.create");
		
		broker.setQueryParameters(model, "descricao");
		
		return broker.executeReturnGeneratedKey();
	}
	
	public TipoSensorModel Obter(TipoSensorModel model) {
		broker.setQuery("tipo_sensor.read");
		
		broker.setQueryParameters(model, "id");
		
		return broker.getObject(TipoSensorModel.class, "id", "descricao");
	}
	
	public List<TipoSensorModel> ObterTodos(TipoSensorModel model) {
		broker.setQuery("tipo_sensor.read");
		
		return broker.getListObject(TipoSensorModel.class, "id", "descricao");
	}
	
	public int Atualizar(TipoSensorModel model) {
		broker.setQuery("tipo_sensor.update");
		
		broker.setQueryParameters(model, "descricao", "id");
		
		return broker.executeUpdate();
	}
	
	public int Excluir(TipoSensorModel model) {
		broker.setQuery("tipo_sensor.delete");
		
		broker.setQueryParameters(model, "id");
		
		return broker.executeUpdate();
	}
}

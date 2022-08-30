package br.com.airamcruz.projeto.integrador.dao;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.BoletimSanitarioModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class BoletimSanitarioDAO {
	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(BoletimSanitarioModel model) {
		broker.setQuery("boletim_sanitario.create");
		
		broker.setQueryParameters(model, "data", "parecer", "aviarioModel.id");
		
		return broker.executeUpdate();
	}
	
	public BoletimSanitarioModel Obter(BoletimSanitarioModel model) {
		broker.setQuery("boletim_sanitario.read");
		
		broker.setQueryParameters(model, "id");
		
		return broker.getObject(BoletimSanitarioModel.class, "id", "data", "parecer", "aviarioModel.id");
	}
	
	public ArrayList<BoletimSanitarioModel> ObterTodos() {
		broker.setQuery("boletim_sanitario.readAll");
		
		return (ArrayList<BoletimSanitarioModel>)broker.getListObject(BoletimSanitarioModel.class, "id", "data", "parecer", "aviarioModel.id");
	}
	
	public ArrayList<BoletimSanitarioModel> ObterTodosPorAviario(BoletimSanitarioModel model) {
		broker.setQuery("boletim_sanitario.readAllByAviario");
		
		broker.setQueryParameters(model, "aviarioModel.id");
		
		return (ArrayList<BoletimSanitarioModel>)broker.getListObject(BoletimSanitarioModel.class, "id", "data", "parecer", "aviarioModel.id");
	}
	
	public int Atualizar(BoletimSanitarioModel model) {
		broker.setQuery("boletim_sanitario.update");
		
		broker.setQueryParameters(model, "data", "parecer", "id");
		
		return broker.executeUpdate();
	}
	
	public int Excluir(BoletimSanitarioModel model) {
		broker.setQuery("boletim_sanitario.delete");
		
		broker.setQueryParameters(model, "id");
		
		return broker.executeUpdate();
	}
}

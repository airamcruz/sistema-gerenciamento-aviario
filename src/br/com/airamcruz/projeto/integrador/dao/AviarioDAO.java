package br.com.airamcruz.projeto.integrador.dao;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class AviarioDAO {
	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(AviarioModel model) {
		broker.setQuery("aviario.create");
		
		broker.setQueryParameters(model, "descricao", "estadoAviario", "usuarioModel.id");
		
		return broker.executeUpdate();
	}
	
	public AviarioModel Obter(AviarioModel model) {
		broker.setQuery("aviario.read");
		
		broker.setQueryParameters(model, "id");
		
		return broker.getObject(AviarioModel.class, "id", "descricao", "estadoAviario", "usuarioModel.id");
	}
	
	public ArrayList<AviarioModel> ObterTodos() {
		broker.setQuery("aviario.readAll");
		
		return (ArrayList<AviarioModel>)broker.getListObject(AviarioModel.class, "id", "descricao", "estadoAviario", "usuarioModel.id");
	}
	
	public ArrayList<AviarioModel> ObterTodosPorUsuario(AviarioModel model) {
		broker.setQuery("aviario.readAllByUser");
		
		broker.setQueryParameters(model, "usuarioModel.id");
		
		return (ArrayList<AviarioModel>)broker.getListObject(AviarioModel.class, "id", "descricao", "estadoAviario", "usuarioModel.id");
	}
	
	public int Atualizar(AviarioModel model) {
		broker.setQuery("aviario.update");
		
		broker.setQueryParameters(model, "descricao", "estadoAviario", "id");
		
		return broker.executeUpdate();
	}
	
	public int Excluir(AviarioModel model) {
		broker.setQuery("aviario.delete");
		
		broker.setQueryParameters(model, "id");
		
		return broker.executeUpdate();
	}
}

package br.com.airamcruz.projeto.integrador.dao;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class UsuarioDAO {

	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(UsuarioModel model) {
		broker.setQuery("usuario.create");
		
		broker.setQueryParameters(model, "id", "nome", "email", "cpf", "perfilUsuario", "senha");
		
		return broker.executeUpdate();
	}
	
	public UsuarioModel Obter(UsuarioModel model) {
		broker.setQuery("usuario.read");
		
		broker.setQueryParameters(model, "id");
		
		return broker.getObject(UsuarioModel.class, "id", "nome", "email", "cpf", "perfilUsuario");
	}
	
	public ArrayList<UsuarioModel> ObterTodos() {
		broker.setQuery("usuario.readAll");
		
		return (ArrayList<UsuarioModel>)broker.getListObject(UsuarioModel.class, "id", "nome", "email", "cpf", "perfilUsuario");
	}
	
	public int Atualizar(UsuarioModel model) {
		broker.setQuery("usuario.update");
		
		broker.setQueryParameters(model, "nome", "email", "perfilUsuario", "id");
		
		return broker.executeUpdate();
	}
	
	public int Excluir(UsuarioModel model) {
		broker.setQuery("usuario.delete");
		
		broker.setQueryParameters(model, "id");
		
		return broker.executeUpdate();
	}

}

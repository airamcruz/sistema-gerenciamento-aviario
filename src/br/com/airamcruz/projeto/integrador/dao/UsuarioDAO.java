package br.com.airamcruz.projeto.integrador.dao;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class UsuarioDAO {
	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(UsuarioModel model) {
		broker.setQuery("usuario.create");
		
		broker.setQueryParameters(model, "nome", "email", "cpf", "perfilUsuario", "senha");
		
		return broker.executeReturnGeneratedKey();
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
	
	public ArrayList<UsuarioModel> ObterPorNome(UsuarioModel model) {
		broker.setQuery("usuario.readAllByName");
		
		broker.setQueryParameters(model, "nome");
		
		return (ArrayList<UsuarioModel>)broker.getListObject(UsuarioModel.class, "id", "nome", "email", "cpf", "perfilUsuario");
	}
	
	public int Atualizar(UsuarioModel model) {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("UPDATE usuario SET nome=?, email=?, perfil=?");
		
		if(!model.getSenha().isEmpty())
			queryBuilder.append(", senha=?");
		
		queryBuilder.append(" WHERE id=?;");
				
		//broker.setQuery("usuario.update");
		System.out.println("Query atualizar Usuario: ");
		
		String query = queryBuilder.toString();
		
		System.out.println(query);
		
		broker.setQuery(query);

		if(model.getSenha().isEmpty())
			broker.setQueryParameters(model, "nome", "email", "perfilUsuario", "id");
		else
			broker.setQueryParameters(model, "nome", "email", "perfilUsuario", "senha", "id");
		
		return broker.executeUpdate();
	}
	
	public int Excluir(UsuarioModel model) {
		broker.setQuery("usuario.delete");
		
		broker.setQueryParameters(model, "id");
		
		return broker.executeUpdate();
	}
	
	public UsuarioModel Autenticar(UsuarioModel model) {
		broker.setQuery("usuario.auth");
		
		broker.setQueryParameters(model, "cpf", "senha");
		
		return broker.getObject(UsuarioModel.class, "id", "nome", "email", "cpf", "perfilUsuario");
	}

}

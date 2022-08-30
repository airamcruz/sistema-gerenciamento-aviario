package br.com.airamcruz.projeto.integrador.dao;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.LoteModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class LoteDAO {
	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(LoteModel model) {
		broker.setQuery("lote.create");
		
		broker.setQueryParameters(model, "descricao", "dataCompra", "quantidadeFrangos", "previsaoAbate", "aviarioModel.id");
		
		return broker.executeUpdate();
	}
	
	public LoteModel Obter(LoteModel model) {
		broker.setQuery("lote.read");
		
		broker.setQueryParameters(model, "id");
		
		return broker.getObject(LoteModel.class, "id", "descricao", "dataCompra", "quantidadeFrangos", "previsaoAbate", "aviarioModel.id");
	}
	
	public ArrayList<LoteModel> ObterTodos() {
		broker.setQuery("lote.readAll");
		
		return (ArrayList<LoteModel>)broker.getListObject(LoteModel.class, "id", "descricao", "dataCompra", "quantidadeFrangos", "previsaoAbate", "aviarioModel.id");
	}
	
	public ArrayList<LoteModel> ObterTodosPorAviario(LoteModel model) {
		broker.setQuery("lote.readAllByAviario");
		
		broker.setQueryParameters(model, "aviarioModel.id");
		
		return (ArrayList<LoteModel>)broker.getListObject(LoteModel.class, "id", "descricao", "dataCompra", "quantidadeFrangos", "previsaoAbate", "aviarioModel.id");
	}
	
	public int Atualizar(LoteModel model) {
		broker.setQuery("lote.update");
		
		broker.setQueryParameters(model, "descricao", "dataCompra", "quantidadeFrangos", "previsaoAbate", "aviarioModel.id", "id");
		
		return broker.executeUpdate();
	}
	
	public int Excluir(LoteModel model) {
		broker.setQuery("lote.delete");
		
		broker.setQueryParameters(model, "id");
		
		return broker.executeUpdate();
	}

}

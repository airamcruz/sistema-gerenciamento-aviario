package br.com.airamcruz.projeto.integrador.dao;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.FluxoCaixaModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class FluxoCaixaDAO {
	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(FluxoCaixaModel model) {
		broker.setQuery("fluxo_caixa.create");
		
		broker.setQueryParameters(model, "data", "tipoFluxoCaixa", "valor", "usuarioModel.id");
		
		return broker.executeUpdate();
	}
	
	public FluxoCaixaModel Obter(FluxoCaixaModel model) {
		broker.setQuery("fluxo_caixa.read");
		
		broker.setQueryParameters(model, "id");
		
		return broker.getObject(FluxoCaixaModel.class, "id", "data", "tipoFluxoCaixa", "valor", "usuarioModel.id");
	}
	
	public ArrayList<FluxoCaixaModel> ObterTodos() {
		broker.setQuery("fluxo_caixa.readAll");
		
		return (ArrayList<FluxoCaixaModel>)broker.getListObject(FluxoCaixaModel.class, "id", "data", "tipoFluxoCaixa", "valor", "usuarioModel.id");
	}
	
	public ArrayList<FluxoCaixaModel> ObterPorUsuario(FluxoCaixaModel model) {
		broker.setQuery("fluxo_caixa.readAllByUser");
		
		broker.setQueryParameters(model, "usuarioModel.id");
		
		return (ArrayList<FluxoCaixaModel>)broker.getListObject(FluxoCaixaModel.class, "id", "data", "tipoFluxoCaixa", "valor", "usuarioModel.id");
	}
	
	public int Atualizar(FluxoCaixaModel model) {
		broker.setQuery("fluxo_caixa.update");
		
		broker.setQueryParameters(model, "data", "tipoFluxoCaixa", "valor", "id");
		
		return broker.executeUpdate();
	}
	
	public int Excluir(FluxoCaixaModel model) {
		broker.setQuery("fluxo_caixa.delete");
		
		broker.setQueryParameters(model, "id");
		
		return broker.executeUpdate();
	}

}

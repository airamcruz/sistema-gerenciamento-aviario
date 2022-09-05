package br.com.airamcruz.projeto.integrador.dao;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.MortalidadeModel;
import br.com.airamcruz.projeto.integrador.util.factory.DatabaseBroker;

public class MortalidadeDAO {
	
	private DatabaseBroker broker = new DatabaseBroker();
	
	public int Inserir(MortalidadeModel model) {
		broker.setQuery("mortalidade.create");
		
		broker.setQueryParameters(model, "motivoMortalidade", "dataMortalidade", "quantidadeFrangos", "loteModel.id");
		
		return broker.executeUpdate();
	}
	
	public MortalidadeModel Obter(MortalidadeModel model) {
		broker.setQuery("mortalidade.read");
		
		broker.setQueryParameters(model, "id");
		
		return broker.getObject(MortalidadeModel.class, "id", "motivoMortalidade", "dataMortalidade", "quantidadeFrangos", "loteModel.id");
	}
	
	public ArrayList<MortalidadeModel> ObterTodos() {
		broker.setQuery("mortalidade.readAll");
		
		return (ArrayList<MortalidadeModel>)broker.getListObject(MortalidadeModel.class, "id", "motivoMortalidade", "dataMortalidade", "quantidadeFrangos", "loteModel.id");
	}
	
	public ArrayList<MortalidadeModel> ObterTodosPorLote(MortalidadeModel model) {
		broker.setQuery("mortalidade.readAllByLote");
		
		broker.setQueryParameters(model, "loteModel.id");
		
		return (ArrayList<MortalidadeModel>)broker.getListObject(MortalidadeModel.class, "id", "motivoMortalidade", "dataMortalidade", "quantidadeFrangos", "loteModel.id");
	}
	
	public int Atualizar(MortalidadeModel model) {
		broker.setQuery("mortalidade.update");
		
		broker.setQueryParameters(model, "motivoMortalidade", "dataMortalidade", "quantidadeFrangos", "id");
		
		return broker.executeUpdate();
	}
	
	public int Excluir(MortalidadeModel model) {
		broker.setQuery("mortalidade.delete");
		
		broker.setQueryParameters(model, "id");
		
		return broker.executeUpdate();
	}

}

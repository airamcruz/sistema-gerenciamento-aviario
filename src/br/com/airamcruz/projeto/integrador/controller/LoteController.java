package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.LoteModel;
import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;

public class LoteController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();
	
	private SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

	public boolean Inserir(String descricao, String dataCompra, int quantidadeFrangos, int aviarioId) {
		try {
			LoteModel model = new LoteModel();

			model.setDescricao(descricao);
			model.setDataCompra(formato.parse(dataCompra));
			model.setQuantidadeFrangos(quantidadeFrangos);
			model.setAviarioModel(new AviarioModel(aviarioId));

			int result = this.managerDAO.getLoteDAO().Inserir(model);

			return result > 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public LoteModel Obter(int id) {
		LoteModel model = this.managerDAO.getLoteDAO().Obter(new LoteModel(id));

		if (model == null)
			return null;

		carregarRelacionamento(model);

		return model;
	}

	public ArrayList<LoteModel> ObterTodos() {
		ArrayList<LoteModel> result = new ArrayList<LoteModel>();

		for (LoteModel model : this.managerDAO.getLoteDAO().ObterTodos()) {

			carregarRelacionamento(model);

			result.add(model);
		}

		return result;
	}

	public ArrayList<LoteModel> ObterPorAviario(int aviarioId) {
		ArrayList<LoteModel> result = new ArrayList<LoteModel>();
		
		LoteModel temp = new LoteModel();
		temp.setAviarioModel(new AviarioModel(aviarioId));

		for (LoteModel model : this.managerDAO.getLoteDAO().ObterPorAviario(temp)) {

			carregarRelacionamento(model);

			result.add(model);
		}

		return result;
	}

	public ArrayList<LoteModel> ObterPorUsuario() {
		ArrayList<LoteModel> result = new ArrayList<LoteModel>();
		
		AviarioModel aviarioTemp = new AviarioModel();
		aviarioTemp.setUsuarioModel(AuthManager.getInstance().getUsuario());
		
		LoteModel temp = new LoteModel();
		temp.setAviarioModel(aviarioTemp);

		for (LoteModel model : this.managerDAO.getLoteDAO().ObterPorUsuario(temp)) {

			carregarRelacionamento(model);

			result.add(model);
		}

		return result;
	}

	public boolean Atualizar(int id, String descricao, String dataCompra, int quantidadeFrangos, int aviarioId) {
		try {
			LoteModel model = new LoteModel(id);

			model.setDescricao(descricao);
			model.setDataCompra(formato.parse(dataCompra));
			model.setQuantidadeFrangos(quantidadeFrangos);
			model.setAviarioModel(new AviarioModel(aviarioId));

			int result = this.managerDAO.getLoteDAO().Atualizar(model);

			return result > 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getLoteDAO().Excluir(new LoteModel(id));

		return result > 0;
	}

	private void carregarRelacionamento(LoteModel model) {
		model.setAviarioModel(this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel()));
	}

}

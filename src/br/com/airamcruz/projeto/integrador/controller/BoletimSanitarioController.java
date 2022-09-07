package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.BoletimSanitarioModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;

public class BoletimSanitarioController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	private SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

	public boolean Inserir(String parecer, String data, int aviarioId) {
		try {
			BoletimSanitarioModel model = new BoletimSanitarioModel();
			model.setParecer(parecer);
			model.setData(formato.parse(data));
			model.setAviarioModel(new AviarioModel(aviarioId));

			int result = this.managerDAO.getBoletimSanitarioDAO().Inserir(model);

			return result > 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public BoletimSanitarioModel Obter(int id) {
		BoletimSanitarioModel model = this.managerDAO.getBoletimSanitarioDAO().Obter(new BoletimSanitarioModel(id));

		if (model == null)
			return null;

		carregarRelacionamento(model);

		return model;
	}

	public ArrayList<BoletimSanitarioModel> ObterTodos() {
		ArrayList<BoletimSanitarioModel> result = new ArrayList<BoletimSanitarioModel>();

		for (BoletimSanitarioModel model : this.managerDAO.getBoletimSanitarioDAO().ObterTodos()) {

			model.setAviarioModel(this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel()));

			result.add(model);
		}

		return result;
	}

	public boolean Atualizar(int id, String parecer, String data) {
		try {
			BoletimSanitarioModel model = new BoletimSanitarioModel(id);
			model.setParecer(parecer);
			model.setData(formato.parse(data));

			int result = this.managerDAO.getBoletimSanitarioDAO().Atualizar(model);

			return result > 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getBoletimSanitarioDAO().Excluir(new BoletimSanitarioModel(id));

		return result > 0;
	}

	private void carregarRelacionamento(BoletimSanitarioModel model) {
		model.setAviarioModel(this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel()));
	}
}

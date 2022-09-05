package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.BoletimSanitarioModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;

public class BoletimSanitarioController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

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

	public String[] Obter(int id) {
		BoletimSanitarioModel model = this.managerDAO.getBoletimSanitarioDAO().Obter(new BoletimSanitarioModel(id));

		AviarioModel aviarioTemp = this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel());

		return new String[] { String.valueOf(model.getId()), model.getParecer(), this.formato.format(model.getData()),
				aviarioTemp.getDescricao() };
	}

	public ArrayList<String[]> ObterTodos() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		for (BoletimSanitarioModel model : this.managerDAO.getBoletimSanitarioDAO().ObterTodos()) {

			AviarioModel aviarioTemp = this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel());

			result.add(new String[] { String.valueOf(model.getId()), model.getParecer(),
					this.formato.format(model.getData()), aviarioTemp.getDescricao() });
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
}

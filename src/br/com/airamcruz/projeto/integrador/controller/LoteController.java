package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.LoteModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;

public class LoteController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

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

	public String[] Obter(int id) {
		LoteModel model = this.managerDAO.getLoteDAO().Obter(new LoteModel(id));

		AviarioModel aviarioTemp = this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel());

		return new String[] { String.valueOf(model.getId()), model.getDescricao(),
				this.formato.format(model.getDataCompra()), String.valueOf(model.getQuantidadeFrangos()),
				this.formato.format(model.getPrevisaoAbate()), aviarioTemp.getDescricao() };
	}

	public ArrayList<String[]> ObterTodos() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		for (LoteModel model : this.managerDAO.getLoteDAO().ObterTodos()) {

			AviarioModel aviarioTemp = this.managerDAO.getAviarioDAO().Obter(model.getAviarioModel());

			result.add(new String[] { String.valueOf(model.getId()), model.getDescricao(),
					this.formato.format(model.getDataCompra()), String.valueOf(model.getQuantidadeFrangos()),
					this.formato.format(model.getPrevisaoAbate()), aviarioTemp.getDescricao() });
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

}

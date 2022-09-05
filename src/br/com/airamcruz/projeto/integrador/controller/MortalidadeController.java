package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.LoteModel;
import br.com.airamcruz.projeto.integrador.model.MortalidadeModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;

public class MortalidadeController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public boolean Inserir(String motivoMortalidade, String dataMortalidade, int quantidadeFrangos, int loteId) {
		try {
			MortalidadeModel model = new MortalidadeModel();

			model.setMotivoMortalidade(motivoMortalidade);
			model.setDataMortalidade(formato.parse(dataMortalidade));
			model.setQuantidadeFrangos(quantidadeFrangos);
			model.setLoteModel(new LoteModel(loteId));

			int result = this.managerDAO.getMortalidadeDAO().Inserir(model);

			return result > 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public String[] Obter(int id) {
		MortalidadeModel model = this.managerDAO.getMortalidadeDAO().Obter(new MortalidadeModel(id));

		LoteModel loteTemp = this.managerDAO.getLoteDAO().Obter(model.getLoteModel());

		return new String[] { String.valueOf(model.getId()), model.getMotivoMortalidade(),
				this.formato.format(model.getDataMortalidade()), String.valueOf(model.getQuantidadeFrangos()),
				loteTemp.getDescricao() };
	}

	public ArrayList<String[]> ObterTodos() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		for (MortalidadeModel model : this.managerDAO.getMortalidadeDAO().ObterTodos()) {

			LoteModel loteTemp = this.managerDAO.getLoteDAO().Obter(model.getLoteModel());

			result.add(new String[] { String.valueOf(model.getId()), model.getMotivoMortalidade(),
					this.formato.format(model.getDataMortalidade()), String.valueOf(model.getQuantidadeFrangos()),
					loteTemp.getDescricao() });
		}

		return result;
	}

	public boolean Atualizar(int id, String motivoMortalidade, String dataMortalidade, int quantidadeFrangos) {
		try {
			MortalidadeModel model = new MortalidadeModel(id);

			model.setMotivoMortalidade(motivoMortalidade);
			model.setDataMortalidade(formato.parse(dataMortalidade));
			model.setQuantidadeFrangos(quantidadeFrangos);

			int result = this.managerDAO.getMortalidadeDAO().Atualizar(model);

			return result > 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getMortalidadeDAO().Excluir(new MortalidadeModel(id));

		return result > 0;
	}

}

package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.FluxoCaixaModel;
import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;
import br.com.airamcruz.projeto.integrador.util.enums.TipoFluxoCaixaEnum;

public class FluxoCaixaController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public boolean Inserir(String data, String tipoFluxoCaixa, String valor, int usuarioId) {
		FluxoCaixaModel model = new FluxoCaixaModel();

		try {
			model.setData(formato.parse(data));
			model.setTipoFluxoCaixa(TipoFluxoCaixaEnum.valueOf(tipoFluxoCaixa));
			model.setValor(Double.parseDouble(valor));
			model.setUsuarioModel(new UsuarioModel(usuarioId));

			int result = this.managerDAO.getFluxoCaixaDAO().Inserir(model);

			return result > 0;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String[] Obter(int id) {
		FluxoCaixaModel model = this.managerDAO.getFluxoCaixaDAO().Obter(new FluxoCaixaModel(id));

		//UsuarioModel usuarioTemp = this.managerDAO.getUsuarioDAO().Obter(model.getUsuarioModel());

		return new String[] { String.valueOf(model.getId()), this.formato.format(model.getData()),
				String.valueOf(model.getValor()), String.valueOf(model.getTipoFluxoCaixa())};
	}

	public ArrayList<String[]> ObterTodos() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		for (FluxoCaixaModel model : this.managerDAO.getFluxoCaixaDAO().ObterTodos()) {

			//UsuarioModel usuarioTemp = this.managerDAO.getUsuarioDAO().Obter(model.getUsuarioModel());

			result.add(new String[] { String.valueOf(model.getId()), this.formato.format(model.getData()),
					String.format("R$ %f", model.getValor()), String.valueOf(model.getTipoFluxoCaixa()) });
		}

		return result;
	}

	public boolean Atualizar(int id, String data, String tipoFluxoCaixa, String valor) {
		FluxoCaixaModel model = new FluxoCaixaModel(id);

		try {
			model.setData(formato.parse(data));
			model.setTipoFluxoCaixa(TipoFluxoCaixaEnum.valueOf(tipoFluxoCaixa));
			model.setValor(Double.parseDouble(valor));

			int result = this.managerDAO.getFluxoCaixaDAO().Atualizar(model);

			return result > 0;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getFluxoCaixaDAO().Excluir(new FluxoCaixaModel(id));

		return result > 0;
	}

}

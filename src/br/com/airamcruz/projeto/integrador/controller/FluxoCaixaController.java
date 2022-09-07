package br.com.airamcruz.projeto.integrador.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.FluxoCaixaModel;
import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;
import br.com.airamcruz.projeto.integrador.util.enums.TipoFluxoCaixaEnum;

public class FluxoCaixaController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();
	
	private SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

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

	public FluxoCaixaModel Obter(int id) {
		FluxoCaixaModel model = this.managerDAO.getFluxoCaixaDAO().Obter(new FluxoCaixaModel(id));

		if (model == null)
			return null;

		carregarRelacionamento(model);
		return model;
	}

	public ArrayList<FluxoCaixaModel> ObterTodos() {
		ArrayList<FluxoCaixaModel> result = new ArrayList<FluxoCaixaModel>();

		for (FluxoCaixaModel model : this.managerDAO.getFluxoCaixaDAO().ObterTodos()) {
			
			carregarRelacionamento(model);
			
			//result.add(new String[] { String.valueOf(model.getId()), formato.format(model.getData()),
				//	String.format("R$ %.02f", model.getValor()), String.valueOf(model.getTipoFluxoCaixa()) });
			result.add(model);
		}

		return result;
	}

	public ArrayList<FluxoCaixaModel> ObterPorUsuario() {
		ArrayList<FluxoCaixaModel> result = new ArrayList<FluxoCaixaModel>();
		
		FluxoCaixaModel temp = new FluxoCaixaModel();
		temp.setUsuarioModel(AuthManager.getInstance().getUsuario());

		for (FluxoCaixaModel model : this.managerDAO.getFluxoCaixaDAO().ObterPorUsuario(temp)) {
			
			carregarRelacionamento(model);
			
			//result.add(new String[] { String.valueOf(model.getId()), formato.format(model.getData()),
				//	String.format("R$ %.02f", model.getValor()), String.valueOf(model.getTipoFluxoCaixa()) });
			result.add(model);
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
	
	private void carregarRelacionamento(FluxoCaixaModel model) {
		model.setUsuarioModel(this.managerDAO.getUsuarioDAO().Obter(model.getUsuarioModel()));
	}

}

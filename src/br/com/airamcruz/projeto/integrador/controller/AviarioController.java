package br.com.airamcruz.projeto.integrador.controller;

import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.ManagerDAO;
import br.com.airamcruz.projeto.integrador.util.enums.EstadoAviarioEnum;

public class AviarioController {

	private ManagerDAO managerDAO = ManagerDAO.getInstance();

	public boolean Inserir(String descricao, String estadoAviario, int usuarioId) {
		AviarioModel model = new AviarioModel();
		model.setDescricao(descricao);
		model.setEstadoAviario(EstadoAviarioEnum.valueOf(estadoAviario));
		model.setUsuarioModel(new UsuarioModel(usuarioId));

		int result = this.managerDAO.getAviarioDAO().Inserir(model);

		return result > 0;
	}

	public String[] Obter(int id) {
		AviarioModel model = this.managerDAO.getAviarioDAO().Obter(new AviarioModel(id));

		UsuarioModel usuarioTemp = this.managerDAO.getUsuarioDAO().Obter(model.getUsuarioModel());

		return new String[] { String.valueOf(model.getId()), model.getDescricao(),
				String.valueOf(model.getEstadoAviario()), String.valueOf(usuarioTemp.getNome()) };
	}

	public ArrayList<String[]> ObterTodos() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		for (AviarioModel model : this.managerDAO.getAviarioDAO().ObterTodos()) {

			UsuarioModel usuarioTemp = this.managerDAO.getUsuarioDAO().Obter(model.getUsuarioModel());

			result.add(new String[] { String.valueOf(model.getId()), model.getDescricao(),
					String.valueOf(model.getEstadoAviario()), String.valueOf(usuarioTemp.getNome()) });
		}

		return result;
	}

	public boolean Atualizar(int id, String descricao, String estadoAviario, int usuarioId) {

		AviarioModel model = new AviarioModel(id);
		model.setDescricao(descricao);
		model.setEstadoAviario(EstadoAviarioEnum.valueOf(estadoAviario));

		int result = this.managerDAO.getAviarioDAO().Atualizar(model);

		return result > 0;

	}

	public boolean Excluir(int id) {
		int result = this.managerDAO.getAviarioDAO().Excluir(new AviarioModel(id));

		return result > 0;
	}

}

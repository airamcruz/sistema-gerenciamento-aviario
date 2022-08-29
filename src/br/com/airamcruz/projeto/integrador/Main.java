package br.com.airamcruz.projeto.integrador;

import br.com.airamcruz.projeto.integrador.dao.TipoSensorDAO;
import br.com.airamcruz.projeto.integrador.model.TipoSensorModel;

public class Main {

	public static void main(String[] args) {
		System.out.println("Ola Mundo!");
		TipoSensorModel sensor1 = new TipoSensorModel();
		sensor1.setDescricao("Adicionando um novo1");
	
		TipoSensorDAO tipoSensorDao = new TipoSensorDAO();
		System.out.println(tipoSensorDao.Inserir(sensor1));
		
		TipoSensorModel modelReturn = tipoSensorDao.Obter(new TipoSensorModel(15));
		System.out.println("modelReturn id: " + modelReturn.getId());
		System.out.println("modelReturn descricao: " + modelReturn.getDescricao());
	}

}

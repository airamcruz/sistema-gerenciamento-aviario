package br.com.airamcruz.projeto.integrador;

import br.com.airamcruz.projeto.integrador.controller.FluxoCaixaController;
import br.com.airamcruz.projeto.integrador.controller.UsuarioController;
import br.com.airamcruz.projeto.integrador.model.FluxoCaixaModel;
import br.com.airamcruz.projeto.integrador.util.AuthManager;

public class Main {

	public static void main(String[] args) {
		System.out.println("Ola Mundo!");
		
		/*UsuarioController usuarioController = new UsuarioController();
		
		System.out.println(AuthManager.encryptPassword("05049536"));
		System.out.println(AuthManager.encryptPassword("05049536"));
		
		if(usuarioController.Inserir("Leonardo Airam", "aiiramcruz@gmail.com", "025.280.915-75", "ADMINISTRADOR", "05049536")) {
			System.out.println("Usuario Cadastrado");
		} else {
			System.out.println("Error ao cadastrar");
		}
		

		/*if(AuthManager.getInstance().autenticar("025.280.915-75", "05049536")) {
			System.out.println("Usuario Autenticado");
		} else {
			System.out.println("Error ao autenticar");
		}
				

		if(AuthManager.getInstance().deslogar()) {
			System.out.println("Usuario Deslogado");
		} else {
			System.out.println("Error ao deslogar");
		}*/
		
		FluxoCaixaController fluxoCaixaController = new FluxoCaixaController();
		
		/*if(fluxoCaixaController.Inserir("11/05/1991", "DESPESA", "95.5", 1)) {
			System.out.println("Sucesso ao inserir fluxo");
		} else {
			System.out.println("Error ao fluxoCaixaController.Inserir");
		}*/

		System.out.println("Recuperando fluxo Caixa: ");
		for(String value : fluxoCaixaController.Obter(1)) {
			System.out.println(value);
		}
	}

}

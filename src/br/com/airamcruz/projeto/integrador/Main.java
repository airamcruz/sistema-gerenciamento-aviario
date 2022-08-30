package br.com.airamcruz.projeto.integrador;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import br.com.airamcruz.projeto.integrador.dao.TipoSensorDAO;
import br.com.airamcruz.projeto.integrador.dao.UsuarioDAO;
import br.com.airamcruz.projeto.integrador.model.TipoSensorModel;
import br.com.airamcruz.projeto.integrador.model.UsuarioModel;
import br.com.airamcruz.projeto.integrador.util.enums.PerfilUsuarioEnum;

public class Main {

	public static void main(String[] args) {
		System.out.println("Ola Mundo!");
		/*
		TipoSensorModel sensor1 = new TipoSensorModel();
		sensor1.setDescricao("Sensor 01");
		
		TipoSensorModel sensor2 = new TipoSensorModel();
		sensor2.setDescricao("Sensor 02");
		
		TipoSensorModel sensor3 = new TipoSensorModel();
		sensor3.setDescricao("Sensor 03");
	*/
		TipoSensorDAO tipoSensorDao = new TipoSensorDAO();
		//System.out.println(tipoSensorDao.Inserir(sensor1));
		//System.out.println(tipoSensorDao.Inserir(sensor2));
		//System.out.println(tipoSensorDao.Inserir(sensor3));
		
		/*TipoSensorModel modelReturn = tipoSensorDao.Obter(new TipoSensorModel(1));
		System.out.println("modelReturn id: " + modelReturn.getId());
		System.out.println("modelReturn descricao: " + modelReturn.getDescricao());*/

		PerfilUsuarioEnum penum = PerfilUsuarioEnum.PROPRIETARIO;
		System.out.println(penum.name());
	
		ArrayList<TipoSensorModel> lista = tipoSensorDao.ObterTodos();
		
		for(TipoSensorModel sensor : lista) {
			System.out.println("ID: " + sensor.getId() + " | Descricao: " + sensor.getDescricao());
		}
		
		UsuarioModel model = new UsuarioModel();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		model.setId(14);
		model.setCpf("025.280.915.75");
		model.setEmail("AiiramCruz@gmail.com");
		model.setPerfilUsuario(PerfilUsuarioEnum.ADMINISTRADOR);
		model.setNome("Leonardo Airam Muniz Cruz5");
		model.setSenha("123456");
		
		/*try {
			Field prop = model.getClass().getDeclaredField("perfilUsuario");
			
			for(Method method : prop.getClass().getMethods()) {
				System.out.println("Method: " + method.getName() + " | " + method.getParameterCount());
			}
			
			Method metodo = prop.getClass().getDeclaredMethod("toString", null);
			System.out.println(metodo.invoke(model, null));
			
			
			
		} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//System.out.println(usuarioDao.Inserir(model));
		

		
		ArrayList<UsuarioModel> listaUser = usuarioDao.ObterTodos();
		
		for(UsuarioModel user : listaUser) {
			System.out.println("ID: " + user.getId() + " | Nome: " + user.getNome() + " | Perfil: " + user.getPerfilUsuario());
		}
		
	}

}

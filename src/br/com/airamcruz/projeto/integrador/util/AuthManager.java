package br.com.airamcruz.projeto.integrador.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.airamcruz.projeto.integrador.model.UsuarioModel;

public class AuthManager {
	
	private static AuthManager _instance  = null;
	
	private UsuarioModel usuarioModel = null;
	
	public UsuarioModel getUsuario() {
		return usuarioModel;
	}
	
	public boolean autenticar(String cpf, String senha) {
		
		UsuarioModel model = new UsuarioModel();
		model.setCpf(cpf);
		model.setSenha(encryptPassword(senha));
		
		this.usuarioModel = ManagerDAO.getInstance().getUsuarioDAO().Autenticar(model);
		
		if(this.usuarioModel != null)
			return true;
		
		return false;
	}
	
	public boolean deslogar() {
		
		this.usuarioModel = null;
		
		return true;
	}
	
	public static String encryptPassword(String senha) {
		//https://www.javatpoint.com/how-to-encrypt-password-in-java
		String result = "";
		try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(senha.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            result = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }
		
		return result;
	}

	public static AuthManager getInstance() {
		if(_instance == null) {
			_instance = new AuthManager();
		}
		return _instance;
	}
}

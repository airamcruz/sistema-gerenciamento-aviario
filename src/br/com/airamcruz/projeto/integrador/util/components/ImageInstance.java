package br.com.airamcruz.projeto.integrador.util.components;

import javax.swing.ImageIcon;

import com.formdev.flatlaf.extras.FlatSVGIcon;

public class ImageInstance {
	
	/**
	 * 
	 * @param img
	 * Nome da imagem que deseja retornar
	 * @return
	 */
	public static ImageIcon getImageIcon(String name) {
		String extension = name.split("\\.")[1];
		
		ImageIcon imageIcon;
		
		if(extension.equalsIgnoreCase("svg")) {
			imageIcon = new FlatSVGIcon(ImageInstance.class.getClassLoader().getResource("resource/image/" + name)); 
		} else {
			imageIcon = new ImageIcon(ImageInstance.class.getClassLoader().getResource("resource/image/" + name));	
		}
		return imageIcon;
	}
	
	/**
	 * 
	 * @param img
	 * Nome da imagem que deseja retornar
	 * @param width
	 * Define a largura da imagem
	 * @param height
	 * Define a altura da imagem
	 * @return
	 */
	public static ImageIcon getImageIcon(String name, int width , int height) {
		String extension = name.split("\\.")[1];
		
		ImageIcon imageIcon;
		
		if(extension.equalsIgnoreCase("svg")) {
			imageIcon = new FlatSVGIcon(ImageInstance.class.getClassLoader().getResource("resource/image/" + name)).derive(width, height); 
		} else {
			imageIcon = new ImageIcon(ImageInstance.class.getClassLoader().getResource("resource/image/" + name));
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(width, height, 100));			
		}
		return imageIcon;
	}
	
}

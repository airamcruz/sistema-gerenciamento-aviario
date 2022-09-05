package br.com.airamcruz.projeto.integrador.util.components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;

public class IconFormattedTextField extends JFormattedTextField {

	private static final long serialVersionUID = 3387477157547386014L;

	private ImageIcon fundo;
	private int width;
	private int height;
	private int x;
	private int y;

	public IconFormattedTextField(String name, int x, int y, int width, int height) {
		fundo = ImageInstance.getImageIcon(name);
		this.setMargin(new Insets(2, 25, 2, 2));
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = fundo.getImage();
		// g.drawImage(img, 0, 0, this);
		g.drawImage(img, x, y, width, height, this);
	}
}

package br.com.airamcruz.projeto.integrador.util;

import java.net.URL;

import javax.swing.JLabel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

public class SVGImage extends JLabel {

	private static final long serialVersionUID = -91572932394662115L;
	
	private FlatSVGIcon svgIcon;
	
	public void setSVGIcon(String name, int width, int height) {
		svgIcon = new FlatSVGIcon(name, width, height);
		setIcon(svgIcon);
	}
	
	public void setSVGIcon(URL name, int width, int height) {
		svgIcon = new FlatSVGIcon(name).derive(width, height);
		setIcon(svgIcon);
	}
	
	public void setSVGIcon(String name) {
		svgIcon = new FlatSVGIcon(name);
		setIcon(svgIcon);
	}
}

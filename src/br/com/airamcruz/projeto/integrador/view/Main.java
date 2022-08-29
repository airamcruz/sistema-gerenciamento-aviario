package br.com.airamcruz.projeto.integrador.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.airamcruz.projeto.integrador.util.SVGImage;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SVGImage svg001 = new SVGImage();
		svg001.setBounds(10, 11, 200, 200);
		contentPane.add(svg001);
		
		SVGImage svg002 = new SVGImage();
		svg002.setBounds(224, 11, 200, 200);
		contentPane.add(svg002);
		
		svg001.setSVGIcon("br/com/airamcruz/projeto/integrador/view/user-add.svg", 200, 200);
		
		svg002.setSVGIcon(getClass().getClassLoader().getResource("image/user-add.svg"), 100, 100);
	}
}

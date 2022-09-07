package br.com.airamcruz.projeto.integrador.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.components.IconFormattedTextField;
import br.com.airamcruz.projeto.integrador.util.components.IconPasswordField;
import br.com.airamcruz.projeto.integrador.util.components.ImageInstance;

public class Main extends JFrame {

	private static final long serialVersionUID = -1274960478662649272L;

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
	 * 
	 * @throws ParseException
	 */
	public Main() throws ParseException {
		setTitle("Acesso ao Sistema");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 396, 263);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSistemaDeGesto = new JLabel("Sistema de Gerenciamento Aviário\r\n");
		lblSistemaDeGesto.setForeground(new Color(112, 128, 144));
		lblSistemaDeGesto.setBackground(new Color(139, 0, 0));
		lblSistemaDeGesto.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSistemaDeGesto.setBounds(82, 11, 290, 39);
		contentPane.add(lblSistemaDeGesto);

		JLabel label = new JLabel("");
		label.setIcon(ImageInstance.getImageIcon("frango.png", 120, 170));
		label.setBounds(10, 45, 151, 163);
		contentPane.add(label);

		JLabel lblLogin = new JLabel("Informe seu CPF:");
		lblLogin.setBounds(171, 61, 194, 14);
		contentPane.add(lblLogin);
		lblLogin.setFont(new Font("Verdana", Font.PLAIN, 11));

		DefaultFormatterFactory factory = new DefaultFormatterFactory(new MaskFormatter("###.###.###-##"));

		IconFormattedTextField txtLogin = new IconFormattedTextField("user.svg", 5, 5, 14, 14);
		txtLogin.setFormatterFactory(factory);
		txtLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtLogin.setBounds(171, 80, 201, 25);
		contentPane.add(txtLogin);
		txtLogin.setToolTipText("Digite seu CPF");
		txtLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Informe sua senha:");
		lblSenha.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblSenha.setBounds(171, 110, 194, 14);
		contentPane.add(lblSenha);

		IconPasswordField txtSenha = new IconPasswordField("password1.svg", 5, 5, 14, 14);
		txtSenha.setToolTipText("Digite seu CPF");
		txtSenha.setColumns(10);
		txtSenha.setAlignmentX(1.0f);
		txtSenha.setBounds(171, 130, 201, 25);
		contentPane.add(txtSenha);

		JButton btnLogin = new JButton("Login");
		btnLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (AuthManager.getInstance().autenticar(txtLogin.getText(), String.valueOf(txtSenha.getPassword()))) {
					setVisible(false);
					new Dashboard().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Login Invalido!");
				}

			}
		});
		btnLogin.setBounds(232, 167, 140, 25);
		contentPane.add(btnLogin);
		btnLogin.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLabel lblVersionBeta = new JLabel("Versao Beta");
		lblVersionBeta.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblVersionBeta.setForeground(new Color(128, 128, 128));
		lblVersionBeta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersionBeta.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblVersionBeta.setBounds(179, 195, 193, 14);
		contentPane.add(lblVersionBeta);

		JLabel lblLeonardoAiramMuniz = new JLabel("Leonardo Airam Muniz Cruz\r\n");
		lblLeonardoAiramMuniz.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblLeonardoAiramMuniz.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLeonardoAiramMuniz.setForeground(Color.GRAY);
		lblLeonardoAiramMuniz.setFont(new Font("Verdana", Font.PLAIN, 8));
		lblLeonardoAiramMuniz.setBounds(178, 209, 193, 14);
		contentPane.add(lblLeonardoAiramMuniz);
	}
}

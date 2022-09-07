package br.com.airamcruz.projeto.integrador.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.enums.PerfilUsuarioEnum;
import br.com.airamcruz.projeto.integrador.view.aviario.ListarAviario;
import br.com.airamcruz.projeto.integrador.view.fluxocaixa.ListarFluxoCaixa;
import br.com.airamcruz.projeto.integrador.view.lote.ListarLote;
import br.com.airamcruz.projeto.integrador.view.tiposensor.ListarTipoSensor;
import br.com.airamcruz.projeto.integrador.view.usuario.BuscarUsuarioModal;
import br.com.airamcruz.projeto.integrador.view.usuario.CadastrarRecuperarUsuario;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = -2919185229942168409L;
	
	private JPanel contentPane;
	private static JDesktopPane desktopPane;
	static Dashboard frame;

	public static JDesktopPane getDesktopPane()
	{
		if(desktopPane == null)
		{
			desktopPane = new JDesktopPane(); 
			desktopPane.setLayout(new BorderLayout(0, 0));
		}
		return desktopPane;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Dashboard();
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
	public Dashboard() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		
		setResizable(false);
		setTitle("Sistema de Gerenciamento Avi\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuario");
		mnUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JMenuItem mCadastrarUsuario = new JMenuItem("Cadastrar/Recuperar");
		mCadastrarUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		mCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdicionarFrameInternal(new CadastrarRecuperarUsuario(desktopPane.getWidth(), desktopPane.getHeight()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnUsuario.add(mCadastrarUsuario);
		
		JSeparator separator = new JSeparator();
		mnUsuario.add(separator);
		
		JMenuItem mBuscarUsuario = new JMenuItem("Buscar Usuario");
		mBuscarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarUsuarioModal jdialog = new BuscarUsuarioModal(false);
				jdialog.setLocationRelativeTo(frame);
				jdialog.setVisible(true);
			}
		});
		mBuscarUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnUsuario.add(mBuscarUsuario);
		
		JMenu mnTipoSensor = new JMenu("Tipo Sensor");
		mnTipoSensor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdicionarFrameInternal(new ListarTipoSensor(desktopPane.getWidth(), desktopPane.getHeight()));
			}
		});
		
		JMenu mnFluxoCaixa = new JMenu("Fluxo Caixa");
		mnFluxoCaixa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdicionarFrameInternal(new ListarFluxoCaixa(desktopPane.getWidth(), desktopPane.getHeight()));
			}
		});
		
		JMenu mnAviario = new JMenu("Aviario");
		mnAviario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdicionarFrameInternal(new ListarAviario(desktopPane.getWidth(), desktopPane.getHeight()));
			}
		});
		mnAviario.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JMenu mnLote = new JMenu("Lote");
		mnLote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdicionarFrameInternal(new ListarLote(desktopPane.getWidth(), desktopPane.getHeight()));
			}
		});
		mnLote.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnFluxoCaixa.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnTipoSensor.setFont(new Font("Verdana", Font.PLAIN, 12));
		menuBar.add(mnAviario);
		menuBar.add(mnLote);
		menuBar.add(mnFluxoCaixa);
		if(AuthManager.getInstance().getUsuario().getPerfilUsuario() == PerfilUsuarioEnum.ADMINISTRADOR) {
			menuBar.add(mnUsuario);
			menuBar.add(mnTipoSensor);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		desktopPane = getDesktopPane();
		desktopPane.setBounds(0, 0, 750, 450);
		desktopPane.setBackground(new Color(240, 240, 240));
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}
	public void AdicionarFrameInternal(JInternalFrame jIternalFrame) {
		desktopPane.removeAll();
		desktopPane.add(jIternalFrame);
		jIternalFrame.setVisible(true);
	}
}

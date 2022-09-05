package br.com.airamcruz.projeto.integrador.view.usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.MaskFormatter;

import br.com.airamcruz.projeto.integrador.controller.UsuarioController;
import br.com.airamcruz.projeto.integrador.util.components.ImageInstance;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;
import br.com.airamcruz.projeto.integrador.util.enums.PerfilUsuarioEnum;

public class CadastrarRecuperarUsuario extends JInternalFrame {
	
	private static final long serialVersionUID = -5669202609386139792L;

	private String[] usuarioData = null;

	private UsuarioController Controller = new UsuarioController();

	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JFormattedTextField txtCPF;
	private JPasswordField txtSenha;
	private JPanel conteudo;
	private JPanel barraInferior;
	private JPanel panelInformacoes;
	private JPanel panelAviarios;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JTabbedPane tabbedPane;
	private JComboBox cbxPerfil;
	private JScrollPane scrollPane_1;
	private JTable table;
	private TabelaModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarRecuperarUsuario frame = new CadastrarRecuperarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void close() {
		this.dispose();
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public CadastrarRecuperarUsuario() throws ParseException {
		setClosable(true);
		setVerifyInputWhenFocusTarget(false);
		setRootPaneCheckingEnabled(false);
		setBounds(100, 100, 735, 419);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Id do cliente: ");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel_1.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel_1.add(txtId);
		txtId.setColumns(15);
		
		JButton btnRecuperar = new JButton("Recuperar");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!txtId.getText().isEmpty())
				{
					usuarioData = Controller.Obter(Integer.parseInt(txtId.getText()));
					if(usuarioData != null) {
						PreencherCamposDados(usuarioData);
						ativarComponentes(true, true);
						//ListarCargos();
						//PreencherTabela();						
					}
					else {
						JOptionPane.showMessageDialog(null, "Usuario não encontrado!");
						usuarioData = null;
						ativarComponentes(false, true);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Matricula Invalida!");
					usuarioData = null;
					ativarComponentes(false, true);
				}
			}
		});
		btnRecuperar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnRecuperar.setIcon(ImageInstance.getImageIcon("user-edit.svg", 16, 16));
		panel_1.add(btnRecuperar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarComponentes(true, false);
				tabbedPane.setEnabledAt(1, false);
				
				txtId.setText("");
				txtNome.setText("");
				txtEmail.setText("");
				txtCPF.setText("");
				txtSenha.setText("");
				cbxPerfil.setSelectedItem(PerfilUsuarioEnum.ADMINISTRADOR);

				usuarioData = null;
				//ListarCargos();
			}
		});
		btnCadastrar.setIcon(ImageInstance.getImageIcon("user-add.svg", 16, 16));
		btnCadastrar.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_1.add(btnCadastrar);
		
		barraInferior = new JPanel();
		barraInferior.setVisible(false);
		barraInferior.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel.add(barraInferior, BorderLayout.SOUTH);
		barraInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarComponentes(false, true);
				tabbedPane.setEnabledAt(1, false);

				txtId.setText("");
				txtNome.setText("");
				txtEmail.setText("");
				txtCPF.setText("");
				txtSenha.setText("");
				cbxPerfil.setSelectedItem(PerfilUsuarioEnum.ADMINISTRADOR);
			}
		});
		btnCancelar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnCancelar.setIcon(ImageInstance.getImageIcon("cancel.svg", 16, 16));
		barraInferior.add(btnCancelar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controller.Excluir(Integer.parseInt(usuarioData[0])))
				{
					JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso!");
					usuarioData = null;
					ativarComponentes(false, true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Não foi possivel excluir o usuario!");
				}
			}
		});
		btnExcluir.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnExcluir.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnExcluir.setIcon(ImageInstance.getImageIcon("delete.svg", 16, 16));
		barraInferior.add(btnExcluir);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usuarioData == null)
				{
					if(cbxPerfil.getSelectedItem() == null)
					{
						JOptionPane.showMessageDialog(null, "Selecione o perfil do usuario!");
						return;
					}

					int idRegistro = Controller.Inserir(txtNome.getText(), txtEmail.getText(), txtCPF.getText(), cbxPerfil.getSelectedItem().toString(), String.valueOf(txtSenha.getPassword()));
					if(idRegistro > 0)
					{
						JOptionPane.showMessageDialog(null, "Registro Salvo com Sucesso!");
						txtId.setText(String.valueOf(idRegistro));
						tabbedPane.setEnabledAt(1, true);
						//RecarregarDadosTabelaHorarioAgenda(funcionario.getHorariosAgendados());
						txtSenha.setText("");
						ativarComponentes(true, true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Erro ao Cadastrar o Cliente!");
					}
				}
				else
				{
					boolean registroAtualizado = Controller.Atualizar(Integer.parseInt(txtId.getText()), txtNome.getText(), txtEmail.getText(), txtCPF.getText(), cbxPerfil.getSelectedItem().toString(), String.valueOf(txtSenha.getPassword()));
					if(registroAtualizado)
					{
						JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
						txtSenha.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Não foi possivel atualizar o usuario!");
					}
				}
			}
		});
		btnSalvar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnSalvar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnSalvar.setIcon(ImageInstance.getImageIcon("save.svg", 16, 16));
		barraInferior.add(btnSalvar);
		
		conteudo = new JPanel();
		conteudo.setVisible(false);
		panel.add(conteudo, BorderLayout.CENTER);
		conteudo.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		conteudo.add(tabbedPane, BorderLayout.CENTER);
		
		panelInformacoes = new JPanel();
		tabbedPane.addTab("Informações", null, panelInformacoes, null);
		panelInformacoes.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNome.setBounds(21, 24, 300, 14);
		panelInformacoes.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(21, 45, 300, 20);
		panelInformacoes.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblPerfil = new JLabel("Perfil:");
		lblPerfil.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPerfil.setBounds(404, 85, 104, 14);
		panelInformacoes.add(lblPerfil);
		
		cbxPerfil = new JComboBox(PerfilUsuarioEnum.values());
		cbxPerfil.setBounds(404, 105, 300, 22);
		panelInformacoes.add(cbxPerfil);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEmail.setBounds(404, 24, 300, 14);
		panelInformacoes.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(404, 45, 300, 20);
		panelInformacoes.add(txtEmail);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCPF.setBounds(21, 85, 300, 14);
		panelInformacoes.add(lblCPF);
		
		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCPF.setColumns(10);
		txtCPF.setBounds(21, 106, 300, 20);
		panelInformacoes.add(txtCPF);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSenha.setBounds(21, 152, 300, 14);
		panelInformacoes.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(21, 173, 300, 20);
		panelInformacoes.add(txtSenha);
		
		panelAviarios = new JPanel();
		tabbedPane.addTab("Agendamentos", null, panelAviarios, null);
		panelAviarios.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panelAviarios.add(scrollPane_1, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);

	}

	public CadastrarRecuperarUsuario(int width, int height) throws ParseException {
		this();
		setBounds(0, 0, width, height);
		try {
			setMaximum(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException | PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setBorder(null);
	}

	protected void PreencherCamposDados(String[] usuarioData) {
		txtNome.setText(usuarioData[1]);
		txtCPF.setText(usuarioData[2]);
		txtEmail.setText(usuarioData[3]);
		txtSenha.setText("");
		cbxPerfil.setSelectedItem(PerfilUsuarioEnum.valueOf(usuarioData[4]));
	}

	protected void ativarComponentes(boolean estado, boolean recuperar) {
		conteudo.setVisible(estado);
		barraInferior.setVisible(estado);
		btnExcluir.setVisible(recuperar);
		btnCancelar.setVisible(!recuperar);
	}
/*
	private void RecarregarDadosTabelaHorarioAgenda(List<Agenda> lista) {
		modelo.clear();
		for (Agenda agenda : lista) {
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			cal.setTime(agenda.getDataAgendada());
			modelo.addRow(new Object[] { formatter.format(cal.getTime()), agenda.getCliente().getNome(),
					agenda.getServicos().get(0).getDescricao(), agenda.getObservacao() });
		}
	}

	private void PreencherTabela() {
		String[] Colunas = new String[] { "HORARIO MARCADO", "NOME DO CLIENTE", "SERVICO", "OBSERVACAO DO CLIENTE" };

		modelo = new TabelaModel(Colunas, new ArrayList<Object[]>());

		table.setModel(modelo);

		RecarregarDadosTabelaHorarioAgenda(funcionario.getHorariosAgendados());

		ajustarColunasTabela();

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		table.setEnabled(false);
		table.getTableHeader().setEnabled(false);
	}

	private void ajustarColunasTabela() {
		ArrayList<TableColumn> colunas = Collections.list(table.getColumnModel().getColumns());

		for (TableColumn coluna : colunas) {
			coluna.setResizable(false);
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		colunas.get(0).setPreferredWidth(150);
		colunas.get(1).setPreferredWidth(210);
		colunas.get(2).setPreferredWidth(100);
		colunas.get(3).setPreferredWidth(255);
	}*/
}

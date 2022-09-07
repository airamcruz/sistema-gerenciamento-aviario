package br.com.airamcruz.projeto.integrador.view.usuario;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import br.com.airamcruz.projeto.integrador.controller.AviarioController;
import br.com.airamcruz.projeto.integrador.controller.SensorController;
import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.SensorModel;
import br.com.airamcruz.projeto.integrador.util.components.ImageInstance;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;
import br.com.airamcruz.projeto.integrador.util.enums.EstadoAviarioEnum;
import br.com.airamcruz.projeto.integrador.view.tiposensor.InformacoesTipoSensorModal;

public class InformacoesAviarioModal extends JDialog {

	private static final long serialVersionUID = 8505461754269649451L;
	private static InformacoesAviarioModal dialog;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescricao;
	private boolean sucessoAlteracao = false;

	private JTable table;
	private JComboBox cbxEstadoAviario;
	private TabelaModel modeloTabela;

	private int IdSensorSelected;
	private JButton btnNovoSensor;
	private JButton btnAlterarSensor;
	private JButton btnExcluirSensor;

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private AviarioController aviarioController = new AviarioController();
	private SensorController sensorController = new SensorController();

	private List<SensorModel> sensores = new ArrayList<SensorModel>();

	private AviarioModel aviarioData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new InformacoesAviarioModal(0, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void close() {
		this.dispose();
	}

	public boolean sucessoAlteracao() {
		return this.sucessoAlteracao;
	}

	/**
	 * Create the dialog.
	 */
	public InformacoesAviarioModal(int id, int usuarioId) {

		this.aviarioData = aviarioController.Obter(id);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (id > 0) {
					PreencherTabela(sensorController.ObterPorAviario(id));
				} else {
					btnNovoSensor.setVisible(false);
					btnAlterarSensor.setVisible(false);
					btnExcluirSensor.setVisible(false);
				}
			}
		});
		setResizable(false);
		setTitle("Informa\u00E7\u00F5es do Aviario");
		setModal(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);

		setModal(true);
		setBounds(100, 100, 571, 435);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAviario = new JLabel("Descricao do Aviario:");
			lblAviario.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblAviario.setBounds(10, 11, 198, 14);
			contentPanel.add(lblAviario);
		}
		{
			txtDescricao = new JTextField();
			txtDescricao.setBounds(10, 36, 254, 20);
			contentPanel.add(txtDescricao);
			txtDescricao.setColumns(10);
			if (id > 0)
				txtDescricao.setText(aviarioData.getDescricao());
		}

		JPanel panelSensor = new JPanel();
		panelSensor.setBounds(10, 67, 545, 293);
		contentPanel.add(panelSensor);
		panelSensor.setLayout(null);

		JLabel lblHistoricoLote = new JLabel("Sensores do Aviario:");
		lblHistoricoLote.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblHistoricoLote.setBounds(10, 11, 243, 14);
		panelSensor.add(lblHistoricoLote);

		JPanel panelListAddSensor = new JPanel();
		panelListAddSensor.setBounds(10, 36, 525, 246);
		panelSensor.add(panelListAddSensor);
		panelListAddSensor.setLayout(new BorderLayout(0, 0));

		JPanel panelAcoesSensor = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelAcoesSensor.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelListAddSensor.add(panelAcoesSensor, BorderLayout.NORTH);

		btnExcluirSensor = new JButton("Excluir");
		btnExcluirSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (aviarioController.Excluir(IdSensorSelected)) {
					JOptionPane.showMessageDialog(null, "Aviario excluido com sucesso!");
					btnExcluirSensor.setVisible(false);
					btnAlterarSensor.setVisible(false);
					RecarregarDadosTabela(sensorController.ObterPorAviario(id));
				} else {
					JOptionPane.showMessageDialog(null, "Não foi excluir o Aviario!");
				}
			}
		});
		btnExcluirSensor.setVisible(false);
		btnExcluirSensor.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnExcluirSensor.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnExcluirSensor.setIcon(ImageInstance.getImageIcon("delete.svg", 16, 16));
		panelAcoesSensor.add(btnExcluirSensor);

		btnAlterarSensor = new JButton("Alterar");
		btnAlterarSensor.setVisible(false);
		btnAlterarSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbrirJanelaAlterar(IdSensorSelected);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAlterarSensor.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAlterarSensor.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAlterarSensor.setIcon(ImageInstance.getImageIcon("cancel.svg", 16, 16));
		panelAcoesSensor.add(btnAlterarSensor);

		btnNovoSensor = new JButton("Novo");
		btnNovoSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbrirJanelaAlterar(0);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNovoSensor.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnNovoSensor.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNovoSensor.setIcon(ImageInstance.getImageIcon("save.svg", 16, 16));
		panelAcoesSensor.add(btnNovoSensor);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 525, 247);
		panelListAddSensor.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					IdSensorSelected = modeloTabela.getIdRowSelected(index);
					btnExcluirSensor.setVisible(true);
					btnAlterarSensor.setVisible(true);
					if (e.getClickCount() == 2) {
						try {
							AbrirJanelaAlterar(IdSensorSelected);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblEstadoAviario = new JLabel("Estado do Aviario:");
		lblEstadoAviario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEstadoAviario.setBounds(300, 14, 245, 14);
		contentPanel.add(lblEstadoAviario);

		cbxEstadoAviario = new JComboBox(EstadoAviarioEnum.values());
		cbxEstadoAviario.setBounds(300, 34, 245, 22);
		if (id > 0)
			cbxEstadoAviario.setSelectedItem(aviarioData.getEstadoAviario());

		contentPanel.add(cbxEstadoAviario);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			{
				JButton btnSalvar = new JButton("Salvar");
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (id > 0) {
							if (aviarioController.Atualizar(aviarioData.getId(), txtDescricao.getText(),
									cbxEstadoAviario.getSelectedItem().toString(), usuarioId)) {
								JOptionPane.showMessageDialog(null, "Registro Salvo com Sucesso!");
								sucessoAlteracao = true;
								close();
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao atualizar tipo do sensor!");
							}
						} else {
							int idAviario = aviarioController.Inserir(txtDescricao.getText(),
									cbxEstadoAviario.getSelectedItem().toString(), usuarioId);

							if (idAviario > 0) {
								JOptionPane.showMessageDialog(null, "Registro Salvo com Sucesso!");
								sucessoAlteracao = true;
								close();
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao atualizar tipo do sensor!");
							}
						}
					}
				});
				btnSalvar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnSalvar.setActionCommand("OK");
				buttonPane.add(btnSalvar);
				getRootPane().setDefaultButton(btnSalvar);
			}
		}
	}

	private void AbrirJanelaAlterar(int id) {
		CadastroInformacaoSensorModal jdialog = new CadastroInformacaoSensorModal(id, aviarioData.getId());
		jdialog.setLocationRelativeTo(dialog);
		jdialog.setVisible(true);
		if (jdialog.sucessoAlteracao()) {
			RecarregarDadosTabela(sensorController.ObterPorAviario(aviarioData.getId()));
		}
	}

	private void RecarregarDadosTabela(List<SensorModel> linhas) {
		modeloTabela.clear();
		for (SensorModel row : linhas) {
			modeloTabela.addRow(new Object[] { row.getId(), row.getDescricao(),
					formatter.format(row.getDataInstalacao()), row.getTipoSensorModel().getDescricao() });
		}
	}

	private void PreencherTabela(List<SensorModel> lista) {
		String[] Colunas = new String[] { "ID", "DESCRIÇÃO", "DATA INSTALAÇÃO", "TIPO DO SENSOR" };
		modeloTabela = new TabelaModel(Colunas, new ArrayList<Object[]>());
		table.setModel(modeloTabela);
		RecarregarDadosTabela(lista);
		// ajustarColunasTabela();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
	}
}

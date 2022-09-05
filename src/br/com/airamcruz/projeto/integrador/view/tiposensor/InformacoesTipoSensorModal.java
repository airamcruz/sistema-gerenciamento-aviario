package br.com.airamcruz.projeto.integrador.view.tiposensor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
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
import javax.swing.table.TableColumn;

import br.com.airamcruz.projeto.integrador.controller.SensorController;
import br.com.airamcruz.projeto.integrador.controller.TipoSensorController;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;

public class InformacoesTipoSensorModal extends JDialog {

	private static final long serialVersionUID = 8505461754269649451L;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescricao;
	private boolean sucessoAlteracao = false;

	private JTable table;
	private TabelaModel modeloTabela;
	TipoSensorController Controller = new TipoSensorController();

	String[] tipoSensorData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InformacoesTipoSensorModal dialog = new InformacoesTipoSensorModal(0);
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
	public InformacoesTipoSensorModal(int id) {

		this.tipoSensorData = Controller.Obter(id);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				PreencherTabela();
			}
		});
		setResizable(false);
		setTitle("Adicionar Cargo");
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
			JLabel lblNewLabel = new JLabel("Descricao do Cargo:");
			lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 11, 159, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			txtDescricao = new JTextField();
			txtDescricao.setBounds(10, 36, 304, 20);
			contentPanel.add(txtDescricao);
			txtDescricao.setColumns(10);
			txtDescricao.setText(tipoSensorData[1]);
		}

		JPanel panel = new JPanel();
		panel.setBounds(10, 67, 545, 293);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Funcionarios associados:");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 11, 334, 14);
		panel.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 525, 247);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
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
						if (Controller.Atualizar(Integer.parseInt(tipoSensorData[0]), txtDescricao.getText())) {
							JOptionPane.showMessageDialog(null, "Registro Salvo com Sucesso!");
							sucessoAlteracao = true;
							close();
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao atualizar tipo do sensor!");
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

	private void RecarregarDadosTabela(List<String[]> linhas) {
		modeloTabela.clear();
		for (String[] row : linhas) {
			modeloTabela.addRow(new String[] { row[0], row[1], row[2] });
		}
	}

	private void PreencherTabela() {
		String[] Colunas = new String[] { "ID", "DESCRIÇÂO", "DATA INSTALAÇAO" };
		modeloTabela = new TabelaModel(Colunas, new ArrayList<Object[]>());
		table.setModel(modeloTabela);

		SensorController sensorController = new SensorController();

		RecarregarDadosTabela(sensorController.ObterPorTipoSensor(Integer.parseInt(tipoSensorData[0])));

		//ajustarColunasTabela();
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
		colunas.get(0).setPreferredWidth(100);
		colunas.get(1).setPreferredWidth(420);
	}
}

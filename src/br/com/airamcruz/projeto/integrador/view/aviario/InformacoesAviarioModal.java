package br.com.airamcruz.projeto.integrador.view.aviario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
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

import br.com.airamcruz.projeto.integrador.controller.AviarioController;
import br.com.airamcruz.projeto.integrador.controller.LoteController;
import br.com.airamcruz.projeto.integrador.controller.SensorController;
import br.com.airamcruz.projeto.integrador.controller.TipoSensorController;
import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.LoteModel;
import br.com.airamcruz.projeto.integrador.model.SensorModel;
import br.com.airamcruz.projeto.integrador.model.TipoSensorModel;
import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;
import br.com.airamcruz.projeto.integrador.util.enums.EstadoAviarioEnum;

import javax.swing.JComboBox;

public class InformacoesAviarioModal extends JDialog {

	private static final long serialVersionUID = 8505461754269649451L;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescricao;
	private boolean sucessoAlteracao = false;

	private JTable table;
	private JComboBox<EstadoAviarioEnum> cbxEstadoAviario;
	private TabelaModel modeloTabela;

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private AviarioController Controller = new AviarioController();

	private AviarioModel aviarioData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InformacoesAviarioModal dialog = new InformacoesAviarioModal(0);
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
	public InformacoesAviarioModal(int id) {

		this.aviarioData = Controller.Obter(id);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (id > 0)
					PreencherTabela();
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

		JPanel panel = new JPanel();
		panel.setBounds(10, 67, 545, 293);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblHistoricoLote = new JLabel("Historico de Lotes:");
		lblHistoricoLote.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblHistoricoLote.setBounds(10, 11, 243, 14);
		panel.add(lblHistoricoLote);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 525, 247);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblEstadoAviario = new JLabel("Estado do Aviario:");
		lblEstadoAviario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEstadoAviario.setBounds(300, 14, 245, 14);
		contentPanel.add(lblEstadoAviario);

		cbxEstadoAviario = new JComboBox<EstadoAviarioEnum>(EstadoAviarioEnum.values());
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
						if (Controller.Atualizar(aviarioData.getId(), txtDescricao.getText(),
								cbxEstadoAviario.getSelectedItem().toString(),
								AuthManager.getInstance().getUsuario().getId())) {
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

	private void RecarregarDadosTabela(List<LoteModel> linhas) {
		modeloTabela.clear();
		for (LoteModel row : linhas) {
			String previsaoAbate = row.getPrevisaoAbate() != null ? formatter.format(row.getPrevisaoAbate()) : "";
			modeloTabela.addRow(new Object[] { row.getId(), row.getDescricao(), formatter.format(row.getDataCompra()),
					row.getQuantidadeFrangos(), previsaoAbate, row.getAviarioModel().getDescricao() });
		}
	}

	private void PreencherTabela() {
		String[] Colunas = new String[] { "ID", "DESCRIÇÂO", "DATA COMPRA", "QUANTIDADE DE FRANGOS", "PREVISÃO DO ABATE" };
		modeloTabela = new TabelaModel(Colunas, new ArrayList<Object[]>());
		table.setModel(modeloTabela);

		LoteController loteController = new LoteController();

		RecarregarDadosTabela(loteController.ObterPorAviario(aviarioData.getId()));

		// ajustarColunasTabela();
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

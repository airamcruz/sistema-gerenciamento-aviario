package br.com.airamcruz.projeto.integrador.view.aviario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableColumn;

import br.com.airamcruz.projeto.integrador.controller.AviarioController;
import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.util.components.ImageInstance;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;

public class ListarAviario extends JInternalFrame {

	private static final long serialVersionUID = -4323134571144509443L;

	private static ListarAviario frame;
	private JTextField txtNome;
	private JPanel conteudo;
	private JPanel barraInferior;
	private JButton btnAlterar;
	private int IdSelected;

	private JTable table;
	private TabelaModel modelo;

	AviarioController Controller = new AviarioController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ListarAviario();
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
	 */
	public ListarAviario() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				PreencherTabela(Controller.ObterPorUsuarioDescricao(""));
			}
		});
		setClosable(true);
		setVerifyInputWhenFocusTarget(false);
		setRootPaneCheckingEnabled(false);
		setBounds(100, 100, 607, 450);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Nome do Aviario:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel_1.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel_1.add(txtNome);
		txtNome.setColumns(15);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecarregarDadosTabela(Controller.ObterPorUsuarioDescricao(txtNome.getText()));
			}
		});
		btnBuscar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnBuscar.setIcon(ImageInstance.getImageIcon("user-edit.svg", 16, 16));
		panel_1.add(btnBuscar);

		barraInferior = new JPanel();
		barraInferior.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel.add(barraInferior, BorderLayout.SOUTH);
		barraInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnAlterar = new JButton("Alterar");
		btnAlterar.setVisible(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbrirJanelaAlterar(IdSelected);
			}
		});
		btnAlterar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAlterar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAlterar.setIcon(ImageInstance.getImageIcon("cancel.svg", 16, 16));
		barraInferior.add(btnAlterar);

		conteudo = new JPanel();
		panel.add(conteudo, BorderLayout.CENTER);
		conteudo.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		conteudo.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					IdSelected = modelo.getIdRowSelected(index);
					btnAlterar.setVisible(true);
					if (e.getClickCount() == 2) {
						AbrirJanelaAlterar(IdSelected);
					}

				}
			}
		});
		scrollPane.setViewportView(table);

	}

	public ListarAviario(int width, int height) {
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

	private void RecarregarDadosTabela(List<AviarioModel> lista) {
		modelo.clear();
		for (AviarioModel row : lista) {
			modelo.addRow(new Object[] { row.getId(), row.getDescricao(), row.getEstadoAviario() });
		}
	}

	private void PreencherTabela(List<AviarioModel> list) {
		String[] Colunas = new String[] { "ID", "DESCRIÇÂO", "ESTADO DO AVIARIO" };

		modelo = new TabelaModel(Colunas, new ArrayList<Object[]>());

		table.setModel(modelo);

		RecarregarDadosTabela(list);

		//ajustarColunasTabela();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
	}

	private void ajustarColunasTabela() {

		ArrayList<TableColumn> colunas = Collections.list(table.getColumnModel().getColumns());

		for (TableColumn coluna : colunas) {
			coluna.setResizable(false);
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		colunas.get(0).setPreferredWidth(70);
		colunas.get(1).setPreferredWidth(150);
		colunas.get(2).setPreferredWidth(200);
	}

	private void AbrirJanelaAlterar(int id) {
		InformacoesAviarioModal jdialog = new InformacoesAviarioModal(id);
		jdialog.setLocationRelativeTo(frame);
		jdialog.setVisible(true);
		if (jdialog.sucessoAlteracao()) {
			RecarregarDadosTabela(Controller.ObterPorUsuarioDescricao(txtNome.getText()));
		}
	}
}

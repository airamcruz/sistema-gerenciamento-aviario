package br.com.airamcruz.projeto.integrador.view.fluxocaixa;

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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import br.com.airamcruz.projeto.integrador.controller.FluxoCaixaController;
import br.com.airamcruz.projeto.integrador.util.components.ImageInstance;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;

public class ListarFluxoCaixa extends JInternalFrame {

	private static ListarFluxoCaixa frame;
	private MaskFormatter mascara = null;
	private JPanel conteudo;
	private JPanel barraInferior;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private int IdSelected;

	private JTable table;
	private TabelaModel modelo;
	FluxoCaixaController Controller = new FluxoCaixaController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ListarFluxoCaixa();
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
	public ListarFluxoCaixa() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				PreencherTabela(Controller.ObterTodos());
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

		barraInferior = new JPanel();
		barraInferior.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel.add(barraInferior, BorderLayout.SOUTH);
		barraInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controller.Excluir(IdSelected)) {
					JOptionPane.showMessageDialog(null, "Agendamento excluido com sucesso!");
					RecarregarDadosTabela(Controller.ObterTodos());
				} else {
					JOptionPane.showMessageDialog(null, "Não foi Agendamento excluir o Cargo!");
				}
			}
		});
		btnExcluir.setVisible(false);
		btnExcluir.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnExcluir.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnExcluir.setIcon(ImageInstance.getImageIcon("delete.svg", 16, 16));
		barraInferior.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setVisible(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbrirJanelaAlterar(IdSelected);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAlterar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAlterar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAlterar.setIcon(ImageInstance.getImageIcon("cancel.svg", 16, 16));
		barraInferior.add(btnAlterar);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbrirJanelaAlterar(0);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNovo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnNovo.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNovo.setIcon(ImageInstance.getImageIcon("save.svg", 16, 16));
		barraInferior.add(btnNovo);

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
					btnExcluir.setVisible(true);
					btnAlterar.setVisible(true);
					if (e.getClickCount() == 2) {
						try {
							AbrirJanelaAlterar(IdSelected);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			}
		});
		scrollPane.setViewportView(table);

	}

	public ListarFluxoCaixa(int width, int height) {
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

	private void RecarregarDadosTabela(List<String[]> linhas) {
		modelo.clear();
		for (String[] row : linhas) {
			modelo.addRow(row);
		}
	}

	private void PreencherTabela(List<String[]> linhas) {
		String[] Colunas = new String[] { "ID", "DATA", "VALOR", "TIPO FLUXO CAIXA" };

		modelo = new TabelaModel(Colunas, new ArrayList<Object[]>());

		table.setModel(modelo);

		RecarregarDadosTabela(linhas);

		ajustarColunasTabela();
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
		colunas.get(3).setPreferredWidth(300);
	}

	private void AbrirJanelaAlterar(int id) throws ParseException {
		CadastroInformacaoFluxoCaixaModal jdialog = new CadastroInformacaoFluxoCaixaModal(id);
		jdialog.setLocationRelativeTo(frame);
		jdialog.setVisible(true);
		if (jdialog.sucessoAlteracao()) {
			RecarregarDadosTabela(Controller.ObterTodos());
		}
	}
}

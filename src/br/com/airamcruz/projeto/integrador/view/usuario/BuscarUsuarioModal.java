package br.com.airamcruz.projeto.integrador.view.usuario;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableColumn;

import br.com.airamcruz.projeto.integrador.controller.UsuarioController;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;

public class BuscarUsuarioModal extends JDialog {

	private static final long serialVersionUID = 300371925310363890L;
	
	private JTextField txtNome;
	private JTable table;
	private JButton btnBuscar;
	
	private TabelaModel modeloTabela;
	
	private UsuarioController Controller = new UsuarioController();
	
	private int idClienteSelecionado = 0;
	
	public int getClienteSelecionado()
	{
		return this.idClienteSelecionado;
	}
	
	private void close()
	{
		this.dispose();
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarUsuarioModal dialog = new BuscarUsuarioModal(false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarUsuarioModal(boolean buscar) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				PreencherTabela(Controller.ObterTodos());
			}
		});
		setResizable(false);
		setTitle("Buscar Cliente");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		
		setModal(true);
		setBounds(100, 100, 600, 350);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Nome do Cliente:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNome.setColumns(15);
		panel.add(txtNome);
		
		btnBuscar = new JButton("Buscar Cliente");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecarregarDadosTabela(Controller.ObterPorNome(txtNome.getText()));
			}
		});
		btnBuscar.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panelBusca = new JPanel();
		panelBusca.setVisible(false);
		FlowLayout flowLayout = (FlowLayout) panelBusca.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelBusca, BorderLayout.SOUTH);
		
		JButton btnSelecionar = new JButton("Selecionar Cliente");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idClienteSelecionado = (int) table.getValueAt(table.getSelectedRow(), 0);
				close();
			}
		});
		btnSelecionar.setFont(new Font("Verdana", Font.PLAIN, 12));
		panelBusca.add(btnSelecionar);
		if(buscar)
			panelBusca.setVisible(true);
	}
	
	private void RecarregarDadosTabela(List<String[]> colunas)
	{
		modeloTabela.clear();
		for(String[] col : colunas)
		{
			modeloTabela.addRow(col);
		}
	}
	
	private void PreencherTabela(List<String[]> lista)
	{
		String[] Colunas = new String[] {"ID", "NOME", "CPF", "EMAIL", "PERFIL"};
		modeloTabela = new TabelaModel(Colunas, new ArrayList<Object[]>());
		table.setModel(modeloTabela);
		RecarregarDadosTabela(lista);
		ajustarColunasTabela();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
	}
	private void  ajustarColunasTabela()
	{
		ArrayList<TableColumn> colunas=Collections.list(table.getColumnModel().getColumns());

		for(TableColumn coluna : colunas)
		{
			coluna.setResizable(false);
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		colunas.get(0).setPreferredWidth(50);
		colunas.get(1).setPreferredWidth(200);
		colunas.get(2).setPreferredWidth(80);
		colunas.get(3).setPreferredWidth(100);
		colunas.get(4).setPreferredWidth(150);
	}

}

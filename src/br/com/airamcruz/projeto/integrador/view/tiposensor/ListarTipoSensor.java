package br.com.airamcruz.projeto.integrador.view.tiposensor;

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
import javax.swing.JOptionPane;
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

import br.com.airamcruz.projeto.integrador.controller.TipoSensorController;
import br.com.airamcruz.projeto.integrador.util.components.ImageInstance;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;

public class ListarTipoSensor extends JInternalFrame {

	private static final long serialVersionUID = 1950100651872291856L;
	
	private static ListarTipoSensor frame;
	private JTextField txtNome;
	private JPanel conteudo;
	private JPanel barraInferior;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private int IdSelected;

	private JTable table;
	private TabelaModel modeloTabela;
	TipoSensorController Controller = new TipoSensorController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ListarTipoSensor();
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
	public ListarTipoSensor() {
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
		
		JLabel lblNewLabel = new JLabel("Nome do Tipo do Sensor:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel_1.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel_1.add(txtNome);
		txtNome.setColumns(15);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecarregarDadosTabela(Controller.ObterPorDescricao(txtNome.getText()));
			}
		});
		btnBuscar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnBuscar.setIcon(ImageInstance.getImageIcon("user-edit.svg", 16, 16));
		panel_1.add(btnBuscar);
		
		barraInferior = new JPanel();
		barraInferior.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel.add(barraInferior, BorderLayout.SOUTH);
		barraInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controller.Excluir(IdSelected))
				{
					JOptionPane.showMessageDialog(null, "Tipo de Sensor excluido com sucesso!");
					RecarregarDadosTabela(Controller.ObterPorDescricao(txtNome.getText()));
				}
				else {
					JOptionPane.showMessageDialog(null, "Não foi possivel excluir o Tipo de Sensor!");
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
				AbrirJanelaAlterar();
			}
		});
		btnAlterar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAlterar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAlterar.setIcon(ImageInstance.getImageIcon("cancel.svg", 16, 16));
		barraInferior.add(btnAlterar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarTipoSensorModal jdialog = new CadastrarTipoSensorModal();
				jdialog.setLocationRelativeTo(frame);
				jdialog.setVisible(true);
				if(jdialog.sucessoCadastro())
				{
					RecarregarDadosTabela(Controller.ObterPorDescricao(txtNome.getText()));
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
				if(index >= 0)
				{
					IdSelected = modeloTabela.getIdRowSelected(index);
					btnExcluir.setVisible(true);
					btnAlterar.setVisible(true);
					if (e.getClickCount() == 2) {
						AbrirJanelaAlterar();
					}
				}
			}
		});
		scrollPane.setViewportView(table);

	}
	private void AbrirJanelaAlterar() {
		InformacoesTipoSensorModal jdialog = new InformacoesTipoSensorModal(IdSelected);
		jdialog.setLocationRelativeTo(frame);
		jdialog.setVisible(true);
		if(jdialog.sucessoAlteracao())
		{
			RecarregarDadosTabela(Controller.ObterPorDescricao(txtNome.getText()));
		}
	}

	public ListarTipoSensor(int width, int height) {
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
	
	private void RecarregarDadosTabela(List<String[]> linhas)
	{
		modeloTabela.clear();
		for(String[] row : linhas)
		{
			modeloTabela.addRow(row);
		}
	}
	
	private void PreencherTabela(List<String[]> lista)
	{
		String[] Colunas = new String[] {"ID", "DESCRIÇÃO"};
		modeloTabela = new TabelaModel(Colunas, new ArrayList<Object[]>());
		table.setModel(modeloTabela);
		RecarregarDadosTabela(lista);
		//ajustarColunasTabela();
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
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		colunas.get(0).setPreferredWidth(200);
		colunas.get(1).setPreferredWidth(520);
	}
}

package br.com.airamcruz.projeto.integrador.view.tiposensor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import br.com.airamcruz.projeto.integrador.controller.TipoSensorController;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;

public class CadastrarTipoSensorModal extends JDialog {

	private static final long serialVersionUID = 2102368942029705803L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescricao;
	TipoSensorController Controller = new TipoSensorController();
	private boolean sucessoCadastro = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastrarTipoSensorModal dialog = new CadastrarTipoSensorModal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close()
	{
		this.dispose();
	}
	
	public boolean sucessoCadastro()
	{
		return this.sucessoCadastro;
	}

	/**
	 * Create the dialog.
	 */
	public CadastrarTipoSensorModal() {
		
		setResizable(false);
		setTitle("Adicionar Tipo do Sensor");
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
		setBounds(100, 100, 340, 140);
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
		}
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
						if(Controller.Inserir(txtDescricao.getText()))
						{
							JOptionPane.showMessageDialog(null, "Registro Salvo com Sucesso!");
							sucessoCadastro = true;
							close();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Erro ao Cadastrar o tipo do sensor!");
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
}

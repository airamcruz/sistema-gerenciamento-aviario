package br.com.airamcruz.projeto.integrador.view.fluxocaixa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeArea;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import br.com.airamcruz.projeto.integrador.controller.FluxoCaixaController;
import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.components.TabelaModel;
import br.com.airamcruz.projeto.integrador.util.enums.TipoFluxoCaixaEnum;

public class CadastroInformacaoFluxoCaixaModal extends JDialog {

	private static CadastroInformacaoFluxoCaixaModal dialog;
	private final JPanel contentPanel = new JPanel();
	private boolean sucessoAlteracao = false;
	private DatePicker datePicker;

	FluxoCaixaController Controller = new FluxoCaixaController();

	private String[] tipoFluxoData = null;

	private JTextField txtValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new CadastroInformacaoFluxoCaixaModal(0);
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
	 * 
	 * @throws ParseException
	 */
	public CadastroInformacaoFluxoCaixaModal(int id) throws ParseException {

		if (id == 0) {
			this.tipoFluxoData = null;
		} else {
			this.tipoFluxoData = Controller.Obter(id);
		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		setResizable(false);
		setTitle("Informa\u00E7\u00F5es Servi\u00E7o");
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
		setBounds(100, 100, 463, 203);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		DatePickerSettings dateSettings = new DatePickerSettings();
		datePicker = new DatePicker(dateSettings);
		if (id > 0) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formato.parse(tipoFluxoData[1]);
			datePicker.setDate(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}
		datePicker.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent arg0) {
				System.out.println(arg0.getSource().getDate());
			}
		});
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 12));
		dateSettings.setDateRangeLimits(LocalDate.now().minusDays(20), LocalDate.now().plusDays(20));
		datePicker.getComponentDateTextField().setEnabled(false);
		datePicker.getComponentDateTextField().setEditable(false);
		datePicker.setBounds(10, 31, 204, 20);
		contentPanel.add(datePicker);

		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblData.setBounds(12, 12, 159, 14);
		contentPanel.add(lblData);

		JLabel lblTipoFluixo = new JLabel("Perfil:");
		lblTipoFluixo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTipoFluixo.setBounds(245, 12, 104, 14);
		contentPanel.add(lblTipoFluixo);

		JComboBox cbxTipoFluxo = new JComboBox(TipoFluxoCaixaEnum.values());
		cbxTipoFluxo.setBounds(245, 32, 192, 22);
		contentPanel.add(cbxTipoFluxo);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblValor.setBounds(10, 65, 300, 14);
		contentPanel.add(lblValor);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(10, 86, 204, 20);
		contentPanel.add(txtValor);

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
						if (datePicker.getDate() == null) {
							JOptionPane.showMessageDialog(null, "Campos não preenchidos!");
							return;
						}
						boolean resultado;

						try {

					        Date date = Date.from(datePicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
							SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
							String dataS = formato.format(date);

							if (id == 0) {

								resultado = Controller.Inserir(dataS,
										cbxTipoFluxo.getSelectedItem().toString(), txtValor.getText(),
										AuthManager.getInstance().getUsuario().getId());
							} else {
								resultado = Controller.Atualizar(id, dataS,
										cbxTipoFluxo.getSelectedItem().toString(), txtValor.getText());
							}

							if (resultado) {
								JOptionPane.showMessageDialog(null, "Registro Salvo com Sucesso!");
								sucessoAlteracao = true;
								close();
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao Salvo o Registro!");
							}

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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

	private static class SampleTimeVetoPolicy implements TimeVetoPolicy {

		/**
		 * isTimeAllowed, Return true if a time should be allowed, or false if a time
		 * should be vetoed.
		 */
		@Override
		public boolean isTimeAllowed(LocalTime time) {
			// Only allow times from 9a to 5p, inclusive.
			return PickerUtilities.isLocalTimeInRange(time, LocalTime.of(9, 00), LocalTime.of(17, 00), true);
		}
	}
}

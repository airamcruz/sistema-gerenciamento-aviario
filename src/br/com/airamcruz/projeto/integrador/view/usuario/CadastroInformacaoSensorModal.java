package br.com.airamcruz.projeto.integrador.view.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import br.com.airamcruz.projeto.integrador.controller.AviarioController;
import br.com.airamcruz.projeto.integrador.controller.FluxoCaixaController;
import br.com.airamcruz.projeto.integrador.controller.SensorController;
import br.com.airamcruz.projeto.integrador.controller.TipoSensorController;
import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.FluxoCaixaModel;
import br.com.airamcruz.projeto.integrador.model.SensorModel;
import br.com.airamcruz.projeto.integrador.model.TipoSensorModel;
import br.com.airamcruz.projeto.integrador.util.AuthManager;
import br.com.airamcruz.projeto.integrador.util.enums.TipoFluxoCaixaEnum;

public class CadastroInformacaoSensorModal extends JDialog {

	private static final long serialVersionUID = 7171037448266548682L;
	
	private static CadastroInformacaoSensorModal dialog;
	private final JPanel contentPanel = new JPanel();
	private boolean sucessoAlteracao = false;
	private DatePicker datePicker;

	SensorController Controller = new SensorController();

	private SensorModel sensorModel = null;
	private JComboBox cbxTipoDoSensor;
	private JTextField txtDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new CadastroInformacaoSensorModal(0, 0);
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
	public CadastroInformacaoSensorModal(int id, int idAviario) {

		if (id == 0) {
			this.sensorModel = null;
		} else {
			this.sensorModel = Controller.Obter(id);
		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		setResizable(false);
		setTitle("Informa\u00E7\u00F5es do Sensor");
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
			datePicker.setDate(sensorModel.getDataInstalacao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}
		datePicker.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent arg0) {
				System.out.println(arg0.getSource().getDate());
			}
		});
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 12));
		// dateSettings.setDateRangeLimits(LocalDate.now().minusDays(20),
		// LocalDate.now().plusDays(20));
		// datePicker.getComponentDateTextField().setEnabled(false);
		//datePicker.getComponentDateTextField().setEditable(false);
		datePicker.setBounds(10, 86, 204, 20);
		contentPanel.add(datePicker);

		JLabel lblData = new JLabel(" Instala\u00E7\u00E3o Data:");
		lblData.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblData.setBounds(12, 67, 159, 14);
		contentPanel.add(lblData);

		JLabel lblTipoDoSensor = new JLabel("Tipo do sensor:");
		lblTipoDoSensor.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTipoDoSensor.setBounds(245, 64, 104, 14);

		cbxTipoDoSensor = new JComboBox();
		cbxTipoDoSensor.setBounds(245, 84, 192, 22);
		carregarComboBox();
		if(id == 0) {
			contentPanel.add(lblTipoDoSensor);
			contentPanel.add(cbxTipoDoSensor);			
		}
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 32, 427, 20);

		if (id > 0)
			txtDescricao.setText(sensorModel.getDescricao());
		
		contentPanel.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDescricao.setBounds(12, 12, 425, 14);
		contentPanel.add(lblDescricao);

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
												
						if (datePicker.getDate() == null || cbxTipoDoSensor.getSelectedItem() == null || txtDescricao.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Campos não preenchidos!");
							return;
						}

						boolean resultado;

						try {

							Date date = Date
									.from(datePicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
							SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
							String dataS = formato.format(date);

							if (id == 0) {
								resultado = Controller.Inserir(txtDescricao.getText(), dataS, idAviario, ((TipoSensorModel)cbxTipoDoSensor.getSelectedItem()).getId());
							} else {
								resultado = Controller.Atualizar(id, txtDescricao.getText(), dataS, idAviario);
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

	private void carregarComboBox() {
		TipoSensorController tipoSensorController = new TipoSensorController();
		for (TipoSensorModel model : tipoSensorController.ObterTodos()) {
			cbxTipoDoSensor.addItem(model);
		}
	}
}

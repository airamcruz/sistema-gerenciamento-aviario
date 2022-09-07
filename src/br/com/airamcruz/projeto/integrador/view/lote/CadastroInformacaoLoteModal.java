package br.com.airamcruz.projeto.integrador.view.lote;

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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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
import br.com.airamcruz.projeto.integrador.controller.LoteController;
import br.com.airamcruz.projeto.integrador.model.AviarioModel;
import br.com.airamcruz.projeto.integrador.model.LoteModel;

public class CadastroInformacaoLoteModal extends JDialog {

	private static final long serialVersionUID = 7171037448266548682L;

	private static CadastroInformacaoLoteModal dialog;
	private final JPanel contentPanel = new JPanel();
	private boolean sucessoAlteracao = false;
	private DatePicker datePicker;
	private JComboBox cbxAviario;

	LoteController Controller = new LoteController();

	private LoteModel loteModel = null;

	private JFormattedTextField txtQuantidadeDeFrangos;
	private JTextField txtDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new CadastroInformacaoLoteModal(0);
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
	public CadastroInformacaoLoteModal(int id) throws ParseException {

		if (id == 0) {
			this.loteModel = null;
		} else {
			this.loteModel = Controller.Obter(id);
		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		setResizable(false);
		setTitle("Informa\u00E7\u00F5es de Lote");
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
			datePicker.setDate(loteModel.getDataCompra().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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
		// datePicker.getComponentDateTextField().setEditable(false);
		datePicker.setBounds(233, 33, 204, 20);
		contentPanel.add(datePicker);

		JLabel lblDataCompra = new JLabel("Data Compra:");
		lblDataCompra.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDataCompra.setBounds(235, 14, 159, 14);
		contentPanel.add(lblDataCompra);

		JLabel lblQuantidadeDeFrangos = new JLabel("Quantidade de frangos:");
		lblQuantidadeDeFrangos.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblQuantidadeDeFrangos.setBounds(12, 75, 202, 14);
		contentPanel.add(lblQuantidadeDeFrangos);

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
		formatter.setCommitsOnValidEdit(true);

		txtQuantidadeDeFrangos = new JFormattedTextField(formatter);

		if (id > 0)
			txtQuantidadeDeFrangos.setValue(loteModel.getQuantidadeFrangos());
		else
			txtQuantidadeDeFrangos.setValue(0);

		txtQuantidadeDeFrangos.setColumns(10);
		txtQuantidadeDeFrangos.setBounds(12, 96, 202, 20);
		contentPanel.add(txtQuantidadeDeFrangos);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDescricao.setBounds(12, 12, 202, 14);
		contentPanel.add(lblDescricao);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(12, 33, 202, 20);
		if (id > 0)
			txtDescricao.setText(loteModel.getDescricao());
		contentPanel.add(txtDescricao);
		txtDescricao.setColumns(10);

		cbxAviario = new JComboBox();
		cbxAviario.setBounds(233, 95, 204, 22);
		carregarComboBox();
		if (id > 0)
			cbxAviario.setSelectedItem(loteModel.getAviarioModel());
		contentPanel.add(cbxAviario);

		JLabel lblAviario = new JLabel("Aviario:");
		lblAviario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAviario.setBounds(235, 76, 202, 14);
		contentPanel.add(lblAviario);

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

						if (datePicker.getDate() == null || txtQuantidadeDeFrangos.getText().isEmpty()
								|| cbxAviario.getSelectedItem() == null || txtDescricao.getText().isEmpty()) {
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

								resultado = Controller.Inserir(txtDescricao.getText(), dataS,
										Integer.parseInt(txtQuantidadeDeFrangos.getValue().toString()),
										((AviarioModel) cbxAviario.getSelectedItem()).getId());
							} else {
								resultado = Controller.Atualizar(id, txtDescricao.getText(), dataS,
										Integer.parseInt(txtQuantidadeDeFrangos.getValue().toString()),
										((AviarioModel) cbxAviario.getSelectedItem()).getId());
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
		AviarioController aviarioController = new AviarioController();
		for (AviarioModel model : aviarioController.ObterPorUsuario()) {
			cbxAviario.addItem(model);
		}
	}
}

package br.com.airamcruz.projeto.integrador.util;

import br.com.airamcruz.projeto.integrador.dao.AviarioDAO;
import br.com.airamcruz.projeto.integrador.dao.BoletimSanitarioDAO;
import br.com.airamcruz.projeto.integrador.dao.FluxoCaixaDAO;
import br.com.airamcruz.projeto.integrador.dao.HistoricoMonitoramentoDAO;
import br.com.airamcruz.projeto.integrador.dao.LoteDAO;
import br.com.airamcruz.projeto.integrador.dao.MortalidadeDAO;
import br.com.airamcruz.projeto.integrador.dao.SensorDAO;
import br.com.airamcruz.projeto.integrador.dao.TipoSensorDAO;
import br.com.airamcruz.projeto.integrador.dao.UsuarioDAO;

public class ManagerDAO {
	
	private static ManagerDAO _instance  = null;
	
	private AviarioDAO aviarioDAO = new AviarioDAO();
	private BoletimSanitarioDAO boletimSanitarioDAO = new BoletimSanitarioDAO();
	private FluxoCaixaDAO fluxoCaixaDAO = new FluxoCaixaDAO();
	private HistoricoMonitoramentoDAO historicoMonitoramentoDAO  = new HistoricoMonitoramentoDAO();
	private LoteDAO loteDAO = new LoteDAO();
	private MortalidadeDAO mortalidadeDAO = new MortalidadeDAO();
	private SensorDAO sensorDAO = new SensorDAO();
	private TipoSensorDAO tipoSensorDAO = new TipoSensorDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public AviarioDAO getAviarioDAO() {
		return aviarioDAO;
	}

	public BoletimSanitarioDAO getBoletimSanitarioDAO() {
		return boletimSanitarioDAO;
	}

	public FluxoCaixaDAO getFluxoCaixaDAO() {
		return fluxoCaixaDAO;
	}

	public HistoricoMonitoramentoDAO getHistoricoMonitoramentoDAO() {
		return historicoMonitoramentoDAO;
	}

	public LoteDAO getLoteDAO() {
		return loteDAO;
	}

	public MortalidadeDAO getMortalidadeDAO() {
		return mortalidadeDAO;
	}

	public SensorDAO getSensorDAO() {
		return sensorDAO;
	}

	public TipoSensorDAO getTipoSensorDAO() {
		return tipoSensorDAO;
	}
	
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public static ManagerDAO getInstance() {
		if(_instance == null) {
			_instance = new ManagerDAO();
		}
		return _instance;
	}
}

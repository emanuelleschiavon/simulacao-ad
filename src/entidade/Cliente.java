package entidade;
import java.math.BigDecimal;

public class Cliente {

	private BigDecimal chegada; //cliente
	private BigDecimal saida; //cliente
	private BigDecimal servico; //cliente
	private BigDecimal pendente; //sistema
	private BigDecimal residual; //sistema
	private BigDecimal tempoSistema; //cliente
	private BigDecimal tempoFila; //cliente
	private BigDecimal periodoOcupado; //sistema
	private int tamanhoFilaChegada = 0;
	
	public BigDecimal getChegada() {
		return chegada;
	}
	public void setChegada(BigDecimal chegada) {
		this.chegada = chegada;
	}
	
	public BigDecimal getSaida() {
		return saida;
	}
	public void setSaida(BigDecimal saida) {
		this.saida = saida;
	}
	
	public BigDecimal getServico() {
		return servico;
	}
	public void setServico(BigDecimal servico) {
		this.servico = servico;
	}

	public int getTamanhoFilaChegada() {
		return tamanhoFilaChegada;
	}
	public void setTamanhoFilaChegada(int tamanhoFilaChegada) {
		this.tamanhoFilaChegada = tamanhoFilaChegada;
	}
	
	public BigDecimal getPendente() {
		return pendente;
	}
	public void setPendente(BigDecimal pendente) {
		this.pendente = pendente;
	}
	
	public BigDecimal getResidual() {
		return residual;
	}
	public void setResidual(BigDecimal residual) {
		this.residual = residual;
	}

	public BigDecimal getTempoSistema() {
		return tempoSistema;
	}
	public void setTempoSistema(BigDecimal tempoSistema) {
		this.tempoSistema = tempoSistema;
	}
	
	public BigDecimal getTempoFila() {
		return tempoFila;
	}
	public void setTempoFila(BigDecimal tempoFila) {
		this.tempoFila = tempoFila;
	}
	
	public BigDecimal getPeriodoOcupado() {
		return periodoOcupado;
	}
	public void setPeriodoOcupado(BigDecimal periodoOcupado) {
		this.periodoOcupado = periodoOcupado;
	}
}

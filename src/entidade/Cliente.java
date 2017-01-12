package entidade;
import java.math.BigDecimal;

public class Cliente {

	private BigDecimal chegada;
	private BigDecimal saida;
	private BigDecimal servico;
	private BigDecimal pendente;
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
	
}

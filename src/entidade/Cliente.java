package entidade;
import java.math.BigDecimal;

public class Cliente {

	private BigDecimal chegada;
	private BigDecimal saida;
	private BigDecimal servico;
	
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
	
}

package lcfs;
import java.math.BigDecimal;
import java.util.List;

import entidade.Cliente;
import entidade.Pilha;

public class ServidorLCFSComPreempcao {
	
	private Cliente servidor;
	
	/**
	 * Guarda o cliente que se encontra no servidor na pilha ou retira do sistema se já tiver terminado o serviço
	 */
	public void desocupaServidor(Cliente clienteChegada, Pilha pilha) {
		if(servidor != null){
			if (servidor.getSaida().compareTo(clienteChegada.getChegada()) == 1){
				pilha.push(servidor);
			}
		}
		servidor = null;
	}
	
	/**
	 * Atualiza os clientes na pilha 
	 */
	public void atualizaServidorAntesChegada(Cliente clienteCorrente, Pilha pilha){
		if(servidor != null){	//servidor ta com o anterior
			if (servidor.getSaida().compareTo(clienteCorrente.getChegada()) == 1){//servidor atualizado
				
			}else{		//servidor ocioso
				servidor = null;}
		}else{
			//pega o ultimo da pilha e coloca no servidor
			for (Cliente c : pilha.getPilhaInvertida()) {
				if (c.getSaida().compareTo(clienteCorrente.getChegada()) == 1){
					servidor = c;
					break;
				}else{
					servidor = null;
				}
			}
		}
	}
	
	/**
	 * Atualiza os clientes na pilha 
	 */
	public void atualizaPilha(Cliente cliente, Pilha pilha){
		for (Cliente c : pilha.getPilhaInvertida()) {
			if(cliente.getChegada().compareTo(c.getSaida()) == 1){
				pilha.pop();
			}
		}
	}
	
	/**
	 * Coloca o cliente no servidor e atualiza as saídas dos clientes no sistema e retira os clientes que saíram da pilha
	 */
	public void entraServidor(Cliente clienteCorrente, Pilha pilha){
		servidor = clienteCorrente;
		atualizaTempos(clienteCorrente, pilha);
	}

	/**
	 * Atualiza saídas dos clientes na pilha
	 */
	private void atualizaTempos(Cliente cliente, Pilha pilha) {
		BigDecimal servicosPilha = BigDecimal.ZERO;
		for (Cliente c : pilha.getPilhaInvertida()){
			servicosPilha = servicosPilha.add(c.getServico());
			c.setSaida(servidor.getSaida().add(servicosPilha));
			c.setTempoSistema(c.getSaida().subtract(c.getChegada()));
			c.setTempoFila(c.getTempoSistema().subtract(c.getServico())); //está somado ao valor interrompido
		}
	}

	/**
	 * Adiciona o serviço residual no momento em que chega o cliente
	 */
	public void setaResidual(Cliente cliente){
		if (servidor != null){
			cliente.setResidual(servidor.getSaida().subtract(cliente.getChegada()));
		}else{
			cliente.setResidual(BigDecimal.ZERO);
		}
	}

	/**
	 * Adiciona o serviço pendente no servidor no momento da chegada do cliente
	 */
	public void setaServicoPendente(Pilha pilha, Cliente cliente) {
		BigDecimal servicosPendentes = BigDecimal.ZERO;
		for (Cliente c : pilha.getPilha()) {
			servicosPendentes = servicosPendentes.add(c.getServico());
		}
		cliente.setPendente(servicosPendentes.add(cliente.getResidual()));
	}
	
	/**
	 * Adiciona o período ocupado do sistema no instante da chegada do cliente
	 */
	public void setaPeriodoOcupado(Cliente cliente, List<Cliente> clientes, int posCliente){
		BigDecimal somaServicos = BigDecimal.ZERO;
		for (int i=0; i<posCliente; i++){
			somaServicos = somaServicos.add(clientes.get(i).getServico());
		}
		cliente.setPeriodoOcupado(somaServicos.subtract(cliente.getResidual()));
	}

	public void pegaPeriodosOcupados(List<Cliente> clientes) {
		BigDecimal periodoOcupado = BigDecimal.ZERO;
		for (Cliente cliente : clientes) {
			periodoOcupado = cliente.getTempoSistema().subtract(cliente.getTempoFila());
			cliente.setPeriodoOcupado(periodoOcupado);
		}
	}
}

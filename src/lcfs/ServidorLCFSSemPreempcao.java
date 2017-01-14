package lcfs;
import java.math.BigDecimal;

import entidade.Cliente;
import entidade.Pilha;

public class ServidorLCFSSemPreempcao {
	
	private Cliente servidor;
	
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
	
	public void tentaAtendimento(Cliente clienteCorrente, Pilha pilha){
		if (servidor == null && pilha.getPilha().isEmpty()){		//sistema vazio
			servidor = clienteCorrente;
		}else if (servidor != null){								//servidor ocupado
			pilha.push(clienteCorrente);
		}else{														//servidor recebe topo da pilha
			servidor = pilha.pop();
			pilha.push(clienteCorrente);
		}
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
}

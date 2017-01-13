package lcfs;
import java.math.BigDecimal;
import java.util.List;

import entidade.Cliente;
import entidade.Pilha;

public class ServidorLCFSComPreempcao {
	
	private Cliente servidor;
	
	public void tentaDesocuparServidor(Cliente clienteChegada, Pilha pilha) {
		if(servidor != null){
			if (servidor.getSaida().compareTo(clienteChegada.getChegada()) == 1){
				pilha.push(servidor);
			}
		}
		servidor = null;
	}
	
	public void tentaAtendimento(Cliente clienteCorrente, Pilha pilha){
		servidor = clienteCorrente;
		atualizaSaidas(clienteCorrente, pilha);
	}

	private void atualizaSaidas(Cliente cliente, Pilha pilha) {
		BigDecimal servicosPilha = BigDecimal.ZERO;
		for (Cliente clientePilha : pilha.getPilhaInvertida()){
			servicosPilha = servicosPilha.add(clientePilha.getServico());
			clientePilha.setSaida(servidor.getSaida().add(servicosPilha));
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
}

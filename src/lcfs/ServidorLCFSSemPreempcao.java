package lcfs.sempreempcao;
import java.math.BigDecimal;

import entidade.Cliente;
import entidade.Pilha;

public class ServidorLCFSSemPreempcao {
	
	private Cliente servidor;
	
	public void tentaDesocuparServidor(Cliente clienteChegada) {
		if (servidor != null && servidor.getSaida().compareTo(clienteChegada.getChegada()) != 1){
			servidor = null;
		}
	}
	
	public BigDecimal tentaAtendimento(Cliente clienteCorrente, Pilha pilha){
		BigDecimal residual = BigDecimal.ZERO;
		if (servidor == null && pilha.getPilha().isEmpty()){		//sistema vazio
			servidor = clienteCorrente;
		}else if (servidor != null){								//servidor ocupado
			residual = (servidor.getSaida().subtract(clienteCorrente.getChegada()));
			pilha.push(clienteCorrente);
			atualizaSaidas(clienteCorrente, pilha);
		}else{														//servidor recebe topo da pilha
			servidor = pilha.pop();
			pilha.push(clienteCorrente);
			atualizaSaidas(clienteCorrente, pilha);
		}
		return residual;
	}

	private void atualizaSaidas(Cliente cliente, Pilha pilha) {
		BigDecimal servicosPilha = BigDecimal.ZERO;
		for (Cliente clientePilha : pilha.getPilhaInvertida()){
			servicosPilha = servicosPilha.add(clientePilha.getServico());
			clientePilha.setSaida(servidor.getSaida().add(servicosPilha));
		}
	}
	
	public BigDecimal pegaTrabalhoPendenteSemResidual(Pilha pilha) {
		BigDecimal trabalhoPendente = BigDecimal.ZERO;
		for (Cliente c : pilha.getPilhaInvertida()){
			trabalhoPendente = trabalhoPendente.add(c.getServico());
		}
		return trabalhoPendente;
	}
}

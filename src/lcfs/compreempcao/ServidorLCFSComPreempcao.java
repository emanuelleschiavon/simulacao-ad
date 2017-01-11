package lcfs.compreempcao;
import java.math.BigDecimal;

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
	
	public void tentaAtendimento(Cliente clienteCorrente, Pilha pilha) throws Exception{
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
	
}

package fcfs;

import java.math.BigDecimal;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;
import entidade.Pilha;

public class ServidorFCFSComPreempcao2Classes {
	private Cliente servidor;
	
	public Cliente verificaClienteAtendidoAntes(Cliente clienteFila1, Cliente clienteFila2){
		if (servidor == null) {return null;}
		//se saida do cliente da fila 2 nao for mais 
		if ((servidor.getSaida().add(clienteFila2.getServico())).compareTo(clienteFila1.getChegada()) != 1){
			return clienteFila2;
		}
		return clienteFila1;
	}
	
	public void tentaDesocuparServidor(Cliente clienteChegada) {
		if (servidor != null && servidor.getSaida().compareTo(clienteChegada.getChegada()) != 1){
			servidor = null;
		}
	}
	
	/**
	 * V� quem est� no servidor no momento da chegada do cliente , se estiver vazio fica nulo
	 */
	public void atualizaServidor(Cliente cliente, List<Cliente> clientes, int posCliente) {
		for (int i=0; i<posCliente; i++){
			if (clientes.get(i).getSaida().compareTo(cliente.getChegada()) == 1){
				servidor = clientes.get(i);
				break;
			}else{
				servidor = null;
			}
		}
	}
	
	/**
	 * Atualiza os clientes na pilha 
	 */
	public void atualizaPilha(Cliente cliente, Pilha pilha){
		int auxRemocao = 0;
		for (Cliente c : pilha.getPilhaInvertida()) {
			if(cliente.getChegada().compareTo(c.getSaida()) == 1){
				auxRemocao++;
			}
		}
		for (int i = 0; i < auxRemocao; i++) {
			pilha.pop();
		}
	}
	
	public BigDecimal tentaAtendimento(Cliente clienteCorrente, Fila fila, List<Cliente> clientes, int indiceCliente){
		BigDecimal residual = BigDecimal.ZERO;
		if (servidor == null){		//sistema vazio
			servidor = clienteCorrente;
		}else {							//servidor ocupado
			Cliente clienteAnterior = clientes.get(indiceCliente-1);
			residual = (servidor.getSaida().subtract(clienteCorrente.getChegada()));
			fila.adicionaFila(clienteCorrente);
			clienteCorrente.setTamanhoFilaChegada(fila.getFilaClientes().size()); //guarda lugar na fila para impressao
			servicosFila(fila, clienteCorrente, residual, clienteAnterior);
			servidor = fila.getFilaClientes().get(0);
		}
		return residual;
	}
	
	// TODO Sa�da vai ser ou a taxa de entrada ou a taxa de sa�da de quem saiu
	private void servicosFila(Fila fila, Cliente clienteCorrente, BigDecimal residual, Cliente clienteAnterior){
		BigDecimal somaServicos = residual;
		somaServicos = clienteCorrente.getServico().add(clienteAnterior.getSaida());
		clienteCorrente.setSaida(somaServicos);
	}

	public BigDecimal pegaTrabalhoPendenteSemResidual(Fila fila) {
		BigDecimal trabalhoPendente = BigDecimal.ZERO;
		for (Cliente c : fila.getFilaClientes()){
			trabalhoPendente = trabalhoPendente.add(c.getServico());
		}
		return trabalhoPendente;
	}
}

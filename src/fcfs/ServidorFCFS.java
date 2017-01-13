package fcfs;

import java.math.BigDecimal;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;

public class ServidorFCFS {
	private Cliente servidor;
	
	/**
	 * A sa�da do cliente corrente vai ser a taxa de entrada ou a sa�da do anterior + servi�o
	 */
	public void calculaSaidaCorrente(Cliente clienteCorrente, Cliente clienteAnterior){
		BigDecimal saidaSemEspera = clienteCorrente.getChegada().add(clienteCorrente.getServico());
		if (clienteAnterior == null){
			clienteCorrente.setSaida(saidaSemEspera);
		}else{
			BigDecimal saidaComEspera = clienteAnterior.getSaida().add(clienteCorrente.getServico());
			if (saidaSemEspera.compareTo(saidaComEspera) == 1){
				clienteCorrente.setSaida(saidaSemEspera);
			}else{
				clienteCorrente.setSaida(saidaComEspera);
			}
		}
	}
	

	/**
	 * Retorna o en�simo cliente, n = @param posCliente
	 */
	public Cliente pegaClienteN(List<Cliente> clientes, int posCliente) {
		if (posCliente == clientes.size()+1 || posCliente == -1){
			return null;
		}
		return clientes.get(posCliente);
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
	 * Retorna a fila de clientes no instante de chegada do @param cliente
	 */
	public Fila atualizaFila(Cliente cliente, List<Cliente> clientes, Fila fila, int posCliente){
		fila.getFilaClientes().clear();
		for (int i=0; i<posCliente; i++){
			if (clientes.get(i).getSaida().compareTo(cliente.getChegada()) == 1 && !servidor.equals(clientes.get(i))){
				fila.adicionaFila(clientes.get(i));
			}
		}
		return fila;
	}
	
	/**
	 * Retorna o servi�o residual no momento em que chega o cliente
	 */
	public void setaResidual(Cliente cliente){
		if (servidor != null){
			cliente.setResidual(servidor.getSaida().subtract(cliente.getChegada()));
		}else{
			cliente.setResidual(BigDecimal.ZERO);
		}
		
	}


	public void setaServicoPendente(Fila fila, Cliente cliente) {
		BigDecimal servicosPendentes = BigDecimal.ZERO;
		for (Cliente c : fila.getFilaClientes()) {
			servicosPendentes = servicosPendentes.add(c.getServico());
		}
		cliente.setPendente(servicosPendentes.add(cliente.getResidual()));
		
	}
	
}

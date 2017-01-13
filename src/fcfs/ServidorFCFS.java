package fcfs;

import java.math.BigDecimal;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;

public class ServidorFCFS {
	private Cliente servidor;
	private int posServidor = 0;
	
	/**
	 * A saída do cliente corrente vai ser a taxa de entrada ou a saída do anterior + serviço
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
	 * Retorna o enésimo cliente, n = @param posCliente
	 */
	public Cliente pegaClienteN(List<Cliente> clientes, int posCliente) {
		if (posCliente == clientes.size()+1 || posCliente == -1){
			return null;
		}
		return clientes.get(posCliente);
	}

	/**
	 * Vê quem está no servidor no momento da chegada do cliente , se estiver vazio fica nulo
	 */
	public void atualizaServidor(Cliente cliente, List<Cliente> clientes, int posCliente) {
		for (int i=0; i<posCliente; i++){
			if (clientes.get(i).getSaida().compareTo(cliente.getChegada()) == 1){
				servidor = clientes.get(i);
				posServidor = i;
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
	public void setaServicoPendente(Fila fila, Cliente cliente) {
		BigDecimal servicosPendentes = BigDecimal.ZERO;
		for (Cliente c : fila.getFilaClientes()) {
			servicosPendentes = servicosPendentes.add(c.getServico());
		}
		cliente.setPendente(servicosPendentes.add(cliente.getResidual()));
		
	}
	
	/**
	 * Adiciona o período ocupado do sistema no instante da chegada do cliente
	 */
	public void setaPeriodoOcupado(Cliente cliente, List<Cliente> clientes, int posCliente){
		BigDecimal somaServicos = BigDecimal.ZERO;
		if (servidor != null){
			for (int i=0; i<posServidor; i++){
				somaServicos = somaServicos.add(clientes.get(i).getServico());
			}
			cliente.setPeriodoOcupado(somaServicos.subtract(servidor.getResidual()));
		}else{
			for (int i=0; i<posCliente; i++){
				somaServicos = somaServicos.add(clientes.get(i).getServico());
			}
			cliente.setPeriodoOcupado(somaServicos);
		}
	}
}

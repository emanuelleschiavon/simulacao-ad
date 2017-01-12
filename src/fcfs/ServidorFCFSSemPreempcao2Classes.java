package fcfs;

import java.math.BigDecimal;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;

public class ServidorFCFSSemPreempcao2Classes {
	
	private Cliente servidor;
	
	public Cliente verificaClienteAtendidoAntes(Cliente clienteFila1, Cliente clienteFila2){
		if ((clienteFila2.getChegada().add(clienteFila2.getServico())).compareTo(clienteFila1.getChegada()) != 1){
			return clienteFila1;
		}
		return clienteFila2;
	}
	
	public void tentaDesocuparServidor(Cliente clienteChegada) {
		Cliente clienteExpulso = new Cliente();
		if (servidor != null && servidor.getSaida().compareTo(clienteChegada.getChegada()) != 1){
			servidor = null;
		}
	}
	
	public void tentaAtendimento(Cliente clienteCorrente, Fila fila1, Fila fila2, List<Cliente> clientes, int indiceCliente){
		BigDecimal residual = BigDecimal.ZERO;
		if (servidor == null){		//sistema vazio
			servidor = clienteCorrente;
		}else {							//servidor ocupado
			Cliente clienteAnterior = clientes.get(indiceCliente-1);
			residual = (servidor.getSaida().subtract(clienteCorrente.getChegada()));
			fila1.adicionaFila(clienteCorrente);
			servicosFila(fila1, clienteCorrente, residual, clienteAnterior);
			servidor = fila1.getFilaClientes().get(0);
		}
	}
	
	// TODO Saída vai ser ou a taxa de entrada ou a taxa de saída de quem saiu
	private void servicosFila(Fila fila, Cliente clienteCorrente, BigDecimal residual, Cliente clienteAnterior){
		BigDecimal somaServicos = residual;
		somaServicos = clienteCorrente.getServico().add(clienteAnterior.getSaida());
		clienteCorrente.setSaida(somaServicos);
	}


}

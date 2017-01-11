package fcfs;

import java.math.BigDecimal;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;

public class ServidorFCFS {
	private Cliente servidor;
	
	public void tentaDesocuparServidor(Cliente clienteChegada) {
		if (servidor != null && servidor.getSaida().compareTo(clienteChegada.getChegada()) != 1){
			servidor = null;
		}
	}
	
	public void tentaAtendimento(Cliente clienteCorrente, Fila fila, List<Cliente> clientes, int indiceCliente) throws Exception{
		BigDecimal residual = BigDecimal.ZERO;
		if (servidor == null){		//sistema vazio
			servidor = clienteCorrente;
		}else {							//servidor ocupado
			Cliente clienteAnterior = clientes.get(indiceCliente-1);
			residual = (servidor.getSaida().subtract(clienteCorrente.getChegada()));
			fila.adicionaFila(clienteCorrente);
			servicosFila(fila, clienteCorrente, residual, clienteAnterior);
			servidor = fila.getFilaClientes().get(0);
		}
	}
	
	// TODO Saída vai ser ou a taxa de entrada ou a taxa de saída de quem saiu
	private void servicosFila(Fila fila, Cliente clienteCorrente, BigDecimal residual, Cliente clienteAnterior){
		BigDecimal somaServicos = residual;
		somaServicos = clienteCorrente.getServico().add(clienteAnterior.getSaida());
		clienteCorrente.setSaida(somaServicos);
	}
}

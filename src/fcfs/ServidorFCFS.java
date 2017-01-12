package fcfs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;

public class ServidorFCFS {
	private Cliente servidor;
	
	public void tentaDesocuparServidor(Cliente clienteChegada, Fila fila) {
		List<Cliente> auxRemove = new ArrayList<>();
		for (Cliente c : fila.getFilaClientes()) {
			if(servidor.getSaida().compareTo(c.getChegada()) != 1){
				auxRemove.add(c);
			}
		}
		fila.getFilaClientes().removeAll(auxRemove);
		
		if (servidor != null && servidor.getSaida().compareTo(clienteChegada.getChegada()) != 1){
			servidor = null;
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
	
	// TODO Saída vai ser ou a taxa de entrada ou a taxa de saída de quem saiu
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

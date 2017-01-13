package fcfs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;

public class ServidorFCFS {
	private Cliente servidor;
	
	public void tentaDesocuparServidor(Cliente clienteChegada, Fila fila, int indiceCliente, List<Cliente> clientes) {
		List<Cliente> auxRemove = new ArrayList<>();
		for (Cliente c : fila.getFilaClientes()) {
			if(clienteChegada.getChegada().compareTo(c.getChegada()) != 1){
				auxRemove.add(c);
			}
		}
		fila.getFilaClientes().removeAll(auxRemove);
		if (servidor != null && clientes.get(indiceCliente-1).getSaida().compareTo(clienteChegada.getChegada()) != 1){
			servidor = null;
		}else if (servidor != null && servidor.getSaida().compareTo(clienteChegada.getChegada()) == 1){
			servidor = clientes.get(indiceCliente-1);
		}
	}
	
	public BigDecimal tentaAtendimento(Cliente clienteCorrente, Fila fila, List<Cliente> clientes, int indiceCliente){
		BigDecimal residual = BigDecimal.ZERO;
		if (servidor == null){		//sistema vazio
			servidor = clienteCorrente;
		}else if (servidor != null && !fila.getFilaClientes().isEmpty()){							//servidor ocupado
			clienteCorrente.setTamanhoFilaChegada(fila.getFilaClientes().size()); //guarda lugar na fila para impressao
			residual = (servidor.getSaida().subtract(clienteCorrente.getChegada()));
			Cliente clienteAnterior = clientes.get(indiceCliente-1);
			calculaSaidaCorrente(clienteCorrente, clienteAnterior);
			servidor = fila.getFilaClientes().get(0);
		}else{
			clienteCorrente.setTamanhoFilaChegada(fila.getFilaClientes().size()); //guarda lugar na fila para impressao
			residual = (servidor.getSaida().subtract(clienteCorrente.getChegada()));
			fila.adicionaFila(clienteCorrente);
			Cliente clienteAnterior = clientes.get(indiceCliente-1);
			calculaSaidaCorrente(clienteCorrente, clienteAnterior);
		}
		return residual;
	}
	
	// TODO Saída vai ser ou a taxa de entrada ou a taxa de saída de quem saiu
	private void calculaSaidaCorrente(Cliente clienteCorrente, Cliente clienteAnterior){
		BigDecimal saidaSemEspera = clienteCorrente.getChegada().add(clienteCorrente.getServico());
		BigDecimal saidaComEspera = clienteAnterior.getSaida().add(clienteCorrente.getServico());
		if (saidaSemEspera.compareTo(saidaComEspera) == 1){
			clienteCorrente.setSaida(saidaSemEspera);
		}else{
			clienteCorrente.setSaida(saidaComEspera);
		}
	}

	public BigDecimal pegaTrabalhoPendenteSemResidual(Fila fila) {
		BigDecimal trabalhoPendente = BigDecimal.ZERO;
		for (Cliente c : fila.getFilaClientes()){
			trabalhoPendente = trabalhoPendente.add(c.getServico());
		}
		return trabalhoPendente;
	}
	
}

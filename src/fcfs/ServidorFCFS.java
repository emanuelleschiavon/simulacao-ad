package fcfs;

import java.math.BigDecimal;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;

public class ServidorFCFS {
	private Cliente servidor;
	
	public void tentaDesocuparServidor(Cliente clienteChegada, Fila fila, int indiceCliente, List<Cliente> clientes) {
		if (servidor != null && clientes.get(indiceCliente-1).getSaida().compareTo(clienteChegada.getChegada()) != 1){
			servidor = null;
		}else if (servidor != null && servidor.getSaida().compareTo(clienteChegada.getChegada()) == 1){
			servidor = clientes.get(indiceCliente-1);
		}
	}
	
	public BigDecimal tentaAtendimento(Cliente clienteCorrente, Fila fila, List<Cliente> clientes, int indiceCliente){
		BigDecimal residual = BigDecimal.ZERO;
		Cliente clienteAnterior = new Cliente();
		if (indiceCliente > 0){
			clienteAnterior = clientes.get(indiceCliente-1);
		}else{
			clienteAnterior=null;
		}
		if ( indiceCliente == 0 || clienteAnterior.getSaida().compareTo(clienteCorrente.getChegada()) != 1){		//sistema vazio
			servidor = clienteCorrente;
			if(indiceCliente>0){
				calculaSaidaCorrente(clienteCorrente, clienteAnterior);
			}else{
				clienteCorrente.setSaida(clienteCorrente.getChegada().add(clienteCorrente.getServico()));
			}
		}else if (servidor != null && !fila.getFilaClientes().isEmpty()){							//servidor ocupado
			clienteCorrente.setTamanhoFilaChegada(fila.getFilaClientes().size()); //guarda lugar na fila para impressao
			residual = (clienteAnterior.getSaida().subtract(clienteCorrente.getChegada())).doubleValue() > 0 ? (servidor.getSaida().subtract(clienteCorrente.getChegada())) : BigDecimal.ZERO ;
			calculaSaidaCorrente(clienteCorrente, clienteAnterior);
			servidor = fila.getFilaClientes().get(0);
		}else{
			clienteCorrente.setTamanhoFilaChegada(fila.getFilaClientes().size()); //guarda lugar na fila para impressao
			residual = (clienteAnterior.getSaida().subtract(clienteCorrente.getChegada())).doubleValue() > 0 ? (clienteAnterior.getSaida().subtract(clienteCorrente.getChegada())) : BigDecimal.ZERO ;
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
	
	public Fila tamanhoFila2(Cliente clienteCorrente, List<Cliente> clientes, int indiceClienteCorrente){
		Fila fila = new Fila();
		BigDecimal servicoPendente = BigDecimal.ZERO;
		fila.getFilaClientes().clear();
		for (int i=0; i<indiceClienteCorrente; i++){
			if (clientes.get(i).getSaida().compareTo(clienteCorrente.getChegada()) == 1){
				fila.adicionaFila(clientes.get(i));
				servicoPendente = servicoPendente.add(clientes.get(i).getServico());
			}
		}
		clienteCorrente.setPendente(servicoPendente);
		return fila;
	}
	
	public Cliente pegaServidor (Cliente clienteCorrente, List<Cliente> clientes, int indice){
		Fila fila = new Fila();
		fila = tamanhoFila2(clienteCorrente, clientes, indice);
		if (!fila.getFilaClientes().isEmpty()){
			servidor = fila.getFilaClientes().get(0);
		}
		return servidor;
	}
	
}

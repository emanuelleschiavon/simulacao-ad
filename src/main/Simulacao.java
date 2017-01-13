package main;
import java.math.BigDecimal;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;
import entidade.Pilha;
import fcfs.ServidorFCFS;
import fcfs.ServidorFCFSComPreempcao2Classes;
import lcfs.ServidorLCFSComPreempcao;
import lcfs.ServidorLCFSSemPreempcao;
import retorno.Impressao;

public class Simulacao {

	private List<Cliente> clientes;
	private List<Cliente> clientes2;
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public List<Cliente> getClientes2() {
		return clientes2;
	}
	public void setClientes2(List<Cliente> clientes2) {
		this.clientes2 = clientes2;
	}

	public void executaSimulacao(){
//		Pilha pilha = new Pilha();
		Fila fila1 = new Fila();
//		Fila fila2 = new Fila();
		fcfs(fila1);
//		lcfsComPreempcao(pilha);
//		lcfsSemPreempcao(pilha);
//		fcfsComPreempcao2Filas(fila1, fila2);
	}
	
	public void lcfsSemPreempcao(Pilha pilha){
		ServidorLCFSSemPreempcao servidor = new ServidorLCFSSemPreempcao();
		for (Cliente cliente : clientes) {
			cliente.setSaida(cliente.getChegada().add(cliente.getServico()));
			cliente.setPendente(servidor.pegaTrabalhoPendenteSemResidual(pilha));
			servidor.tentaDesocuparServidor(cliente);
			BigDecimal residual = servidor.tentaAtendimento(cliente, pilha);
			cliente.setPendente(cliente.getPendente().add(residual));
		}
		Impressao.imprimeSaida(clientes, "lcfs sem preempcao");
	}
	
	public void lcfsComPreempcao(Pilha pilha){
		ServidorLCFSComPreempcao servidor = new ServidorLCFSComPreempcao();
		for (Cliente cliente : clientes) {
			cliente.setSaida(cliente.getChegada().add(cliente.getServico()));
			servidor.tentaDesocuparServidor(cliente, pilha);
			cliente.setPendente(servidor.pegaTrabalhoPendenteSemResidual(pilha));
			servidor.tentaAtendimento(cliente, pilha);
		}
		Impressao.imprimeSaida(clientes, "lcfs com preempcao");
	}
	
	public void fcfs(Fila fila){
		ServidorFCFS servidor = new ServidorFCFS();
		int posCliente = -1;

		for (Cliente cliente : clientes) {posCliente++;
			servidor.atualizaServidor(cliente, clientes, posCliente);
			fila = servidor.atualizaFila(cliente, clientes, fila, posCliente);
		
			Cliente clienteAnterior = servidor.pegaClienteN(clientes, posCliente-1);
			servidor.calculaSaidaCorrente(cliente, clienteAnterior);

			cliente.setTamanhoFilaChegada(fila.getFilaClientes().size());
			cliente.setTempoSistema(cliente.getSaida().subtract(cliente.getChegada()));
			cliente.setTempoFila(cliente.getTempoSistema().subtract(cliente.getServico()));
			
			servidor.setaResidual(cliente);
			servidor.setaServicoPendente(fila, cliente);
			servidor.setaPeriodoOcupado(cliente, clientes, posCliente);
		}
		Impressao.imprimeSaida(clientes, "fcfs sem preempcao");
	}
	
	public void fcfsComPreempcao2Filas(Fila fila1, Fila fila2){
		ServidorFCFSComPreempcao2Classes servidor = new ServidorFCFSComPreempcao2Classes();
		ServidorFCFS servidorfcfs = new ServidorFCFS();
		
		int indiceCliente = -1;
		int indiceFila2 = 0;
		
		for (Cliente clienteFila1 : clientes) {
			indiceCliente++;
			clienteFila1.setSaida(clienteFila1.getChegada().add(clienteFila1.getServico()));
			
			Cliente clientePrioridade = new Cliente();
			if (clientes2.get(0).equals(servidor.verificaClienteAtendidoAntes(clienteFila1, clientes2.get(indiceFila2)))){
				fila2.adicionaFila(clientes2.get(0));
				clientePrioridade = clientes2.get(0);
			}else{
				clientePrioridade = clienteFila1;
			}
//			if(indiceCliente+1 == clientes.size())
			int posCliente2 = 0;
			for (Cliente clienteFila2 : fila2.getFilaClientes()) {
				posCliente2 ++;
				if (clienteFila2.equals(clientePrioridade)){
					servidorfcfs.atualizaServidor(clienteFila2, clientes, posCliente2);
					fila2 = servidorfcfs.atualizaFila(clienteFila2, clientes, fila2, posCliente2);
				
					Cliente clienteAnterior = servidorfcfs.pegaClienteN(clientes, posCliente2-1);

					clienteFila2.setTamanhoFilaChegada(fila2.getFilaClientes().size());

					servidorfcfs.setaResidual(clienteFila2);
					servidorfcfs.setaServicoPendente(fila2, clienteFila2);
					
					servidorfcfs.calculaSaidaCorrente(clienteFila2, clienteAnterior);
					
				}else{
					break;
				}
			}
			servidor.tentaDesocuparServidor(clienteFila1);
			servidor.tentaAtendimento(clienteFila1, fila1, clientes, indiceCliente);
		}
		Impressao.imprimeSaida(clientes, clientes2);
	}
}

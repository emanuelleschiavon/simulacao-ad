package main;
import java.util.List;

import entidade.Cliente;
import entidade.Fila;
import entidade.Pilha;
import fcfs.ServidorFCFS;
import fcfs.ServidorFCFSComPreempcao2Classes;
import lcfs.compreempcao.ServidorLCFSComPreempcao;
import lcfs.sempreempcao.ServidorLCFSSemPreempcao;
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

	public void executaSimulacao() throws Exception{
		Pilha pilha = new Pilha();
		Fila fila = new Fila();
		fcfs(fila);
		lcfsComPreempcao(pilha);
		lcfsSemPreempcao(pilha);
	}
	
	private void lcfsSemPreempcao(Pilha pilha) throws Exception{
		ServidorLCFSSemPreempcao servidor = new ServidorLCFSSemPreempcao();
		for (Cliente cliente : clientes) {
			cliente.setSaida(cliente.getChegada().add(cliente.getServico()));
			servidor.tentaDesocuparServidor(cliente);
			servidor.tentaAtendimento(cliente, pilha);
		}
		Impressao.imprimeSaida(clientes);
	}
	
	private void lcfsComPreempcao(Pilha pilha) throws Exception{
		ServidorLCFSComPreempcao servidor = new ServidorLCFSComPreempcao();
		for (Cliente cliente : clientes) {
			cliente.setSaida(cliente.getChegada().add(cliente.getServico()));
			servidor.tentaDesocuparServidor(cliente, pilha);
			servidor.tentaAtendimento(cliente, pilha);
		}
		Impressao.imprimeSaida(clientes);
	}
	
	private void fcfs(Fila fila) throws Exception{
		ServidorFCFS servidor = new ServidorFCFS();
		int indiceCliente = -1;

		for (Cliente cliente : clientes) {indiceCliente++;
			cliente.setSaida(cliente.getChegada().add(cliente.getServico()));
			servidor.tentaDesocuparServidor(cliente);
			servidor.tentaAtendimento(cliente, fila, clientes, indiceCliente);
		}
		Impressao.imprimeSaida(clientes);
	}
	
	private void fcfsComPreempcao2Filas(Fila fila1, Fila fila2) throws Exception{
		ServidorFCFSComPreempcao2Classes servidor = new ServidorFCFSComPreempcao2Classes();
		int indiceCliente = -1;
		
		for (Cliente clienteFila1 : clientes) {indiceCliente++;
			clienteFila1.setSaida(clienteFila1.getChegada().add(clienteFila1.getServico()));
			Cliente clienteFila2 = clientes2.get(0);
			Cliente clientePrioridade = servidor.verificaClienteAtendidoAntes(clienteFila1, clienteFila2);
			servidor.tentaDesocuparServidor(clientePrioridade);
			servidor.tentaAtendimento(clienteFila1, fila1, fila2, clientes, indiceCliente);
		}
		Impressao.imprimeSaida(clientes, clientes2);
	}
	
	private void imprimeSaida(){
		for (Cliente cliente : clientes) {
			System.out.println("Chegada " + cliente.getChegada().toString());
			System.out.println("Servi�o " + cliente.getServico().toString());
			System.out.println("Sa�da " + cliente.getSaida().toString() + "\n");
		}
	}

}

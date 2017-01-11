package main;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import entidade.Cliente;

public class MainTest {

	@Test
	public void simulacaoLCFSSemPreempcao() throws Exception {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente = new Cliente();
		cliente.setChegada(BigDecimal.ONE);
		cliente.setServico(BigDecimal.valueOf(2));
		
		Cliente cliente2 = new Cliente();
		cliente2.setChegada(BigDecimal.valueOf(3));
		cliente2.setServico(BigDecimal.ONE);
		
		Cliente cliente3 = new Cliente();
		cliente3.setChegada(BigDecimal.valueOf(6));
		cliente3.setServico(BigDecimal.valueOf(2));
		
		Cliente cliente4 = new Cliente();
		cliente4.setChegada(BigDecimal.valueOf(7));
		cliente4.setServico(BigDecimal.valueOf(3));
	
		Cliente cliente5 = new Cliente();
		cliente5.setChegada(BigDecimal.valueOf(9));
		cliente5.setServico(BigDecimal.ONE);
		
		clientes.add(cliente);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);
		
		Simulacao simulacao = new Simulacao();
		simulacao.setClientes(clientes);
		simulacao.executaSimulacao();
	}
	
	@Test
	public void simulacaoLCFSSemPreempcao2() throws Exception {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente = new Cliente();
		cliente.setChegada(BigDecimal.ONE);
		cliente.setServico(BigDecimal.valueOf(2));
		
		Cliente cliente2 = new Cliente();
		cliente2.setChegada(BigDecimal.valueOf(2));
		cliente2.setServico(BigDecimal.valueOf(2));
		
		Cliente cliente3 = new Cliente();
		cliente3.setChegada(BigDecimal.valueOf(3));
		cliente3.setServico(BigDecimal.valueOf(2));
		
		Cliente cliente4 = new Cliente();
		cliente4.setChegada(BigDecimal.valueOf(4));
		cliente4.setServico(BigDecimal.valueOf(2));
	
		Cliente cliente5 = new Cliente();
		cliente5.setChegada(BigDecimal.valueOf(5));
		cliente5.setServico(BigDecimal.valueOf(2));
		
		clientes.add(cliente);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);
		
		Simulacao simulacao = new Simulacao();
		simulacao.setClientes(clientes);
		simulacao.executaSimulacao();
	}
	
	@Test
	public void simulacaoLCFSComPreempcao() throws Exception {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente = new Cliente();
		cliente.setChegada(BigDecimal.ONE);
		cliente.setServico(BigDecimal.valueOf(2));
		
		Cliente cliente2 = new Cliente();
		cliente2.setChegada(BigDecimal.valueOf(3));
		cliente2.setServico(BigDecimal.ONE);
		
		Cliente cliente3 = new Cliente();
		cliente3.setChegada(BigDecimal.valueOf(6));
		cliente3.setServico(BigDecimal.valueOf(2));
		
		Cliente cliente4 = new Cliente();
		cliente4.setChegada(BigDecimal.valueOf(7));
		cliente4.setServico(BigDecimal.valueOf(3));
	
		Cliente cliente5 = new Cliente();
		cliente5.setChegada(BigDecimal.valueOf(9));
		cliente5.setServico(BigDecimal.ONE);
		
		clientes.add(cliente);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);
		
		Simulacao simulacao = new Simulacao();
		simulacao.setClientes(clientes);
		simulacao.executaSimulacao();
	}
	
	@Test
	public void simulacaoFCFS() throws Exception {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente = new Cliente();
		cliente.setChegada(BigDecimal.ONE);
		cliente.setServico(BigDecimal.valueOf(2));
		
		Cliente cliente2 = new Cliente();
		cliente2.setChegada(BigDecimal.valueOf(3));
		cliente2.setServico(BigDecimal.ONE);
		
		Cliente cliente3 = new Cliente();
		cliente3.setChegada(BigDecimal.valueOf(6));
		cliente3.setServico(BigDecimal.valueOf(2));
		
		Cliente cliente4 = new Cliente();
		cliente4.setChegada(BigDecimal.valueOf(7));
		cliente4.setServico(BigDecimal.valueOf(3));
	
		Cliente cliente5 = new Cliente();
		cliente5.setChegada(BigDecimal.valueOf(9));
		cliente5.setServico(BigDecimal.ONE);
		
		clientes.add(cliente);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);
		
		Simulacao simulacao = new Simulacao();
		simulacao.setClientes(clientes);
		simulacao.executaSimulacao();
	}
	
	@SuppressWarnings("unused")
	private void geraChegadas(List<Cliente> clientes){
		Random gerador = new Random();
		for (Cliente cliente : clientes) {
			cliente.setChegada(BigDecimal.valueOf(gerador.nextDouble()));
		}
	}
}

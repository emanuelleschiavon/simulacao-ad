package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import entidade.Cliente;

public class PrincipalTest {
	
	private static final double LAMBDA = 0.9; // taxa chegada
	private static final double MU = 1; // taxa servico
	private static final int N = 1000;  // total clientes
	
	@Test
	public void simulacaoTest() throws Exception{
		Simulacao sim = new Simulacao();
		List<Cliente> clientes = geraClientes();
		sim.setClientes(clientes);
		sim.executaSimulacao();
	}
	
	private List<BigDecimal> geraTemposServico(){
		List<BigDecimal> u = geravaloresUniformes();
		List<BigDecimal> taxasServico = new ArrayList<>();

		for(BigDecimal b : u){
			BigDecimal ts =  BigDecimal.valueOf(-1/MU*Math.log(b.doubleValue()));
			taxasServico.add(ts);
		}
		
		return taxasServico;
		
	}
	
	private List<BigDecimal> geraTemposEntreChegadas(){
		
		List<BigDecimal> u = geravaloresUniformes();
		List<BigDecimal> temposEntreChegadas = new ArrayList<>();
		
		for(BigDecimal b : u){
			BigDecimal ts =  BigDecimal.valueOf(-1/LAMBDA*Math.log(b.doubleValue()));
			temposEntreChegadas.add(ts);
		}
		
		return temposEntreChegadas;	
	}
		
	private List<BigDecimal> geraTemposChegada(){
		
		List<BigDecimal> tempoEntreChegadas = geraTemposEntreChegadas();
		
		List<BigDecimal> temposChegada = new ArrayList<>();
		
		BigDecimal tc = BigDecimal.ZERO;
		
		for (BigDecimal tec : tempoEntreChegadas) {
			tc = tc.add(tec);
			temposChegada.add(tc);
		}
		return temposChegada;
		
	}
	
	private List<Cliente> geraClientes(){
		
		List<BigDecimal> temposServico =  geraTemposServico();
		List<BigDecimal> temposChegada =  geraTemposChegada();
		
		List<Cliente> clientes = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			Cliente c = new Cliente();
			c.setServico(temposServico.get(i));
			c.setChegada(temposChegada.get(i));
			clientes.add(c);
		}
		
		
		return clientes;
	}
	
	private void geraChegadas(List<Cliente> clientes){
		Random gerador = new Random();
		for (Cliente cliente : clientes) {
			cliente.setChegada(BigDecimal.valueOf(gerador.nextDouble()));
		}
	}
	
	private List<BigDecimal>  geravaloresUniformes(){
		
		List<BigDecimal> uniformes = new ArrayList<>();
		
		Random gerador = new Random();
		
		for (int i = 0; i<N ; i++) {
			BigDecimal c = BigDecimal.ZERO;
			c = BigDecimal.valueOf(gerador.nextDouble());
			uniformes.add(c);
		}
		return uniformes;
	}
	
	
	
}

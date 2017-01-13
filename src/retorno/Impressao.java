package retorno;

import java.util.List;

import entidade.Cliente;

public class Impressao {

	public static void imprimeSaida(List<Cliente> clientes, List<Cliente> clientes2){
		int i = 0;
		System.out.println("Clientes da Fila 1");
		
		for (Cliente cliente : clientes) {
			i++;
			System.out.println("Cliente (" + i + ")");
			System.out.println("Chegada: " + cliente.getChegada().toString());
			System.out.println("Tempo de Serviço: " + cliente.getServico().toString());
			System.out.println("Saída: " + cliente.getSaida().toString());
			System.out.println("Servico residual: " + cliente.getResidual().toString());
			System.out.println("Tempo no Sistema: " + cliente.getTempoSistema().toString());
			System.out.println("Tempo na Fila: " + cliente.getTempoFila().toString());
			System.out.println("Período Ocupado: " + cliente.getPeriodoOcupado());
			System.out.println("Tamanho da Fila: " + cliente.getTamanhoFilaChegada() + "\n");
		}
		i = 0;
		System.out.println("-------------------------------------------------------------------------" + "\n");
		
		System.out.println("Clientes da Fila 2");
		
		for (Cliente cliente : clientes2) {
			i++;
			System.out.println("Cliente (" + i + ")");
			System.out.println("Chegada: " + cliente.getChegada().toString());
			System.out.println("Tempo de Serviço: " + cliente.getServico().toString());
			System.out.println("Saída: " + cliente.getSaida().toString());
			System.out.println("Servico residual: " + cliente.getResidual().toString());
			System.out.println("Tempo no Sistema: " + cliente.getTempoSistema().toString());
			System.out.println("Tempo na Fila: " + cliente.getTempoFila().toString());
			System.out.println("Período Ocupado: " + cliente.getPeriodoOcupado());
			System.out.println("Tamanho da Fila: " + cliente.getTamanhoFilaChegada() + "\n");
		}	
	}
	
	public static void imprimeSaida(List<Cliente> clientes){
		int i = 0;
		for (Cliente cliente : clientes) {
			i++;
			System.out.println("Cliente (" + i + ")");
			System.out.println("Chegada: " + cliente.getChegada().toString());
			System.out.println("Tempo de Serviço: " + cliente.getServico().toString());
			System.out.println("Saída: " + cliente.getSaida().toString());
			System.out.println("Servico residual: " + cliente.getResidual().toString());
			System.out.println("Tempo no Sistema: " + cliente.getTempoSistema().toString());
			System.out.println("Tempo na Fila: " + cliente.getTempoFila().toString());
			System.out.println("Período Ocupado: " + cliente.getPeriodoOcupado());
			System.out.println("Tamanho da Fila: " + cliente.getTamanhoFilaChegada() + "\n");
		}
	}

	public static void imprimeSaida(List<Cliente> clientes, String tipoServidor){
		int i = 0;
		System.out.println("Simulacao servidor: " + tipoServidor);
		for (Cliente cliente : clientes) {
			i++;
			System.out.println("Cliente (" + i + ")");
			System.out.println("Chegada: " + cliente.getChegada().toString());
			System.out.println("Tempo de Serviço: " + cliente.getServico().toString());
			System.out.println("Saída: " + cliente.getSaida().toString());
			System.out.println("Servico residual: " + cliente.getResidual().toString());
			System.out.println("Tempo no Sistema: " + cliente.getTempoSistema().toString());
			System.out.println("Tempo na Fila: " + cliente.getTempoFila().toString());
			System.out.println("Período Ocupado: " + cliente.getPeriodoOcupado());
			System.out.println("Tamanho da Fila: " + cliente.getTamanhoFilaChegada() + "\n");
		}
	}
}

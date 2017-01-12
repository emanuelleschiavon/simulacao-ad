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
			System.out.println("Chegada " + cliente.getChegada().toString());
			System.out.println("Serviço " + cliente.getServico().toString());
			System.out.println("Saída " + cliente.getSaida().toString() + "\n");
			//System.out.println("Primeira alocacao na fila: " + cliente.getTamanhoFilaChegada() + "\n");
		}
		i = 0;
		System.out.println("-------------------------------------------------------------------------");
		
		System.out.println("Clientes da Fila 2");
		
		for (Cliente cliente : clientes2) {
			i++;
			System.out.println("Cliente (" + i + ")");
			System.out.println("Chegada " + cliente.getChegada().toString());
			System.out.println("Serviço " + cliente.getServico().toString());
			System.out.println("Saída " + cliente.getSaida().toString() + "\n");
			//System.out.println("Primeira alocacao na fila: " + cliente.getTamanhoFilaChegada() + "\n");
		}	
	}
	
	public static void imprimeSaida(List<Cliente> clientes){
		int i = 0;
		for (Cliente cliente : clientes) {
			i++;
			System.out.println("cliente: (" + i + ")");
			System.out.println("Chegada " + cliente.getChegada().toString());
			System.out.println("Serviço " + cliente.getServico().toString());
			System.out.println("Saída " + cliente.getSaida().toString());
			System.out.println("Primeira alocacao na fila: " + cliente.getTamanhoFilaChegada() + "\n");
		}
	}

}

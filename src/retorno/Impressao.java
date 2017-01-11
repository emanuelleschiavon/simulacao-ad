package retorno;

import java.util.List;

import entidade.Cliente;

public class Impressao {

	public static void imprimeSaida(List<Cliente> clientes, List<Cliente> clientes2){
		for (Cliente cliente : clientes) {
			System.out.println("Chegada " + cliente.getChegada().toString());
			System.out.println("Serviço " + cliente.getServico().toString());
			System.out.println("Saída " + cliente.getSaida().toString() + "\n");
		}
	}
	
	public static void imprimeSaida(List<Cliente> clientes){
		for (Cliente cliente : clientes) {
			System.out.println("Chegada " + cliente.getChegada().toString());
			System.out.println("Serviço " + cliente.getServico().toString());
			System.out.println("Saída " + cliente.getSaida().toString() + "\n");
		}
	}
}

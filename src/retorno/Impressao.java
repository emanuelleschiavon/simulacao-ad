package retorno;

import java.util.List;

import entidade.Cliente;

public class Impressao {

	public static void imprimeSaida(List<Cliente> clientes, List<Cliente> clientes2){
		for (Cliente cliente : clientes) {
			System.out.println("Chegada " + cliente.getChegada().toString());
			System.out.println("Servi�o " + cliente.getServico().toString());
			System.out.println("Sa�da " + cliente.getSaida().toString() + "\n");
		}
	}
	
	public static void imprimeSaida(List<Cliente> clientes){
		int i = 0;
		for (Cliente cliente : clientes) {
			i++;
			System.out.println("cliente: (" + i + ")");
			System.out.println("Chegada " + cliente.getChegada().toString());
			System.out.println("Servi�o " + cliente.getServico().toString());
			System.out.println("Sa�da " + cliente.getSaida().toString() + "\n");
		}
	}

}

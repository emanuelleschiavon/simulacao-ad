package entidade;

import java.util.ArrayList;
import java.util.List;

public class Fila {
	private List<Cliente> filaClientes = new ArrayList<>();
	private boolean vazia = true;
	
	public List<Cliente> getFilaClientes() {
		return filaClientes;
	}
	public void adicionaFila(Cliente cliente) {
		this.filaClientes.add(cliente);
	}
	
	public boolean isVazia() {
		return vazia;
	}
	public void setVazia(boolean vazia) {
		this.vazia = vazia;
	}
}

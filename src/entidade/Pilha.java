package entidade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pilha {

	private List<Cliente> pilhaClientes = new ArrayList<>();
	private Cliente topo;
	private boolean vazia = true;
	private List<Cliente> pilhaInvertida;

	public List<Cliente> getPilha() {
		return pilhaClientes;
	}
	public Cliente getTopo() {
		return topo;
	}
	public boolean isVazia() {
		return vazia;
	}
	public List<Cliente> getPilhaInvertida() {
		pilhaInvertida = getPilha();
		Collections.reverse(pilhaInvertida);
		return pilhaInvertida;
	}
	
	public void push(Cliente elemento){
		this.pilhaClientes.add(elemento);
		this.topo = elemento;
		this.vazia = false;
	}
	public Cliente pop() throws Exception{
		

		if (pilhaClientes.size()==0){
			throw new Exception("Tentando tirar da pilha vazia");
		}
		
		Cliente elemento = topo;
		pilhaClientes.remove(topo);
		
		if (pilhaClientes.size()==0){
			vazia=true;
			topo = null;
		}else{
			topo = pilhaClientes.get(pilhaClientes.size()-1);
		}
		return elemento;
	}
	
}

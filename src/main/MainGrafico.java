package main;
import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import entidade.Cliente;
import entidade.Fila;
import entidade.Pilha;

public class MainGrafico extends JFrame {

	
	private static final double LAMBDA = 0.9; // taxa chegada
	private static final double MU = 1; // taxa servico
	private static final int N = 1000;  // total clientes
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainGrafico(List<Cliente> clientes, String tipo, String yAxisLabel){
		super("XY Line Chart");
		 
		JPanel chartPanel = createChartPanel(clientes, tipo, yAxisLabel);
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private XYDataset createDataset(List<Cliente> clientes, String yAxisLabel) {
	    XYSeriesCollection dataset = new XYSeriesCollection();
	    XYSeries series1 = new XYSeries("lambda = 0.9; mu = 1");
	    
	 if(yAxisLabel.compareTo("tempo de servico") == 0){
		 double esperancaServico = 0;
		 for (Cliente c : clientes) {
			 series1.add(c.getChegada(), c.getServico());
			 esperancaServico += c.getServico().doubleValue();
		}
		 esperancaServico = esperancaServico/N;
		 series1.setKey(series1.getKey() + "; esperanca = " + esperancaServico);
	 }
	 
	 if(yAxisLabel.compareTo("servico pendente") == 0){ 
		 double esperancaPendente = 0;
		 for (Cliente c : clientes) {
			 series1.add(c.getChegada(), c.getPendente());
			 esperancaPendente += c.getPendente().doubleValue();
		}
		 esperancaPendente = esperancaPendente/N;
		 series1.setKey(series1.getKey() + "; esperanca = " + esperancaPendente);
	 }
	 
	 if(yAxisLabel.compareTo("tempo de espera") == 0){ 
		 double esperancaEspera = 0;
		 for (Cliente c : clientes) {
			 series1.add(c.getChegada(), (c.getSaida().subtract(c.getChegada())).subtract(c.getServico()) );
			 esperancaEspera += (c.getSaida().subtract(c.getChegada())).subtract(c.getServico()).doubleValue();
		}
		 esperancaEspera = esperancaEspera/N;
		 series1.setKey(series1.getKey() + "; esperanca = " + esperancaEspera);
	 }
	 
	 if(yAxisLabel.compareTo("tempo no sistema") == 0){ 
		 double esperancaSistema = 0;
		 for (Cliente c : clientes) {
			 series1.add(c.getChegada(), c.getSaida().subtract(c.getChegada()) );
			 esperancaSistema += c.getSaida().subtract(c.getChegada()).doubleValue();
		}
		 esperancaSistema = esperancaSistema/N;
		 series1.setKey(series1.getKey() + "; esperanca = " + esperancaSistema);
	 }
	 
	 if(yAxisLabel.compareTo("tempo residual") == 0){
		 double esperancaResidual = 0;
		 for (Cliente c : clientes) {
			 series1.add(c.getChegada(), c.getResidual());
			 esperancaResidual += c.getResidual().doubleValue();
		}
		 esperancaResidual = esperancaResidual/N;
		 series1.setKey(series1.getKey() + "; esperanca = " + esperancaResidual);
	 }
	 
	    dataset.addSeries(series1);
	 
	    return dataset;
	}
	
	private JPanel createChartPanel(List<Cliente> clientes, String tipoServidor, String yAxisLabel) {
	    String chartTitle = tipoServidor;
	    String xAxisLabel = "chegada";
	   
	 
	    XYDataset dataset = createDataset(clientes, yAxisLabel);
	 
	    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
	            xAxisLabel, yAxisLabel, dataset);
	 
	    return new ChartPanel(chart);
	}
	
	
	   
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	
	        	
	        	simulacaoTest();
	            
	        }
	    });
	}
	
	public static void simulacaoTest(){
		Simulacao sim = new Simulacao();
		Fila fila1 = new Fila();
		Fila fila2 = new Fila();
		Pilha pilha = new Pilha();
		List<Cliente> clientes = geraClientes();
		List<Cliente> clientes2 = geraClientes();
		
		sim.setClientes(clientes);
		sim.fcfs(fila1);
		new MainGrafico(clientes, "fcfs sem preempcao", "tempo de servico").setVisible(true);
		new MainGrafico(clientes, "fcfs sem preempcao", "servico pendente").setVisible(true);
		new MainGrafico(clientes, "fcfs sem preempcao", "tempo de espera").setVisible(true);
		new MainGrafico(clientes, "fcfs sem preempcao", "tempo no sistema").setVisible(true);
		new MainGrafico(clientes, "fcfs sem preempcao", "tempo residual").setVisible(true);
		
//		clientes.clear();
//		clientes = geraClientes();
//		sim.setClientes(clientes);
//		sim.lcfsComPreempcao(pilha);
//		new MainGrafico(clientes, "lcfs com preempcao", "tempo de servico").setVisible(true);
//		new MainGrafico(clientes, "lcfs com preempcao", "servico pendente").setVisible(true);
//		new MainGrafico(clientes, "lcfs com preempcao", "tempo de espera").setVisible(true);
//		new MainGrafico(clientes, "lcfs com preempcao", "tempo no sistema").setVisible(true);
//		
//		pilha.getPilha().clear();
//		clientes.clear();
//		clientes = geraClientes();
//		sim.setClientes(clientes);
//		sim.lcfsSemPreempcao(pilha);
//		new MainGrafico(clientes, "lcfs sem preempcao", "tempo de servico").setVisible(true);
//		new MainGrafico(clientes, "lcfs sem preempcao", "servico pendente").setVisible(true);
//		new MainGrafico(clientes, "lcfs sem preempcao", "tempo de espera").setVisible(true);
//		new MainGrafico(clientes, "lcfs sem preempcao", "tempo no sistema").setVisible(true);
		
//		fila1.getFilaClientes().clear();
//		clientes.clear();
//		clientes = geraClientes();
//		sim.setClientes(clientes);
//		sim.setClientes2(clientes2);
//		sim.fcfsComPreempcao2Filas(fila1, fila2);
//		new MainGrafico(clientes, "lcfs sem preempcao classe 1", "tempo de servico").setVisible(true);
//		new MainGrafico(clientes2, "lcfs sem preempcao classe 2", "tempo de servico").setVisible(true);
//		
//		new MainGrafico(clientes, "lcfs sem preempcao classe 1", "servico pendente").setVisible(true);
//		new MainGrafico(clientes2, "lcfs sem preempcao classe 2", "servico pendente").setVisible(true);
//		
//		new MainGrafico(clientes, "lcfs sem preempcao classe 1", "tempo de espera").setVisible(true);
//		new MainGrafico(clientes2, "lcfs sem preempcao classe 2", "tempo de espera").setVisible(true);
//		
//		new MainGrafico(clientes, "lcfs sem preempcao classe 1", "tempo no sistema").setVisible(true);
//		new MainGrafico(clientes2, "lcfs sem preempcao classe 2", "tempo no sistema").setVisible(true);
		
	}
	
	
	   
	private static List<BigDecimal> geraTemposServico(){
		List<BigDecimal> u = geravaloresUniformes();
		List<BigDecimal> taxasServico = new ArrayList<>();
	
		for(BigDecimal b : u){
			BigDecimal ts =  BigDecimal.valueOf(-1/MU*Math.log(b.doubleValue()));
			taxasServico.add(ts);
		}
		
		return taxasServico;
		
	}
		
	private static List<BigDecimal> geraTemposEntreChegadas(){
		
		List<BigDecimal> u = geravaloresUniformes();
		List<BigDecimal> temposEntreChegadas = new ArrayList<>();
		
		for(BigDecimal b : u){
			BigDecimal ts =  BigDecimal.valueOf(-1/LAMBDA*Math.log(b.doubleValue()));
			temposEntreChegadas.add(ts);
		}
		
		return temposEntreChegadas;	
	}
		
	private static List<BigDecimal> geraTemposChegada(){
		
		List<BigDecimal> tempoEntreChegadas = geraTemposEntreChegadas();
		
		List<BigDecimal> temposChegada = new ArrayList<>();
		
		BigDecimal tc = BigDecimal.ZERO;
		
		for (BigDecimal tec : tempoEntreChegadas) {
			tc = tc.add(tec);
			temposChegada.add(tc);
		}
		return temposChegada;
		
	}
	
	private static List<Cliente> geraClientes(){
		
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
		
	private static List<BigDecimal>  geravaloresUniformes(){
		
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
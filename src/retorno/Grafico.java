package retorno;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import entidade.Cliente;

public class Grafico extends ApplicationFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Grafico( String applicationTitle , String chartTitle, String yAxis ,List<Cliente> clientes )
	   {
	      super(applicationTitle);
	      JFreeChart lineChart = ChartFactory.createLineChart(
	         chartTitle,
	         "Years","Number of Schools",
	         createDataset(clientes),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	         
	      ChartPanel chartPanel = new ChartPanel( lineChart );
	      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	      setContentPane( chartPanel );
	   }
	
	
	private DefaultCategoryDataset createDataset(List<Cliente> clientes)
	   {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      
	      dataset.addValue( 15 , "schools" , "1970" );
	      dataset.addValue( 30 , "schools" , "1980" );
	      dataset.addValue( 60 , "schools" ,  "1990" );
	      dataset.addValue( 120 , "schools" , "2000" );
	      dataset.addValue( 240 , "schools" , "2010" );
	      dataset.addValue( 300 , "schools" , "2014" );
	      
//	      for(Cliente c : clientes){
//	    	  dataset.addValue(c.getServico(), "ms", c.getChegada().toString());
//	      }
//	     
	      return dataset;
	   }

	public void show(){
		this.pack( );
		RefineryUtilities.centerFrameOnScreen( this );
		this.setVisible( true );
	}
	
	
}

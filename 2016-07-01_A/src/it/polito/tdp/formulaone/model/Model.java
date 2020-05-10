package it.polito.tdp.formulaone.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	private FormulaOneDAO dao;
	private SimpleDirectedWeightedGraph<Driver, DefaultWeightedEdge> grafo;
	private List <Driver> listaD;
    public Model () {
    	dao = new FormulaOneDAO();
    	
    }
    
	public List<Integer> getAnni() {
		return dao.getAllYearsOfRace();
	}
	public void creaGrafo(int anno) {
		listaD =  new LinkedList<Driver>();
		// creo il grafo;
        grafo = new SimpleDirectedWeightedGraph<Driver,DefaultWeightedEdge> (DefaultWeightedEdge.class);
        listaD.addAll(dao.getPiloti(anno));
		// definisco i veritici;
	   Graphs.addAllVertices(grafo, listaD );
		
		// creo gli archi;
	//  grafo.addEdge(e1,e2);
//		Graphs.addEdge(g, sourceVertex, targetVertex, weight);
	//  Graphs.addEdgeWithVertices(grafo , partenza, arrivo, peso)
//		grafo.setEdgeWeight(partenza, arrivo, peso);
	   for (Driver d1 : grafo.vertexSet()) {
		   for (Driver d2 : grafo.vertexSet()) {
			   int peso = dao.getPeso(anno , d1.getDriverId(), d2.getDriverId());
			   if (peso>0 && d1.getDriverId()<d2.getDriverId()) {
				   Graphs.addEdgeWithVertices(grafo , d1, d2, peso);
			   }
		   }
	   }
		
		// valori
	    System.out.println("N. vertici : "+grafo.vertexSet().size());
		System.out.println("N. archi : "+grafo.edgeSet().size());

	}


}

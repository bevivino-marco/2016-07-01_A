package it.polito.tdp.formulaone.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	private FormulaOneDAO dao;
	private SimpleDirectedWeightedGraph<Driver, DefaultWeightedEdge> grafo;
	private List <Driver> listaD;
	private int min;
	private int M=0;
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
			   if (peso>0 ) {
				   Graphs.addEdgeWithVertices(grafo , d1, d2, peso);
			   }
		   }
	   }
		
		// valori
	    System.out.println("N. vertici : "+grafo.vertexSet().size());
		System.out.println("N. archi : "+grafo.edgeSet().size());

	}

	public String DreamTeam(int k){
		List <Driver> parziale = new LinkedList<>();
		List <Driver> finale = new LinkedList<>();
		min =  Integer.MAX_VALUE;
		
		
		cerca (0, parziale, finale, k);
		
		return finale+" "+min;
		
	}
	
	
	public void cerca (int livello, List <Driver >parziale,List<Driver> finale, int k) {
		
		if (livello == k) {
			int p =getPunteggio(parziale);
			System.out.println(parziale.toString()+"+++++"+p+"\n");
			if (p<min) {
				//System.out.println(p+" "+min+"\n");
				finale.clear();
				finale.addAll(parziale);
				min = p;
				
				
				
				
			}
			return;
		}
		for (Driver d : listaD) {
			if (!parziale.contains(d)) {
				parziale.add(d);
				cerca (livello+1, parziale, finale, k);
				parziale.remove(parziale.size()-1);
			}
			
		}
		
		
	}

	private int getPunteggio(List<Driver> parziale) {
		int p = 0;
		for (Driver d : parziale) {
			p+=Graphs.predecessorListOf(grafo, d).size();
		}
		return p;
	}
	
	
	
	

}

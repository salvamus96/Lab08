package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {

	private Graph <String, DefaultEdge> graph;
	
	private WordDAO dao;
	private List <String> parole;
	
	public Model () {
		this.dao = new WordDAO();
		
	}
	public Graph <String, DefaultEdge> createGraph(int numeroLettere) {
		
		this.graph = new SimpleGraph <> (DefaultEdge.class);
		this.parole = dao.getAllWordsFixedLength(numeroLettere);

		Graphs.addAllVertices(this.graph, parole);
		
		for (String s : parole)
			for (String p : this.getAllSimiliarWord(s))
				Graphs.addEdgeWithVertices(this.graph, s, p);
	
		return graph;
	}
	
	private Set<String> getAllSimiliarWord(String parola) {
		Set <String> similarWord = new HashSet <> ();
		
		for (String s : parole) {
			if (!s.equals(parola) &&s.compareTo(parola) > 0) {
				int count = 0;
				for(int i = 0; i < parola.length(); i++) {
					if(s.charAt(i) != parola.charAt(i))
						count ++;
				}
				if (count == 1)
					similarWord.add(s);
			}
		}
		return similarWord;
	}
	
	public List<String> displayNeighbours(String parola) {
		return Graphs.neighborListOf(this.graph, parola);
	}

	
	public String findMaxDegree() {
		StringBuilder s = new StringBuilder ();
		int maxDegree = 0;
		String maxParola = "";
		List <String> vicini = new ArrayList<> ();
		
		for (String vertice : this.graph.vertexSet())
			if (this.graph.degreeOf(vertice) > maxDegree) {
				maxDegree = this.graph.degreeOf(vertice);
				maxParola = vertice;
			}
		
		vicini = Graphs.neighborListOf(this.graph, maxParola);
		s.append("Grado massimo: " + maxDegree + "\nVertice: " + maxParola + "\nLista dei vicini: " + vicini);
		
		return s.toString();
	}
	
}

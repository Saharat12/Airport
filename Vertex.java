//
// Name: Chokboonanun, Saharat
// Project: 5
// Due: 5/14/2021
// Course: cs-2400-03-sp21
//
// Description:
// Creating a airport app that uses graph ADT to connect each node and data. 
//

import java.util.Iterator;
import java.util.NoSuchElementException;

class Vertex<T> implements VertexInterface <T>{
    private T label;
    private ListWithIteratorInterface<Edge> edgeList; 
    private boolean visited; 
    private VertexInterface<T> previousVertex; 
    private double cost; 
    
    public Vertex(T vertexLabel){
        label = vertexLabel;
        edgeList = new LinkedListWithIterator<Edge>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } 

    public boolean connect(VertexInterface<T> endVertex, double edgeWeight){
        boolean result = false;
        if (!this.equals (endVertex)){
            Iterator <VertexInterface <T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext ()){
                VertexInterface<T> nextNeighbor = neighbors.next ();
                if (endVertex.equals (nextNeighbor))
                    duplicateEdge = true;
            } 

            if (!duplicateEdge){
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } 
        } 
        return result;
    }

    public boolean connect(VertexInterface<T> endVertex){
        return connect(endVertex, 0);
    } 

    public boolean disconnect(VertexInterface<T> endVertex) {
        int x = this.getPosition(endVertex.getLabel().toString());
        return edgeList.remove(x);
    }

    public int getPosition(String temp){
        int x = 1;

        while(true){
            if(edgeList.getEntry(x).vertex.getLabel().toString().equals(temp)){
                return x;
            }
            x++;
        }
    }

    public Iterator<VertexInterface<T>> getNeighborIterator(){
        return new neighborIterator();
    } 

    public boolean hasNeighbor(){
        return !edgeList.isEmpty();
    } 

    public VertexInterface<T> getUnvisitedNeighbor(){
        VertexInterface<T> result = null;
        Iterator<VertexInterface <T>> neighbors = getNeighborIterator ();

        while ((neighbors.hasNext () && (result == null))){
            VertexInterface<T> nextNeighbor = neighbors.next ();

            if (!nextNeighbor.isVisited ())
                result = nextNeighbor;

        } 
        return result;
    } 


    protected class Edge{
        private VertexInterface<T> vertex; 
        private double weight;

        protected Edge(VertexInterface<T> endVertex, double edgeWeight){
            vertex = endVertex;
            weight = edgeWeight;
        }

        protected Edge(VertexInterface<T> endVertex){
            vertex = endVertex;
            weight = 0;
        }

        protected VertexInterface<T> getEndVertex(){
            return vertex;
        } 

        protected double getWeight(){
            return weight;
        } 
    } 

    public T getLabel(){
        return label;
    }

    public void visit(){
		visited = true;
	}

    public void unvisit(){
        visited = false;
    }

    public boolean isVisited(){
        return visited;
    }

    public void setCost(double newCost){
        cost = newCost;
    }

    public double getCost(){
        return cost;
    }

    public void setPredecessor(VertexInterface<T> predecessor){
        previousVertex = predecessor;
    }

    public VertexInterface<T> getPredecessor(){
        return previousVertex;
    }

    public boolean hasPredecessor(){
		return previousVertex != null;
	} 

    public Iterator<Double> getWeightIterator(){
		return new weightIterator();
	}
    
    private class weightIterator implements Iterator<Double>
	{
		private Iterator<Edge> edges;
		
		private weightIterator(){
			edges = edgeList.getIterator();
		} 
		
		public boolean hasNext() {
			return edges.hasNext();
		} 
		
		public Double next(){
            Double edgeWeight;
			
			if (edges.hasNext()){
				Edge edgeToNextNeighbor = edges.next();
				edgeWeight = edgeToNextNeighbor.getWeight();
			}
			else
				throw new NoSuchElementException();
		
			return edgeWeight;
		} 

	} 

    private class neighborIterator implements Iterator<VertexInterface<T>>{
        private Iterator<Edge> edges;
        
        private neighborIterator(){
            edges = edgeList.getIterator();
        }
        
        public boolean hasNext() {
            return edges.hasNext();
        }
        
        public VertexInterface<T> next(){
            VertexInterface<T> nextNeighbor = null;
            
            if (edges.hasNext()){
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();
            
            return nextNeighbor;
        } 
        
	} 

}
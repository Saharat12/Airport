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
import java.util.PriorityQueue;

public class DirectedGraph<T> implements GraphInterface <T>{
    private DictionaryInterface <T, VertexInterface<T>> vertices;
    private int edgeCount;
    
    public DirectedGraph (){
        vertices = new LinkedDictionary <T, VertexInterface<T>> ();
        edgeCount = 0;
    } 

    public boolean addVertex (T vertexLabel){
        VertexInterface<T> isDuplicate = vertices.add(vertexLabel, new Vertex(vertexLabel));
        return isDuplicate == null; 
    } 

    public boolean addEdge (T begin, T end, double edgeWeight){
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        if(hasEdge(begin, end)){
            System.out.print("(DUPLICATE) ");
            return result;
        }

        if ((beginVertex != null) && (endVertex != null))
            result = beginVertex.connect(endVertex, edgeWeight);
        if (result)
            edgeCount++;
            
        return result;
    } 

    public boolean addEdge (T begin, T end){
        return addEdge(begin, end, 0);
    } 

    public boolean hasEdge (T begin, T end){
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        if((beginVertex != null) && (endVertex != null)){
            Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();

            while(!found && neighbors.hasNext ()){
                VertexInterface<T> nextNeighbor = neighbors.next();
                if(endVertex.equals(nextNeighbor))
                    found = true;
            } 

        } 

        return found;
    } 

    public boolean removeEdge(T begin, T end){
        boolean result = false;
        edgeCount--;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        result = beginVertex.disconnect(endVertex);
        
        return result;
    }

    public boolean isEmpty (){
        return vertices.isEmpty ();
    } 

    public int getNumberOfVertices(){
	  return vertices.getSize();
	} 

    public int getNumberOfEdges(){
	  return edgeCount;
	} 

    public void getNeighbor(T first){
        VertexInterface<T> beginVertex = vertices.getValue(first);

            if(vertices.getValue(first) == null){
                System.out.println("ERROR AREA CODE!");
            }else{
                Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
                VertexInterface<T> nextNeighbor = neighbors.next();

                System.out.print(nextNeighbor.getLabel());
            }
    }

    public boolean haveVertex(T vertexLabel){
        return vertices.contains(vertexLabel); 
    } 


    protected void resetVertices(){
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
       
        while(vertexIterator.hasNext()){
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        } 
    } 

    public int getShortestPath(T begin, T end, StackInterface<T> path){
        resetVertices();
        boolean done = false;
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        originVertex.visit();
        vertexQueue.enqueue(originVertex);

        while(!done && !vertexQueue.isEmpty()){
            VertexInterface<T> frontVertex = vertexQueue.dequeue();

            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();

            while (!done && neighbors.hasNext()){
                VertexInterface<T> nextNeighbor = neighbors.next();

                if (!nextNeighbor.isVisited()){
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                }

                if (nextNeighbor.equals(endVertex))
                    done = true;
            } 
        } 
       
        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()){
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        }

        return pathLength;
    }

    public double getCheapestPath(T begin, T end, StackInterface<T> path){
		resetVertices();
		boolean done = false;

		PriorityQueue<EntryPQ> priorityQueue = new PriorityQueue<EntryPQ>();
		
		VertexInterface<T> originVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		priorityQueue.add(new EntryPQ(originVertex, 0, null));
	
		while (!done && !priorityQueue.isEmpty()){
			EntryPQ frontEntry = priorityQueue.remove();
			VertexInterface<T> frontVertex = frontEntry.getVertex();
			
			if (!frontVertex.isVisited()){
				frontVertex.visit();
				frontVertex.setCost(frontEntry.getCost());
				frontVertex.setPredecessor(frontEntry.getPredecessor());
				
				if (frontVertex.equals(endVertex))
					done = true;
				else {
					Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
					Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
					while (neighbors.hasNext()){
						VertexInterface<T> nextNeighbor = neighbors.next();
						Double weightOfEdgeToNeighbor = edgeWeights.next();
						
						if (!nextNeighbor.isVisited()){
							double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
							priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
						} 
					} 
				} 
			} 
		} 

		double pathCost = endVertex.getCost();
		path.push(endVertex.getLabel());
		
		VertexInterface<T> vertex = endVertex;
		while (vertex.hasPredecessor()){
			vertex = vertex.getPredecessor();
			path.push(vertex.getLabel());
		} 

		return pathCost;
	} 

    private class EntryPQ implements Comparable<EntryPQ>{
		private VertexInterface<T> vertex; 	
		private VertexInterface<T> previousVertex; 
		private double cost;
		
		private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex){
			this.vertex = vertex;
			this.previousVertex = previousVertex;
			this.cost = cost;
		} 
		
		public VertexInterface<T> getVertex(){
			return vertex;
		}
		
		public VertexInterface<T> getPredecessor(){
			return previousVertex;
		} 

		public double getCost(){
			return cost;
		} 
		
		public int compareTo(EntryPQ otherEntry){
			return (int)Math.signum(cost - otherEntry.cost);
		} 
		
		public String toString(){
			return vertex.toString() + " " + cost;
		} 
	} 
} 
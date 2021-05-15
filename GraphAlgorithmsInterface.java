//
// Name: Chokboonanun, Saharat
// Project: 5
// Due: 5/14/2021
// Course: cs-2400-03-sp21
//
// Description:
// Creating a airport app that uses graph ADT to connect each node and data. 
//

public interface GraphAlgorithmsInterface<T>{
    public int getShortestPath(T begin, T end, StackInterface<T> path);
    public double getCheapestPath(T begin, T end, StackInterface<T> path);
}

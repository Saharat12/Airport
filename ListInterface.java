//
// Name: Chokboonanun, Saharat
// Project: 5
// Due: 5/14/2021
// Course: cs-2400-03-sp21
//
// Description:
// Creating a airport app that uses graph ADT to connect each node and data. 
//

public interface ListInterface<T>{
    public boolean add(T newEntry);
    public boolean add(int newPosition, T newEntry);
    public boolean remove(int place);
    public T getEntry(int givenPosition);  
    public boolean contains(T anEntry);  
    public boolean isEmpty();
} 
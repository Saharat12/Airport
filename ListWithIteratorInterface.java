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

public interface ListWithIteratorInterface<T> extends ListInterface<T>, Iterable<T>{
  public Iterator<T> getIterator();
} 
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

public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T>
{
	private Node firstNode;
	private int  length;  
  
	public LinkedListWithIterator(){
		clear();
	}

	public final void clear() {
		firstNode = null;
		length = 0;
	} 
  
	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry);

		if (isEmpty())
			firstNode = newNode;
		else{                             
			Node lastNode = getNodeAt(length);
			lastNode.next = newNode;		     
		} 
		
		length++;
		return true;
	} 

    public boolean add(int newPosition, T newEntry){
		boolean isSuccessful = true;

		if ((newPosition >= 1) && (newPosition <= length+1)){	
			Node newNode = new Node(newEntry); 

			if (isEmpty() || (newPosition == 1)){
				newNode.next = firstNode;							
				firstNode = newNode;
			}
			else{
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.next;
				newNode.next = nodeAfter;
				nodeBefore.next = newNode;
			}
		
			length++;
		}else{
			isSuccessful = false;
        }

		return isSuccessful;
	}

	public boolean remove(int givenPosition){
               
	  	boolean removed = false;

	  	if ((givenPosition >= 1) && (givenPosition <= length)){
	    	assert !isEmpty();
	    
			if (givenPosition == 1){
			   
				firstNode = firstNode.next;
				removed = true;
			}else                          {
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.next;
				Node nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter; 
			} 

			length--;
	  	} 
	  
	  return removed;                  
	                                   
	}

    public T getEntry(int givenPosition){
  	    T result = null; 
      
		if ((givenPosition >= 1) && (givenPosition <= length)){
			assert !isEmpty();
            result = getNodeAt(givenPosition).data;
   	    } 
      
        return result;
    }

	public boolean contains(T anEntry){
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null)){
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} 
		return found;
	} 

    public boolean isEmpty(){
  	    boolean result;
 		
        if (length == 0){
            assert firstNode == null;
            result = true;
        }
        else{
            assert firstNode != null;
            result = false;
        } 
   	
   	    return result;
    } 
	
	public Iterator<T> getIterator(){
	  return new IteratorForLinkedList();
	} 

    public Iterator<T> iterator(){
        return getIterator();
    } 

	private Node getNodeAt(int givenPosition){
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= length);
		Node currentNode = firstNode;
		
    
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.next;
		
		assert currentNode != null;
		return currentNode;
	} 

	private class IteratorForLinkedList implements Iterator<T>{
  	    private Node nextNode;

		private IteratorForLinkedList(){
			nextNode = firstNode;
		} 
		
		public boolean hasNext(){
			return nextNode != null;
		} 

		public T next(){
			if (hasNext()){
				Node returnNode = nextNode; 
				nextNode = nextNode.next;   
				
				return returnNode.data;   
			}
			else
				throw new NoSuchElementException("Illegal call to next();" + "iterator is after end of list.");
		} 
	} 
	
	private class Node {
		private T data; 
		private Node next; 

		private Node(T dataPortion) {
			this(dataPortion, null);
		} 

		private Node(T dataPortion, Node linkPortion) {
			data = dataPortion;
			next = linkPortion;
		} 

	} 
}
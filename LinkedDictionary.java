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

public class LinkedDictionary<K, V> implements DictionaryInterface<K, V>
{
	private Node firstNode;  
	private int  currentSize; 
	
	public LinkedDictionary(){
		firstNode = null;		
    	currentSize = 0;
	} 
	
	
  	public V add(K key, V value){
    	V result = null;
    	Node currentNode = firstNode;

			while ( (currentNode != null) && !key.equals(currentNode.getKey()) ){
				currentNode = currentNode.getNextNode();
			} 
			
			if (currentNode == null){
				Node newNode = new Node(key, value);
				newNode.setNextNode(firstNode);
				firstNode = newNode;
				currentSize++;
			}
			else{
				result = currentNode.getValue();
				currentNode.setValue(value); 
			} 
			
		return result;
  	} 

  	public V remove(K key){
		V result = null; 
		
			if (!isEmpty()){	
				Node currentNode = firstNode;
				Node nodeBefore = null;
		
			while ( (currentNode != null) && !key.equals(currentNode.getKey()) ){
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			} 
				
			if (currentNode != null){	
				Node nodeAfter = currentNode.getNextNode();
					if (nodeBefore == null)
						firstNode = nodeAfter;
					else
						nodeBefore.setNextNode(nodeAfter);       

					result = currentNode.getValue();           
					currentSize--;                              
				} 
			}
				
		return result;  
  	} 

  	public V getValue(K key){
		V result = null;

		
		Node currentNode = firstNode;
			
		while ( (currentNode != null) && !key.equals(currentNode.getKey()) ){
			currentNode = currentNode.getNextNode();
		} 

		if (currentNode != null){
			result = currentNode.getValue();
		} 
			
		return result;
  	} 

	public boolean contains(K key){
   		return getValue(key) != null; 
  	} 

	public boolean isEmpty(){
		return currentSize == 0;
	} 
		
	public int getSize(){
		return currentSize;
	}

	public final void clear(){ 
		firstNode = null;		
    	currentSize = 0;
  	} 

	public Iterator<K> getKeyIterator(){
		return new KeyIterator();
	} 
	
	public Iterator<V> getValueIterator(){
		return new ValueIterator();
	} 
  
	private class KeyIterator implements Iterator<K>{
		private Node nextNode;
		
		private KeyIterator(){
			nextNode = firstNode;
		} 

		public boolean hasNext() {
			return nextNode != null;
		}
		
		public K next(){
			K result;
			
			if (hasNext()){
				result = nextNode.getKey();
				nextNode = nextNode.getNextNode();
			}else{
				throw new NoSuchElementException();
			} 
		
			return result;
		} 
		
	} 
	
	private class ValueIterator implements Iterator<V>{
		private Node nextNode;
		
		private ValueIterator(){
			nextNode = firstNode;
		} 
		
		public boolean hasNext() {
			return nextNode != null;
		} 
		
		public V next(){
			V result;
			
			if (hasNext()){
				result = nextNode.getValue();
				nextNode = nextNode.getNextNode();
			}else{
				throw new NoSuchElementException();
			} 
		
			return result;
		} 

	} 

	private class Node {
		private K key;
		private V value;
		private Node next;

		private Node(K searchKey, V dataValue){
			key = searchKey;
			value = dataValue;
			next = null;	
		} 
		
		private K getKey(){
			return key;
		} 
		
		private V getValue(){
			return value;
		} 

		private void setValue(V newValue){
			value = newValue;
		} 

		private Node getNextNode(){
			return next;
		} 
		
		private void setNextNode(Node nextNode){
			next = nextNode;
		} 
	} 
} 
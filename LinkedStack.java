//
// Name: Chokboonanun, Saharat
// Project: 4
// Due: 5/14/2021
// Course: cs-2400-03-sp21
//
// Description:
// Creating a airport app that uses graph ADT to connect each node and data. 
//

import java.util.NoSuchElementException;

public class LinkedStack<T> implements StackInterface<T> {
	private Node topNode; 
	private int size;

	public LinkedStack() {
		topNode = null;
		size = -1;
	}

	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
		size++;
	}

	public T pop() {
		if (topNode == null) {
			throw new NoSuchElementException("Stack is empty.");
		}

		T item = peek();
		topNode = topNode.getNextNode();
		size--;
		return item;
	}


	public T peek() {
		if (topNode == null) {
			throw new NoSuchElementException("Stack is empty.");
		}

		return topNode.data;
	}

    public boolean isEmpty() {
		return (topNode == null);
	}

	private class Node {
		private T data; 
		private Node next; 

		private Node(T dataPortion, Node linkPortion) {
			data = dataPortion;
			next = linkPortion;
		}

		private Node getNextNode() {
			return next;
		}
	} 
} 
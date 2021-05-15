//
// Name: Chokboonanun, Saharat
// Project: 5
// Due: 5/14/2021
// Course: cs-2400-03-sp21
//
// Description:
// Creating a airport app that uses graph ADT to connect each node and data. 
//

public final class LinkedQueue<T> implements QueueInterface<T>{
    private Node firstNode; 
    private Node lastNode; 

    public LinkedQueue(){
        firstNode = null;
        lastNode = null;
    } 

    private class Node{
        private T data; 
        private Node next; 

        private Node(T dataPortion, Node nextNode){
            data = dataPortion;
            next = nextNode;
        }

        private T getData(){
            return data;
        }

        private void setData(T newData){
            data = newData;
        }

        private Node getNextNode(){
            return next;
        } 

        private void setNextNode(Node nextNode){
            next = nextNode;
        } 
    } 

    public T getFront() {
        return firstNode.getData();
    } 

    public void enqueue(T newEntry){
        Node newNode = new Node(newEntry, null);

        if (isEmpty())
            firstNode = newNode;
        else{
            lastNode.setNextNode(newNode);
        }

        lastNode = newNode;
    }
    
    public T dequeue(){
        T front = getFront();
     
        firstNode.setData(null);
        firstNode = firstNode.getNextNode();

        if (firstNode == null){
            lastNode = null;
        }

        return front;
    } 

    public boolean isEmpty(){
        return (firstNode == null) && (lastNode == null);
    } 

 
} 
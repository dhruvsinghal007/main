package linkedlist;

public class Node {
	private Node next;
	private int data;
	
	public Node(int data) {
		this.data = data;
		this.next = null;
	}
	
	public int getData(){
		return data;
	}
	
	public Node getNext(){
		return next;
	}
	
	public void setNext(Node n){
		next = n;
	}
	
	public void setData(int d){
		data = d;
	}
}

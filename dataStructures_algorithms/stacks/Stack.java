package stacks;

import linkedlist.LinkedList;
import linkedlist.Node;

public class Stack {
	private LinkedList l;
	
	public Stack() {
		// TODO Auto-generated constructor stub
		l=new LinkedList();
	}
	
	public void push(Node n){
		if(l.getStart()==null){
			l.setStart(n);
		}
		else{
			n.setNext(l.getStart());
			l.setStart(n);
		}
		System.out.println("Pushed : " + n.getData());
	}
	
	public Node pop(){
		Node n;
		if(l.getStart()==null){
			System.out.println("List is empty!!!");
			n = null;
		}
		else{
			n=l.getStart();
			l.setStart(n.getNext());
			System.out.println("Element popped: "+n.getData());
			n.setNext(null);
		}
		return n;
	}
	
	public Node peek(){
		return l.getStart();
	}
	
	public boolean isEmpty(){
		return (l.getStart() == null);
	}
	
	public void display(){
		l.display();
	}
}

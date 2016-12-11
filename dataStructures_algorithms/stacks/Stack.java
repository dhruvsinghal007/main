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
	}
	
	public void pop(){
		if(l.getStart()==null){
			System.out.println("List is empty!!!");
		}
		else{
			Node n=l.getStart();
			l.setStart(n.getNext());
			System.out.println("Element popped: "+n.getData());
			n=null;
		}
	}
}

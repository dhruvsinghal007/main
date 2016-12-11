package stacks;

import linkedlist.LinkedList;
import linkedlist.Node;

public class MinStack {
	private LinkedList l;
	
	public MinStack() {
		l=new LinkedList();
	}
	
	public void pushInMinStack(int ele){
		Node n=new Node(ele);
		if(l.getStart()==null){
			l.setStart(n);
		}
		else if(n.getData()<=l.getStart().getData()){
			l.appendToStart(n);
		}
		else{
			Node n1=pop();
			l.appendToStart(n);
			l.appendToStart(n1);
		}
	}
	
	public Node pop(){
		Node n=l.getStart();
		l.setStart(n.getNext());
		n.setNext(null);
		return n;
	}
	
	public int getMinFromStack(){
		return l.getStart().getData();
	}
	
	public static void main(String[] args) {
		MinStack ms=new MinStack();
		ms.pushInMinStack(2);
		ms.pushInMinStack(3);
		ms.pushInMinStack(1);
		ms.pushInMinStack(3);
		ms.pushInMinStack(4);
		ms.pushInMinStack(1);
		ms.pushInMinStack(3);
	}
}

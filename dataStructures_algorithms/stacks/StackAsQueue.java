package stacks;

import linkedlist.Node;

public class StackAsQueue {

	private Stack s1,s2;
	
	public StackAsQueue() {
		s1 = new Stack();
		s2 = new Stack();
	}
	
	public void enqueue(Node n){
		System.out.println("s1 element added : " + n.getData());
		s1.push(n);
		System.out.print("s1 : ");
		s1.display();
		System.out.print("s2 : ");
		s2.display();
	}
	
	public void dequeue(){
		if(!(s2.isEmpty())){
			s2.pop();
			System.out.print("s1 : ");
			s1.display();
			System.out.print("s2 : ");
			s2.display();
			return;
		}
		while(!(s1.isEmpty())){
			s2.push(s1.pop());
			System.out.print("s1 : ");
			s1.display();
			System.out.print("s2 : ");
			s2.display();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackAsQueue obj = new StackAsQueue();
		obj.enqueue(new Node(0));
		obj.enqueue(new Node(1));
		obj.enqueue(new Node(2));
		obj.dequeue();
		obj.dequeue();
		obj.enqueue(new Node(4));
		obj.enqueue(new Node(5));
	}

}

package linkedlist;

public class LinkedList {
	private Node start;
	
	public LinkedList() {
		start = null;
	}
	
	public void appendToTail(Node n){
		if(start == null){
			start = n;
		}
		else{
			Node temp = start;
			while(temp.getNext() != null){
				temp = temp.getNext();
			}
			temp.setNext(n);
		}
		display();
	}
	
	public void appendToStart(Node n){
		if(start == null){
			start = n;
		}
		else{
			n.setNext(start);
			start = n;
		}
		display();
	}
	
	public Node deleteNode(int data){
		if(start == null){
			System.out.println("List is empty !!!");
			return null;
		}
		else if(start.getNext() == null){
			Node tmp = start;
			if(tmp.getData() == data){
				start = null;
				display();
				return tmp;
			}
			display();
			return null;
		}
		else{
			Node t = start,n = null,t1;
			if(t.getData() == data){
				start = t.getNext();
				t.setNext(null);
				n = t;
			}
			else{
				while(t.getNext() != null){
					t1 = t.getNext();
					if(t1.getData() == data){
						t.setNext(t1.getNext());
						t1.setNext(null);
						n = t1;
						break;
					}
					t = t.getNext();
				}
			}
			display();
			return n;
		}
	}
	
	public void display(){
		if(start == null){
			System.out.println("List is empty !!!");
		}
		else{
			Node n = start;
			while(n != null){
				System.out.print("-->" + n.getData() + " ");
				n = n.getNext();
			}
			System.out.println("");
		}
	}
	
	public Node getStart(){
		return start;
	}
	
	public void setStart(Node n){
		start = n;
	}
}

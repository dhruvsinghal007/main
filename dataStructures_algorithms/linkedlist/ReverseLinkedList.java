package linkedlist;

public class ReverseLinkedList {
	
	public static void reverseList(LinkedList l){
		Node cNode,pNode,nNode;
		cNode = l.getStart();
		pNode = null;
		nNode = cNode.getNext();
		cNode.setNext(null);
		while(nNode != null){
			pNode = cNode;
			cNode = nNode;
			nNode = nNode.getNext();
			cNode.setNext(pNode);
		}
		l.setStart(cNode);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 0;
		LinkedList l = new LinkedList();
		Node n1 = new Node(i++);
		Node n2 = new Node(i++);
		Node n3 = new Node(i++);
		Node n4 = new Node(i++);
		Node n5 = new Node(i++);
		
		l.appendToTail(n1);
		l.appendToTail(n2);
		l.appendToTail(n3);
		l.appendToTail(n4);
		l.appendToTail(n5);
		
		reverseList(l);
		l.display();
	}

}

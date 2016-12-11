package linkedlist;

public class RemoveDups {

	public static void removeDups(LinkedList list){
		if(list.getStart() == null || list.getStart().getNext() == null){
			list.display();
			return;
		}
		Node t1 = list.getStart();
		Node tail = t1.getNext();
		Node temp = list.getStart().getNext();
		while(temp != null){
			Node t2 = list.getStart();
			while(t2 != temp){
				if(t2.getData() == temp.getData()){
					break;
				}
				t2 = t2.getNext();
			}
			if(t2 == temp){
				tail.setData(temp.getData());
				tail = tail.getNext();
				t1 = t1.getNext();
			}
			temp = temp.getNext();
		}
		t1.setNext(null);
		temp = null;
		
		for(Node t = list.getStart() ; t != null ; t = t.getNext()){
			System.out.print("-->" + t.getData());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList();
		list.appendToTail(new Node(1));
		list.appendToTail(new Node(1));
		list.appendToTail(new Node(2));
		list.appendToTail(new Node(2));
		list.appendToTail(new Node(3));
		list.appendToTail(new Node(3));
		
		removeDups(list);
	}

}

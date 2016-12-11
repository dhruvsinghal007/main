package linkedlist;

public class NumberAdditionPuzzle {

	public static void addNumbers(LinkedList l1,LinkedList l2){
		LinkedList l3=new LinkedList();
		int c1=0,c2=0,count;
		int sum,c=0;
		
		Node t=l1.getStart();
		while(t!=null){
			c1++;
			t=t.getNext();
		}
		count=c1;
		
		t=l2.getStart();
		while(t!=null){
			c2++;
			t=t.getNext();
		}
		if(c2>c1)count=c2;
		count++;
		
		Node nodes[]=new Node[count];
		for(int i=0;i<count;i++){
			nodes[i]=new Node(0);
			l3.appendToTail(nodes[i]);
		}
		
		Node t1=l1.getStart(),t2=l2.getStart(),t3=l3.getStart();
		while(t1!=null&&t2!=null){
			sum=t1.getData()+t2.getData()+c;
			
			if(sum<10){
				c=0;
			}
			else{
				c=sum/10;
				sum%=10;
			}
			System.out.println(sum);
			t3.setData(t3.getData()+sum);
			
			t3=t3.getNext();
			t1=t1.getNext();
			t2=t2.getNext();
		}
		t3.setData(t3.getData()+c);
		l3.display();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList l1=new LinkedList();
		LinkedList l2=new LinkedList();
		Node n1[]=new Node[3];
		Node n2[]=new Node[3];
		for(int i=0;i<3;i++){
			n1[i]=new Node(9);
			n2[i]=new Node(8);
			l1.appendToTail(n1[i]);
			l2.appendToTail(n2[i]);
		}
		
		addNumbers(l1, l2);
	}

}

package stacks;

import linkedlist.Node;

public class SortStack {

	public static void sort(Stack in){
		System.out.print("input : ");
		in.display();
		
		Stack res = new Stack();
		
		while(!(in.isEmpty())){
			if(res.isEmpty() || in.peek().getData() >= res.peek().getData()){
				res.push(in.pop());
			}
			else{
				Node tmp = in.pop();
				while(!(res.isEmpty()) && tmp.getData() < res.peek().getData() ){
					in.push(res.pop());
				}
				res.push(tmp);
			}
		}
		
		System.out.print("result : ");
		res.display();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack stack = new Stack();
		stack.push(new Node(5));
		stack.push(new Node(8));
		stack.push(new Node(3));
		stack.push(new Node(9));
		stack.push(new Node(2));
		
		sort(stack);
	}

}

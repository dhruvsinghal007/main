package stacks;

import java.util.ArrayList;

import linkedlist.LinkedList;
import linkedlist.Node;

public class GrowableStack {

	private ArrayList<LinkedList> list;
	private int max,n;
	private LinkedList l;
	
	public GrowableStack(int max) {
		list = new ArrayList<LinkedList>();
		this.max = max;
		n = 0;
	}
	
	public void push(Node node){
		if(n == max){
			list.add(l);
			l = null;
			n = 0;
		}
		if(n == 0){
			l = new LinkedList();
		}
		l.appendToStart(node);
		n++;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrowableStack stack = new GrowableStack(5);
		for(int i = 0 ; i < 6 ; i++){
			stack.push(new Node(i));
		}
	}

}

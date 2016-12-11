package stacks;

public class TripleStackArray {
	private int[] arr;
	private int t1,t2,t3;
	
	public TripleStackArray(int a) {
		// TODO Auto-generated constructor stub
		arr=new int[a];
		for(int i=0;i<arr.length;i++){
			arr[i]=-1;
		}
		t1=0;
		t2=1+(a-1)/3;
		t3=1+2*(a-1)/3;
	}
	
	public String toString(){
		String st="[";
		for(int i=0;i<arr.length-1;i++){
			st+=arr[i];
			st+=",";
		}
		st+=arr[arr.length-1];
		st+="]";
		return st;
	}
	
	public void pushInStackNumber(int st,int ele){
		if(st==1){
			if(t1==(1+(arr.length-1)/3)){
				System.out.println("Stack number 1 overflow!!!");
			}
			else{
				arr[t1]=ele;
				t1++;
			}
		}
		else if(st==2){
			if(t2==(1+2*(arr.length-1)/3)){
				System.out.println("Stack number 2 overflow!!!");
			}
			else{
				arr[t2]=ele;
				t2++;
			}
		}
		else{
			if(t3==arr.length-1){
				System.out.println("Stack number 3 overflow!!!");
			}
			else{
				arr[t3]=ele;
				t3++;
			}
		}
	}
	
	public int popFromStackNumber(int st){
		int ele=-1;     //negative number implies empty stack
		
		if(st==1){
			if(t1<0){
				System.out.println("Stack number 1 underflow!!!");
			}
			else{
				ele=arr[t1];
				arr[t1]=-1;
				t1--;
			}
		}
		else if(st==2){
			if(t2<(arr.length-1)/3){
				System.out.println("Stack number 2 underflow!!!");
			}
			else{
				ele=arr[t2];
				arr[t2]=-1;
				t2--;
			}
		}
		else{
			if(t3==arr.length-1){
				System.out.println("Stack number 3 underflow!!!");
			}
			else{
				ele=arr[t3];
				arr[t3]=-1;
				t3--;
			}
		}
		return ele;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TripleStackArray ts=new TripleStackArray(10);
		
		ts.pushInStackNumber(1, 10);
		System.out.println(ts);
		
		ts.pushInStackNumber(2, 20);
		System.out.println(ts);

		ts.pushInStackNumber(2, 10);
		System.out.println(ts);
		
		ts.pushInStackNumber(3, 50);
		System.out.println(ts);
		
		ts.popFromStackNumber(1);
		System.out.println(ts);
		
		ts.popFromStackNumber(1);
		System.out.println(ts);
		
		ts.popFromStackNumber(1);
		System.out.println(ts);
	}
}

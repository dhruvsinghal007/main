package sorting;

public class CountSort {
	
	public int[] sort(int[] a){
		int[] b = new int[a.length];
		int[] c = new int[256];
		
		for(int i = 0 ; i < a.length ; i++){
			c[a[i]]++;
		}
		
		for(int i = 1 ; i < c.length ; i++){
			c[i] += c[i-1];
		}
		
		for(int i = 0 ; i < a.length ; i++){
			c[a[i]]--;
			b[c[a[i]]] = a[i];
		}
		return b;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {4,7,2,3,1,9,8,5};
		int[] res = new CountSort().sort(arr);
		for(int i = 0 ; i < res.length ; i++){
			System.out.print(res[i] + " ");
		}
	}
}

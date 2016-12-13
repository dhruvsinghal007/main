package sorting;

public class MergeSort {

	public void merge(int[] arr, int low1, int high1, int low2, int high2){
		int[] temp = new int[high2+1];
		int c = low1, a = low1, b = low2;
		while(a <= high1 && b <= high2){
			if(arr[a] <= arr[b]){
				temp[c] = arr[a];
				a++;
			}
			else{
				temp[c] = arr[b];
				b++;
			}
			c++;
		}
		if(a <= high1){
			while(a <= high1){
				temp[c] = arr[a];
				c++;
				a++;
			}
		}
		if(b <= high2){
			while(b <= high2){
				temp[c] = arr[b];
				c++;
				b++;
			}
		}
		
		for(int i = low1 ; i <= high2 ; i++){
			arr[i] = temp[i];
		}
	}
	
	public void sort(int[] arr, int low, int high){
		if(low < high){
			int mid = (low + high) / 2;
			sort(arr, low, mid);
			sort(arr, mid + 1, high);
			merge(arr,low, mid, mid + 1, high);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] {4,7,2,3,1,9,8,5};
		new MergeSort().sort(arr, 0, arr.length - 1);
		for(int i = 0 ; i < arr.length ; i++){
			System.out.print(arr[i] + " ");
		}
	}
}

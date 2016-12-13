package sorting;

public class QuickSort {

	public void sort(int[] arr, int low, int high){
		if(low < high){
			int pivot = findPivot(arr, low, high);
			sort(arr, low, pivot-1);
			sort(arr, pivot+1, high);
		}
	}
	
	private int findPivot(int[] arr, int low, int high) {
		int left = low, right = high+1;
		int pivot = low;
		while(left < right){
			do{
				left++;
			}while(arr[left] < arr[pivot]);
			
			do{
				right--;
			}while(arr[right] > arr[pivot]);
			
			if(left <= right){
				arr[left] += arr[right];
				arr[right] = arr[left] - arr[right];
				arr[left] -= arr[right];
			}
		}
		arr[pivot] += arr[right];
		arr[right] = arr[pivot] - arr[right];
		arr[pivot] -= arr[right];
		
		return right;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] {4,7,2,3,1,9,8,5};
		new QuickSort().sort(arr, 0, arr.length - 1);
		for(int i = 0 ; i < arr.length ; i++){
			System.out.print(arr[i] + " ");
		}
	}
}

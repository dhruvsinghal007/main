package sorting;

public class HeapSort {

	public int getParent(int i){
		return (i-1)/2;
	}
	
	public int getLeft(int i){
		return (i*2)+1;
	}
	
	public int getRight(int i){
		return (i+1)*2;
	}
	
	public void maxHeapify(int[] arr, int i, int n){
		int left = getLeft(i);
		int right = getRight(i);
		int maxIndex = i;
		
		if(left < n && arr[i] < arr[left]){
			maxIndex = left;
		}
		
		if(right < n && arr[maxIndex] < arr[right]){
			maxIndex = right;
		}
		
		if(maxIndex != i){
			arr[maxIndex] += arr[i];
			arr[i] = arr[maxIndex] - arr[i];
			arr[maxIndex] -= arr[i];

			maxHeapify(arr, maxIndex, n);
		}
	}
	
	public void buildMaxHeap(int[] arr){
		for(int i = (arr.length-1)/2 ; i >= 0 ; i--){
			maxHeapify(arr, i, arr.length);
		}
	}
	
	public void sort(int[] arr){
		buildMaxHeap(arr);
		for(int i = arr.length-1 ; i > 0 ; i--){
			arr[i] += arr[0];
			arr[0] = arr[i] - arr[0];
			arr[i] -= arr[0];
			maxHeapify(arr, 0, i);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] {4,7,2,3,1,9,8,5};
		new HeapSort().sort(arr);
		for(int i = 0 ; i < arr.length ; i++){
			System.out.print(arr[i] + " ");
		}
	}

}

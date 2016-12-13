package sorting;

public class InsertionSort {

	public static void sort(int[] arr){
		for(int i = 1 ; i < arr.length ; i++){
			int j = i;
			while(j > 0){
				if(arr[j-1] > arr[j]){
					arr[j] += arr[j-1];
					arr[j-1] = arr[j] - arr[j-1];
					arr[j] -= arr[j-1];
				}
				j--;
			}
		}
		System.out.print("Sorted : ");
		for(int i = 0 ; i < arr.length ; i++){
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sort(new int[] {6,3,8,1,5,9,2,7});
	}

}

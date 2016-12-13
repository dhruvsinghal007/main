package sorting;

public class SelectionSort {

	public static void sort(int[] arr){
		for(int i = 0 ; i < arr.length - 1 ; i++){
			for(int j = i + 1 ; j < arr.length ; j++){
				if(arr[i] > arr[j]){
					arr[i] += arr[j];
					arr[j] = arr[i] - arr[j];
					arr[i] -= arr[j];
				}
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

package sorting;

public class BubbleSort {

	public static void sort(int[] arr){
		for(int i = 0 ; i < arr.length - 1 ; i++){
			for(int j = 0 ; j < arr.length - i - 1 ; j++){
				if(arr[j] > arr[j+1]){
					arr[j] += arr[j+1];
					arr[j+1] = arr[j] - arr[j+1];
					arr[j] -= arr[j+1];
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

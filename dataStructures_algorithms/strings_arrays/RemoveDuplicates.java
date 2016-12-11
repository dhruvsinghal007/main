package strings_arrays;

public class RemoveDuplicates {
	public static void removeDuplicates(char[] arr){
		if(arr == null || arr.length == 1){
			System.out.println(arr);
		}
		else{
			int tail = 1;
			for(int i = 1 ; i < arr.length ; i++){
				int j;
				for(j = 0 ; j < i ; j++){
					if(arr[j] == arr[i]){
						break;
					}
				}
				if(j == i){
					arr[tail++] = arr[i];
				}
			}
			arr[tail] = '\0';
			for(int i = 0 ; arr[i] != 0 ; i++){
				System.out.print(arr[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		removeDuplicates("ddhhrruuvv".toCharArray());
	}
}

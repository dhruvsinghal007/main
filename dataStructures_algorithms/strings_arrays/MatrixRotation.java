package strings_arrays;

public class MatrixRotation {

	static int[][] rotate(int[][] matrix, int n){
		
		for(int i = 0 ; i < (n/2) ; i++){
			for(int j = i ; j < n-1-i ; j++){
				int temp = matrix[i][j+i];
				
				matrix[j+i][n-1-i] += temp;
				temp = matrix[j+i][n-1-i] - temp;
				matrix[j+i][n-1-i] -= temp;
				
				matrix[n-1-i][n-1-j-i] += temp;
				temp = matrix[n-1-i][n-1-j-i] - temp;
				matrix[n-1-i][n-1-j-i] -= temp;
				
				matrix[n-1-j-i][i] += temp;
				temp = matrix[n-1-j-i][i] - temp;
				matrix[n-1-j-i][i] -= temp;
				
				matrix[i][j+i] = temp;
			}
		}
		
		return matrix;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] in = {{1,2},{3,4}};
		int[][] result = rotate(in, 2);
		for(int i = 0 ; i < result.length ; i++){
			for(int j = 0 ; j < result[i].length ; j++){
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}

}

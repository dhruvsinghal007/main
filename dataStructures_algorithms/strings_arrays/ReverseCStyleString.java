package strings_arrays;

public class ReverseCStyleString {
	public static String reverse(String input){
		//following code to make c-style string to adhere to the problem.
		char[] in = input.toCharArray();
		
		char[] input_char = new char[in.length + 1];
		
		for(int i = 0 ; i < in.length ; i++){
			input_char[i] = in[i];
		}
		input_char[in.length] = 0;
		//System.out.println(input_char);
		
		//following code for reversing the processed string (char array with null value)
		int len = input_char.length - 1;
		for(int i = 0 ; i < (len/2) ; i++){
			input_char[i] = (char) (input_char[i] + input_char[len - 1 - i]);
			input_char[len - 1 - i] = (char) (input_char[i] - input_char[len - 1 - i]);
			input_char[i] = (char) (input_char[i] - input_char[len - 1 - i]);
		}
		//System.out.println(input_char);
		
		return new String(input_char);
	}
	
	public static void main(String[] args) {
		System.out.println(reverse("Dhruv"));
	}
}

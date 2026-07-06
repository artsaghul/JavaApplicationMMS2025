public class MULtiDimentionalArray1{
	public static void main(String[] args){
		int[][] numbers={
			{7,5,6,7,5,4},
			{9,3,7,4,2,3},
			{3,2,5,4,7,8}
		};
		System.out.println("The element of the array are:");
		for(int row = 0; row < 3; row++){
			for(int col = 0; col <5; col++){
				System.out.printf("%s%n",numbers[row][col] + "");
			}
		}
	}
}
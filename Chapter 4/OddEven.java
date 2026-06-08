public class OddEven {
    public static void main(String[] args) {
        int value = 7; // you can change this number

        switch (value % 2) {
            case 0:
                System.out.println("Even integer");
                break;
            case 1:
                System.out.println("Odd integer");
                break;
        }
    }
}

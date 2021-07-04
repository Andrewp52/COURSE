package homework2;

public class Test {
    static final int SIZE = 4;

    public static void main(String[] args) {
        String[][] arr = new String[][]{
                {"1", "2", "3", "4"},
                {"4", "5", "6", "7"},
                {"7", "8", "9", "10"},
                {"10", "11", "12", "13"}
        };

        try {
            System.out.println("Sum = " + sumArray(arr));
        } catch (MyArrayDataException e){
            System.out.println(e.getMessage());
            System.out.println("Cause : " + e.getCause());
        } catch (MyArraySizeException e){
            System.out.println(e.getMessage());
        }

    }

    private static int sumArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if(arr.length != SIZE){
            throw new MyArraySizeException("Invalid outer array size : " + arr.length);
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != SIZE){
                throw new MyArraySizeException(
                        String.format("Invalid inner array size : %d at index : %d", arr[i].length, i)
                );
            }
            for (int j = 0; j < arr.length; j++) {
                try {
                    res += Integer.parseInt(arr[i][j]);
                } catch (Exception e){
                    throw new MyArrayDataException(String.format("Invalid data in the cell [%d][%d]", i, j), e);
                }
            }
        }
        return res;
    }
}

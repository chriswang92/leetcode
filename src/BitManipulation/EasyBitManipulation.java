package BitManipulation;

public class EasyBitManipulation {
    public static void main(String[] args) {
        int x = getSum(1,Integer.MAX_VALUE);
    }
    //371. Sum of Two Integers
    public static int getSum(int a, int b) {

        if (a == Integer.MAX_VALUE)
            return a;

        if (b == Integer.MAX_VALUE)
            return b;
        while (b != 0)
        {
            // carry now contains common
            // set bits of x and y
            int carry = a & b;

            // Sum of bits of x and
            // y where at least one
            // of the bits is not set
            a = a ^ b;

            // Carry is shifted by
            // one so that adding it
            // to x gives the required sum
            b = carry << 1;
        }
        return a;
    }

}

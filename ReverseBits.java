public class ReverseBits {
     public int reverseBits(int n) {
        int rev=0;
        for(int pos=0;pos<32;pos++){
            rev <<= 1;
 
            // if current bit is '1'
            if ((int)(n & 1) == 1)
                rev ^= 1;
 
            // bitwise right shift
            //'n' by 1
            n >>= 1;
        }
        return rev;
    }
}

public class SortingColours {
    public void sortColors(int[] nums) {
        int[] map=new int[3];
        for(int k:nums){
            map[k]++;
        }
        int i=0;
        for(int k=0;k<3;k++){
            while(map[k]>0){
                nums[i]=k;
                map[k]--;
                i++;
            }
        }
    }
} {
    
}

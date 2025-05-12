public class SearchIn2DMatrix {
public boolean searchMatrix(int[][] matrix, int target) {
        int rows=matrix.length;
        int cols=matrix[0].length;
        int lo=0,hi=rows*cols-1;
        while(lo<=hi){
            int mid_1d=lo+(hi-lo)/2;
            int mid_2d=matrix[mid_1d/cols][mid_1d%cols];
            if(mid_2d==target)
            return true;
            else if(mid_2d<target)
            lo=mid_1d+1;
            else
            hi=mid_1d-1;
        }
        return false;
    }
}

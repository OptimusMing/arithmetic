package cn.mml;

public class process {

    public static void getMax(int[] intarr){
        System.out.println(processdemo(intarr,0,intarr.length-1));
    }

    //求数组L到R的最大值
    public static int processdemo(int[] intarr,int L,int R){
        if(L==R){return intarr[L];}
        //求中位数
        int mid = L + ((R-L)>>1);//**注意括号运算顺序
        int maxL = processdemo(intarr,L,mid);
        int maxR = processdemo(intarr,mid+1,R);
        return Math.max(maxL,maxR);
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,8,4,11,6,3,9,30,21,77,100};
        //int[] arr = {1,3,2,8};
        getMax(arr);
    }
}

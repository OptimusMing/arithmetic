package cn.mml.sort;

public class radixSort {

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,9,3,1};
        //int[] arr = {5,2};
        radixSort(arr);
        for(int x=0;x<arr.length;x++){
            System.out.println(arr[x]);
        }
    }

    public static void radixSort(int[] arr){
        if(arr==null|| arr.length<2){
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }
    public static int maxbits(int[] arr){
        int max = Integer.MIN_VALUE; //0x888888888
        for(int i=0 ;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }
        int res =0;
        while(max!=0){
            res++;
            max /=10;
        }
        return res;
    }
    //digit表示这个数组中最大的值有几个十进制位
    public static void radixSort(int[] arr,int L,int R,int digit){
        final int radix = 10;//以10 为基底
        int i=0,j=0;
        //有多少个数准备多少个备份空间
        int[] bucket =  new int[R-L+1];
        //有多少位进出桶就是多少次
        for(int d=1;d<=digit;d++){
            int[] count = new int[radix];
            for(i=L;i<=R;i++){
                j = getDigit(arr[i],d);
                count[j]++;
            }
            for(i=1;i<radix;i++){
                count[i] = count[i]+count[i-1];
            }
            for(i=R;i>=L;i--){
                j=getDigit(arr[i],d);
                bucket[count[j]-1] = arr[i];
                count[j]--;
            }
            for(i=L,j=0;i<=R;i++,j++){
                arr[i] = bucket[j];
            }
        }
    }

    public static int getDigit(int x ,int d){
        return ((x/((int)Math.pow(10,d-1)))%10);
    }
}

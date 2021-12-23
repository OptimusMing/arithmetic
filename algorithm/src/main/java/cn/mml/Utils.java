package cn.mml;

public class Utils {

    public static void swap(int[]  arr,int i ,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /*
    交换i位置和j位置的值
     */
    public static void swap_bit(int[]  arr,int i ,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /*
    遍历数组并打印
     */
    public static void ergodicInt( int[] arr){
        for(int x=0;x<arr.length;x++){
            System.out.print(arr[x]);
        }
    }
    public static void ergodicInteger( Integer[] arr){
        for(int x=0;x<arr.length;x++){
            System.out.print(arr[x]);
            System.out.print(",");
        }
    }


}

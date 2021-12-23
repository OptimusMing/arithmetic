package cn.mml.sort;

//快速排序
public class qukeSort {

    public static void main(String[] args) {

        int[] arr = {1,3,5,2,9,3,1,4,7,0};
        //int[] arr = {5,2};
        quickSort(arr,0,arr.length-1);
        for(int x=0;x<arr.length;x++){
            System.out.println(arr[x]);
        }
    }


    public static void quickSort(int[] arr,int L,int R){

        if(L < R){
            swapS(arr,L+(int)(Math.random()*(R-L+1)),R);
            int[] p = partition(arr,L,R);
            quickSort(arr,L,p[0]-1);
            quickSort(arr,p[1]+1,R);
        }
    }

    public static int[] partition(int[] arr,int L ,int R){

        int less = L-1; // < 区右边界
        int more = R; // > 区左边界

        while(L<more){
            if(arr[L] < arr[R]){  //当前数小于划分值
                swapS(arr,++less,L++);
            }else if(arr[L] > arr[R]){ //当前值大于划分值
                swapS(arr,--more,L);
            }else{
                L++;
            }
        }
        swapS(arr,more,R);
        return new int[] {less+1,more};//返回左边界和右边界
    }

    public static void swapS(int[]  arr,int i ,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

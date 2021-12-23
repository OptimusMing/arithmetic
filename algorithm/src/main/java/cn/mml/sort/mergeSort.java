package cn.mml.sort;

/*
归并排序
 */
public class mergeSort {

    public static  void process(int[] intArr,int L,int R){
        if(L==R){
            return;
        }
        int M = L + ((R-L)>>1);
        //左边排序
        process(intArr,L,M);
        //右边排序
        process(intArr,M+1,R);
        //将左右归并在一起
        mager(intArr,L,M,R);
    }
    public static void mager(int[] arr,int L,int M ,int R){
        int[] help =new int[R-L+1];
        int i =0;
        int p1 =L;
        int p2 = M+1;
        //都没有越界时
        while(p1<=M && p2<=R){
            help[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        //其中一个越界
        while(p1 <= M){
            help[i++] = arr[p1++];
        }
        while(p2 <= R){
            help[i++] = arr[p2++];
        }
        for(int j=0;j<help.length;j++){
            arr[L+j] = help[j];
        }
    }

    public static void main(String[] args) {
        //int[] arr = {1,3,2,8,4,11,6,3,9,30,21,77,100};
        //int[] arr = {1,3,4,2,5};
        int[] arr = {2,5,1};
//        process(arr,0,arr.length-1);
//        for(int x=0;x<arr.length;x++){
//            System.out.println(arr[x]);
//        }smallSum
        System.out.println(processSum(arr,0,arr.length-1));
        System.out.println(smallSum(arr));
    }
    /*
    求小和
     */
    public static int processSum(int[] arr,int L,int R){
        if(L==R){
            return 0;
        }
        int M = L +((R-L)>>1);
        //不仅排序还要加小和
        processSum(arr,L,M);
        processSum(arr,(M+1),R);
        mergerSum(arr,L,M,R);
        return processSum(arr,L,M)
                +processSum(arr,(M+1),R)
                +mergerSum(arr,L,M,R);
    }
    public static int mergerSum(int[] arr,int L,int M,int R){
        int[] help =new int[R-L+1];
        int ret =0;
        int i =0;
        int p1 =L;
        int p2 =M+1;
        //没有越界，先求和然后排序123
        while( p1<= M && p2<=R){
           ret += arr[p1] < arr[p2] ? (R-p2+1) * arr[p1] : 0;
           help[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        //其中一个越界
        while(p1 <= M){
            help[i++] = arr[p1++];
        }
        while(p2 <= R){
            help[i++] = arr[p2++];
        }
        for(int j=0;j<help.length;j++){
            arr[L+j] = help[j];
        }
        return ret;
    }



    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int res = 0;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }
    public static void main1 (String[] args) throws java.lang.Exception
    {
        int[] arr = { 1,2,3};
        System.out.println(smallSum(arr));
    }

}

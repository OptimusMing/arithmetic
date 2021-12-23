package cn.mml.sort;


//堆排序
public class heapSort {




    //heapInsert 过程-->某个数出现在index位置，往上继续运动  往上
    public static void heapInsert(int[] arr,int index){
        while(arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }


    //堆化-->某数在index位置能否往下移动   往下
    public static void heapify(int[] arr,int index,int heapSize){
        int left = index*2+1;  //左孩子的下标
        while(left < heapSize){ //下面还有孩子

            //两个孩子中谁的值大，把下标给lagest
            int lagest = left+1<heapSize && arr[left]<arr[left+1]?left+1:left;
            //父亲和最大孩子的值谁大谁把标给lagest
            lagest =  arr[index] > arr[lagest] ?index:lagest;

            if(lagest==index){
                break;
            }
            swap(arr,lagest,index);
            index = lagest;
            left = index*2+1;
        }

    }

    public static void swap(int[]  arr,int i ,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

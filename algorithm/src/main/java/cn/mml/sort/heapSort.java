package cn.mml.sort;


import cn.mml.Utils;

import java.util.PriorityQueue;

//堆排序
/*
先让数组变成一个大根堆

 */
public class heapSort {


    public static void main(String[] args) {
        //int[] arr = {2,5,1};
//        int[] arr = {1,3,2,8,4,11,6,3,9,30,21,77,100};
//        heapSort(arr);
//        for(int x=0;x<arr.length;x++){
//            System.out.println(arr[x]);
//        }
        //samllheap();
        int[] arr = {2,5,1,6,3};
        sortedArrdistanceLessK(arr, 3);
        Utils.ergodicInt(arr);
    }

    public static void sortedArrdistanceLessK(int[] arr,int k){
        //默认小根堆--》优先级队列
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        //先将K个数据放入到小根堆里
        int index =0;
        int minK =  Math.min(arr.length-1,k);
        for(;index <= minK;index++){
            heap.add(arr[index]);
        }
        int i= 0;
        for(;index <arr.length;i++,index++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while(!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
    }

    /*
    小根堆 与优先级队列
     */

    public static void samllheap(){
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        heap.add(8);
        heap.add(4);
        heap.add(10);
        heap.add(1);
        while(!heap.isEmpty()){
            System.out.println(heap.poll());
        }
    }

    public static void heapSort(int[] arr){

        if(arr== null || arr.length<2){
            return;
        }
//        for(int i=0;i<arr.length;i++){//循环数组，依次将数据弄如大根堆
//            heapInsert(arr,i);
//        }
        for(int i=arr.length-1;i>=0;i++){//同上面注掉的for循环一样都是将数组弄成大根堆
            heapify(arr,i,arr.length);
        }
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while(heapSize>0){
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }
    }


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
            //swap(arr,lagest,index);
            Utils.swap(arr,lagest,index);
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

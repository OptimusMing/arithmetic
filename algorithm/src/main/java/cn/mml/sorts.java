package cn.mml;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sorts {

    @Value("angecodeStrarr")
    private static String[] strArr;

    public static void main(String[] args) {}
    /*
    选择排序
     */
    public static void selectsort(int[] arr){
        if(arr==null|| arr.length<2){return;}

        for(int i=0;i<arr.length-1;i++){
            int minXb = i;
            for(int j=i+1;j<arr.length;j++){
                minXb =  arr[j] < arr[minXb]?j:minXb;
            }
            swapS(arr,i,minXb);
        }
    }
    public static void swapS(int[]  arr,int i ,int j){
        int tmp = arr[i];
         arr[i] = arr[j];
         arr[j] = tmp;
    }
    /*
    插入
     */
    public static void insertSort(int[] arr){

        if(arr== null || arr.length<1){
            return;
        }
        //第一层for循环保证从0-i 有序
        for(int i =1;i<arr.length;i++){
            for(int j =i-1;j>=0 && arr[j]>arr[j+1];j-- ){
                swap(arr,j,j+1);
            }
        }

    }
    /*
    冒泡
     */
    public static void bubble( int[] arr){
        if(arr==null|| arr.length<2){
            return;
        }
        for(int i = arr.length -1;i>0;i--){    //第几轮
            for(int j=0;j<i;j++){              //每轮每个元素的比较
                if(arr[j]>arr[j+1]){
                    swap(arr,arr[i],arr[j]);
                }
            }
        }
    }
    /*
    交换i位置和j位置的值
     */
    public static void swap(int[]  arr,int i ,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void test2(int[] arr){
        int eor =0,eor1=0;
        for(int i : arr){
            eor ^= i;
        }
        //此时eor = a^b ,并且不等于0 ，那么必有一个位置上的数字是1
        //获取最右边为1的数（取反加一在与上自己就是找到自己最右边为一的那个数字）
    /*
    比如说： a = 1100101011
    那么   ~a = 0011010100
    加一    为   0011010101
        a&(~a+1)0000000001
    */
        int reightOne = eor & (~eor+1);
        for(int i : arr){
            if((i&reightOne)==1){//这里就是获取其中某个位置为1 的数的异或值
                eor1 ^= i;
            }
        }
        //这里获取的eor2就是ab其中的一个值
        //那么eor^eor2  就是ab其中的另一个
        System.out.println(eor1);
        System.out.println(eor1^eor);
    }
}

package cn.mml.sort;

import cn.mml.Utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class ComparatorSort {

    public static class student{
        int id;
        String name;
        int age;
    }
    public static class IdAscendingComparator implements Comparator<student>{
        @Override
        public int compare(student o1, student o2) {
            return o1.id-o2.id;
        }
    }




    public static class AComparator implements Comparator<Integer>{
        //返回负数，第一个参数排前面
        //返回正数，第二个参数排前面
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }
    public static class BComparator implements Comparator<Integer>{
        //返回负数，第一个参数排前面
        //返回正数，第二个参数排前面
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }



    public static void main(String[] args) {
        Integer[] arr =  {1,3,2,5,9,4,1,6,8};

        Arrays.sort(arr,new AComparator());
        Utils.ergodicInteger(arr);
        System.out.println("=============================");
        Arrays.sort(arr,new BComparator());
        Utils.ergodicInteger(arr);
    }
}

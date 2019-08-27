package client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @description:
 * @author: daixiongkun
 * @time: 2019-08-23 16:20
 */
public class Arrary {
    public static void main(String[] args) {

    int arr[] = getArr();
    int arr1[] = removeArr(arr);
        for (int a:arr1
             ) {
            System.out.println(a);
        }

        try {
            BufferedWriter buff = new BufferedWriter(new FileWriter("number.text"));
            for (int i:arr
                 ) {
                buff.write(i+"  ");
            }
            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] getArr(){
        int[] arr = new int[10];
        System.out.println("请输入10个整数：");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    public static int[] removeArr(int[] arr){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 10){
                list.add(arr[i]);
            }
        }
        int [] remove = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            remove[i] = list.get(i);
        }
        return remove;
    }
}

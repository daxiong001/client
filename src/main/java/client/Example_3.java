package client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: daixiongkun
 * @time: 2019-08-27 11:09
 */
public class Example_3 {


    public static int[] getArray(){
        int [] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
          int b = (int) (Math.random()*100+1);
          arr[i] = b;
        }
        return arr;
    }



    public static void main(String[] args) throws Exception{
        int[] array = getArray();
        List<Integer> list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        BufferedWriter writer = new BufferedWriter(new FileWriter("arr.txt"));
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 10){
                list.add(array[i]);
            }else {
                map.put(i, array[i]);
            }
            writer.write(array[i]+" ");
        }
        System.out.println(list);
        System.out.println(map);
        writer.close();
    }
}

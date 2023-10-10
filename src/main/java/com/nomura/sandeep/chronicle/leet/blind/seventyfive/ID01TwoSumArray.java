package com.nomura.sandeep.chronicle.leet.blind.seventyfive;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// arrays use hashmaps
public class ID01TwoSumArray {
    public int[] Solution (int[] array, int sum){
        if (array == null || array.length == 0){
            return new int[]{-1,-1};
        }
        // value, index
        Map<Integer, Integer> map  = new HashMap<>();
        for (int i = 0; i < array.length; i++){
            map.put(array[i],i);
        }

        for (int i = 0; i < array.length; i++){
            int remaining = sum - array[i];
            if (map.containsKey(remaining)){
                int[] ans = new int[2];
                ans[0] = i ;
                ans[1] = map.get(remaining);
                return ans;
            }
        }
        // case when we don't find the answer.
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        ID01TwoSumArray sol  = new ID01TwoSumArray();

        print(sol.Solution(new int[]{2,11,15,7},9));
        print(sol.Solution(new int[]{2,7,15,11},9));
        print(sol.Solution(new int[]{2,7,7,11},9));
        print(sol.Solution(new int[]{2,-7,8,11},-5));
        print(sol.Solution(new int[]{2,-7,8,11},-50));
    }

    public  static void print(int[] answer){
        System.out.println(answer[0] + "," + answer[1]);
    }
}

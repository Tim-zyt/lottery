package com.sf.lottery.web.utils;

/**
 * Created by 01139954 on 2016/12/3.
 */
public class RandomUtil {
    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n 随机数个数
     */
    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    public static void main(String args[]){
        for(int j = 0 ; j < 20 ; j++){
            int[] ran = randomCommon(0,10,3);
            for(int i = 0 ; i < ran.length ; i++){
                System.out.print(ran[i] + "," );
            }
            System.out.println("");
        }


    }
}

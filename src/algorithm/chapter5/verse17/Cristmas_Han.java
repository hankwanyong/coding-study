package algorithm.chapter5.verse17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cristmas_Han {

    public static void main(String... args) {
//        int goods_cnt1 = 6;     // 선물상자 수
        int child1 = 4;         // 어린이 수
        int[] goods1Arr = {1,2,3,4,5,6};    // 선물상자의 선물수 배열

//        int goods_cnt2 = 4;     // 선물상자 수
        int child2 = 1;         // 어린이 수
        int[] goods2Arr = {1,2,3,4};        // 선물상자의 선물수 배열

        System.out.println("1-1 : " + test1(child1, goods1Arr));
        System.out.println("2-1 : " + test1(child2, goods2Arr));

        /**
         * 2번 문제 의미를 모르곘음.
         * 2-2 경우
         * 1,234
         * 12,34
         * 123,4
         * 2,34
         * 23,4
         * 3,4
         *
         * >> 6가지 경우? 답은 4
         */

    }

    /**
     * 어린이 수에 대한 부분합
     * @ex 어린이 4명 > 부분합배열 % 4
     * @param child
     * @param goodsArr
     * @return
     */
    public static List<Integer> getDsum(int child, int[] goodsArr) {
        List<Integer> ret = new ArrayList<>();
        for(int i=0; i< goodsArr.length; i++) {
            if(i == 0) ret.add(goodsArr[i] % child);
            else  ret.add((ret.get(i-1) + goodsArr[i]) % child);
        }
//        System.out.println("dSum : " + ret);
        return ret;
    }

    public static int test1(int child, int[] goodsArr){
        int ret = 0;

        if(child == 0) return ret;

        List<Integer> dSum = getDsum(child, goodsArr);

        // stream 이용해서 할수없나?
//        ret = dSum.stream().filter(i -> Collections.frequency(dSum, i) > 1)
//                .collect(Collectors.toSet())
//                .size();

        for(int i=0; i<dSum.size(); i++) {
            int dSumCutInt = dSum.get(i);

            for(int j=i+1; j<dSum.size(); j++) {
                if(dSumCutInt == dSum.get(j)) ret++;
            }

            // 상자의 선물수가 어린이 수의 배수인경우
            if(dSumCutInt == 0) ret++;
        }

        return ret;
    }

}

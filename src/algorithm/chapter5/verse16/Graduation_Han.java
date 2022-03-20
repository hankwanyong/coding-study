package algorithm.chapter5.verse16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graduation_Han {

    public static void main(String[] args) {
        // 예제.1
        int[] ex1 = {4, 4, 4, 4};
        int[][] subjectArr1 = {
                {}
                , {0}
                , {0,1,3}
                , {}
        };
        int[][] semesterArr1 = {
                {0,1,2,3}
                , {0,1,2,3}
                , {0,1,3}
                , {0,1,2,3}
        };

        // 예제.2
        int[] ex2 = {4,2,2,4};
        int[][] subjectArr2 = {
                {1}
                , {}
                , {3}
                , {2}
        };
        int[][] semesterArr2 = {
                {0,2,3}
                , {1,2,3}
        };


        System.out.println("test1 : " + chgRet(test(ex1, subjectArr1, semesterArr1)));
        System.out.println("test2 : " + chgRet(test(ex2, subjectArr2, semesterArr2)));

    }

    /**
     *
     * @param ex            : 학교 수강 정보
     * @param subjectArr    : 과목 정보
     * @param semesterArr   : 학기 정보
     * @return
     */
    static int test(int[] ex, int[][] subjectArr, int[][] semesterArr){

//        String ret = "IMPOSSIBLE";
        int ret = 0;

        int subject_cnt = ex[0];      // 전공 과목수
        int cp_subject = ex[1];         // 필수 과목수
        int semester_cnt = ex[2];       // 학기수
        int limit_cnt = ex[3];          // 한 학기 수강제한 수

        if(0 == cp_subject) return ret;     // 들어야할 과목 없으면 return;

        List<Integer> takenList = new ArrayList<>();

        int retInt = 0;
        for(int[] semester : semesterArr) {
            retInt++;

            List<Integer> psbSub = new ArrayList<>();

            for(int i=0; i<subjectArr.length; i++) {
                int subInt = i;     // 과목번호
                int[] subject = subjectArr[subInt];     // 과목의 먼저 들어야할 과목 정보
                if(!takenList.contains(i) && Arrays.stream(semester).anyMatch(s -> s == subInt)) {
                    // 수강하지 않은 과목 + 이번 학기에 개강한 과목

                    if(subject.length == 0) {
                        // 수강조건이 없는 과목 >> 수강
                        psbSub.add(subInt);
                    }else {
                        boolean psb = true;
                        for (int k : subject) {
                            if (!takenList.contains(k)) {
                                psb = false;
                                break;
                            }
                        }
                        if(psb) {
                            psbSub.add(subInt);
                        }
                    }

                }

            }

            takenList.addAll(psbSub);

//            System.out.println("taken_sub >> \n" + takenList);

            if(takenList.size() == cp_subject) {
                ret = retInt;
                break;
            }
        }






        return ret;
    }

    static String chgRet (int a) {
        return a == 0 ? "IMPOSSIBLE" : a+"";
    }
}

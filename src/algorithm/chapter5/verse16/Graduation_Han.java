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


//        boolean[] signUpSbjctArr = new boolean[4];
//        for(boolean z : signUpSbjctArr) {
//            System.out.println("zzz : " + z);
//        }

//        System.out.println("test1 : " + chgRet(test2(ex1, subjectArr1, semesterArr1)));
        System.out.println("test2 : " + chgRet(test2(ex2, subjectArr2, semesterArr2)));

        System.out.println((1 & 1<<0) );
        System.out.println((11 & 1<<0) );
        System.out.println((13 & 1<<0) );
        System.out.println((12 & 1<<0) );
        System.out.println((14 & 1<<0) );


    }

    /**
     * 비트마스크 변환
     * @param param
     * @return
     */
    public static int getBitmask(int[] param) {
        int ret = 0;
        for(int i : param) {
            ret += 1<<i;
        }
        return ret;
    }

    /**
     *
     * @param ex            : 학교 수강 정보
     * @param subjectArr    : 과목 정보
     * @param semesterArr   : 학기 정보
     * @return
     */
    static int test2(int[] ex, int[][] subjectArr, int[][] semesterArr){

        int ret = 0;

        int[] sbjctBm = new int[ex[0]];        // 과목 비트마스크
        int[] smstrBm = new int[ex[2]];        // 학기 비트마스크
        boolean[] signUpSbjctArr = new boolean[ex[0]];        // 수강한 과목


        // 과목 설정
        for(int index1=0; index1 < ex[0]; index1++) {
            sbjctBm[index1] = getBitmask(subjectArr[index1]);
        }

        // 학기 설정
        for(int index2=0; index2 < ex[2]; index2++) {
            smstrBm[index2] = getBitmask(semesterArr[index2]);
        }

        // 학기 loop
        for(int smstr : smstrBm) {

            boolean signUpYn = false;       // 과목 수강 여부
            List<Integer> curSignUpSbjct = new ArrayList<>();

            // 과목 loop >> i:과목 index
            for(int i=0; i<ex[0]; i++){
                int sbjct = sbjctBm[i];
                boolean signUpPsb = true;  // 수강 가능 여부

                // 이 과목 포함하는 학기
                int zzz = smstr & 1<<i;
                if((smstr & 1<<i) > 0) {


//                    if(sbjct != 0) {

                        // 수강한 과목 loop >> j: 수강한 과목 index
                        for(int j=0; j<ex[0]; j++ ) {
                            boolean signUpSbjct = signUpSbjctArr[j];
                            int 포함여부 = sbjct & 1<<j;

                            if(signUpSbjct && i == j) {
                                // j 수강하고 i과목과 같음
                                signUpPsb = false;
                            }else if(!signUpSbjct && i == j){
                                // j 수강하지 않고 i과목과 같음

                            }else if(signUpSbjct && i != j) {
                                // j 수강하고 i과목과 다름

                            }else {
                                // j 수강하지 않고 i과목과 다름
                                if((sbjct & 1<<j) > 0) signUpPsb = false;
                            }

                            if(!signUpPsb) break;
                        }
//                    }

                }

                if(signUpPsb) {
                    signUpYn = true;        // 수강 했음 >> 학기 수 ++
                    curSignUpSbjct.add(i);
                }

            }

            if(signUpYn) {
                ret++;      // 학기 수 ++

                System.out.println("curSignUpSbjct : " + curSignUpSbjct.toString());

                // 전체 수강한 과목 업데이트
                for(Integer curSignUp : curSignUpSbjct) {
                    signUpSbjctArr[curSignUp] = true;
                }

//                signUpLog(signUpSbjctArr);
            }

            if(signUpInt(signUpSbjctArr) == ex[1]) {
                break;
            }

        }


        return ret;

    }

    public static void signUpLog(boolean[] signUpSbjctArr) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<signUpSbjctArr.length; i++) {
            if(signUpSbjctArr[i]) {
                if(i == 0) {
                    sb.append(i);
                }else {
                    sb.append(", " + i);
                }
            }
        }

        System.out.println("수강한 과목 : " + sb.toString());
    }

    public static int signUpInt(boolean[] signUpSbjctArr) {
        int ret = 0;
        for (boolean b : signUpSbjctArr) {
            if (b) {
                ret++;
            }
        }
        return ret;
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

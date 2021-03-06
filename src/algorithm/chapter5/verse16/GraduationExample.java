package algorithm.chapter5.verse16;

/**
 * 16.4 졸업 학기 (590 p)
 * 예제 1
 * 4 4 4 4 (전공 과목 수, 필수 과목 수, 학기 수, 최대 수강 수)
 * -- 과목 정보
 * 0
 * 1 0
 * 3 0 1 3
 * 0
 * -- 학기정보
 * 4 0 1 2 3
 * 4 0 1 2 3
 * 3 0 1 3
 * 4 0 1 2 3
 *
 * 예제 2
 * 4 2 2 4
 * --과목 정보
 * 1 1
 * 0
 * 1 3
 * 1 2
 * --학기정보
 * 3 0 2 3
 * 3 1 2 3
 */
public class GraduationExample {
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
}

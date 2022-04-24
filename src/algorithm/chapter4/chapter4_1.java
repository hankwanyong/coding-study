package algorithm.chapter4;
/**
 * 04. 알고리즘의 시간 복잡도 분석
 * @author euny
 *
 */
public class chapter4_1 {
	/*
	 * 4.1 도입
	 *  1.1. 알고리즘의 속도 측정
	 *   사실 '프로그램의 수행 시간'으로 보는 것이 가장 적합하지만, 척도로 사용할 수 없는 이유
	 *   여러가지 환경에 의해 바뀔 수 있기 때문에 외적요소를 통일 시켜서 측정해야함
	 *   입력에 대한 실행 시간을 반영하지 못함
	 *   입력의 크기, 특성에 따라 수행시간이 달라짐
	 * 
	 *  1.2. 반복문의 지배
	 *   지배한다 : 한 가지 항목이 전체의 대소를 좌지우지하는 것
	 *   알고리즘의 수행 시간을 지배하는 건 반복문
	 *   수행 시간 = 입력의 크기에 대한 반복문의 수행 횟수
	 *   
	 * 4.2 선형 시간 알고리즘
	 *  2.1. 다이어트 현황 파악 : 이동 평균 계산하기
	 *   이동 평균 : 시간에 따라 변화하는 값들을 관찰할 때 유용하게 사용할 수 있는 통계적 기준
	 *   M 이동 평균 : 마지막 M개의 관찰 값의 평균
	 *   수행 시간이 변수에 정비례하는(시간 복잡도가 N인) 알고리즘을 선형 시간 알고리즘 이라고 함
	 *   가장 좋은 알고리즘인 경우가 많음
	 *   한번씩 보기만 해도 (가장 간단한 작업조차도) 선형 시간이 걸리기 때문
	 *   
	 * 4.3 선형 이하 시간 알고리즘
	 *  3.1. 성형 전 사진 찾기
	 *   입력으로 주어진 자료에 미리 알고 있는 지식을 더하면 한번씩 보지도 않아도 됨
	 *   선형 시간보다 적은 시간의 알고리즘 가능
	 *   이진 탐색 알고리즘을 이용하여 시간 복잡도가 log N인 알고리즘을 선형 이하 시간 알고리즘이라고 함
	 *   이 알고리즘은 구현하기 복잡함
	 *   '존 벤틀리가 프로그래머 수십 명에게 구현하도록 했지만, 버그 없는 코드를 작성한 사람이 절반도 되지 않았다는 일화가 있음'
	 *
	 * 4.4 지수 시간 알고리즘
	 *  4.1. 다항 시간 알고리즘
	 *   어떤 변수에 대한 거듭제곱들의 선형 결하으로 이루어진 식이 다항식
	 *   반복문의 수행 횟수가 입력 크기의 다항식으로 표현되는 알고리즘 : 다항 시간 알고리즘
	 *   선형 시간 알고리즘도 다항 시간 알고리즘에 속함
	 *   여러 개의 답이 있고 가장 좋은 답을 찾는 문제는 모든 답을 고려해보는 것이 가장 좋은 방법
	 *   이런 경우에 재귀 호출을 이용한 방법이 유용함
	 *   4.1.1. 지수시간 알고리즘
	 *    지수 함수는 알고리즘의 수행 시간에 엄청난 영향을 미침
	 *    아직까지 지수시간 알고리즘보다 나은 알고리즘을 찾지 못한 문제들이 많음
	 *    효율적으로 해결할 수 있음의 경계가 되고 있음
	 *    '다항시간 알고리즘'이 있다 => 효율적 해결 가능
	 *   4.1.2. 소인수 분해의 수행 시간 (?)
	 *    N이 주어지고 만약 소수이면 N-1번 나누는 연산을 해보기 때문에 선형 시간 알고리즘으로 볼 수 있음
	 *    하지만 N이 큰 수가 되면 차지하는 bit 수 (메모리)가 많아지기 때문에 그 크기에 비례해서 알고리즘 수행 시간이 최대 2배 증가함
	 *    지수 시간이 걸린다고 볼 수 있음
	 *    
	 * 4.5 시간 복잡도
	 *  시간 복잡도 : 가장 널리 사용되는 알고리즘의 수행 시간 기준
	 *  알고리즘 실행 중 수행하는 기본적인 연산의 수를 입력의 크기에 대한 함수로 표현
	 *  시간 복잡도가 높다 == 입력의 크기가 증가할 때 알고리즘 수행시간이 더 빠르게 증가한다
	 *  입력의 크기에 따라 적합한 알고리즘이 다를 수 있음
	 *  5.1. 입력 종류에 따른 수행 시간의 변화
	 *   입력은 크기뿐 아니라 어떤 형태인지도 수행 시간에 영향을 미침
	 *   이렇게 입력의 종류에 따라 수행 시간이 달라지는 경우는 최선/최악/평균의 수행시간을 따로 계산
	 *   대부분 '최악의 수행시간'과 '수행시간 기대치'가 비슷하기 때문에 최악의 수행시간을 씀
	 *  5.2. 점근적 시간 표기: O 표기
	 *   Big-O Notation
	 *   주어진 함수에서 가장 빨리 증가하는 항만을 남긴 채 나머지를 버리는 표기법
	 *   반복문이 몇 번 수행되는지만 보면 손쉽게 구할 수 있음
	 *   상수 시간 알고리즘 => O(1)
	 *   5.2.1. O 표기법의 의미
	 *    상수를 떼어내지 않은 식 과 O 표기법으로 떼어낸 식 은 적당히 큰 수가 되면 별 차이가 없어진다.
	 *    이 때 전자에 적절한 수 C를 곱해주면 항상 후자보다 크다는 성질을 만족할 수 있다.
	 *    함수의 상한을 나타낼 수 있음
	 *    최악의 수행시간과 관련이 있는 것은 아님
	 *   5.2.2. 시간 복잡도 분석 연습
	 *    선택 정렬 : 주어진 입력의 순서에 상관없이 일정
	 *    삽입 정렬 : 주어진 입력의 정렬 상태에 따라 복잡도가 달라짐
	 *    최선 : O(N)
	 *    최악 : O(N^2)
	 *   5.2.3. 시간 복잡도의 분할 상환 분석
	 *    반복문을 세는 것보다 더 정확한 계산
	 *    큰 작업에서의 시간이 일정할 때, 구성하는 작은 작업에서의 시간이 각각 달라도 작은 작업의 갯수로 나누어주어 평균을 내면 항상 소요시간이 같음
	 */

}
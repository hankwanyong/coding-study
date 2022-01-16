package effectivejava.chapter10.item75;

/**
 * 예외의 상세 메시지에 실패 관련 정보를 담으라.
 * @author 박민영
 *
 */
public class Item75Main {
	
	
	/*
	 * 예외를 잡지 못해 프로그램이 실패하면 자바 시스템은 그 예외의 스택 추적 정보를 자동으로 출력한다.
	 * 스택 추적은 예외 객체의 toString 메서드를 호출해 얻는 문자열로, 보통은 예외의 클래스 이름 뒤에 상세 메시지가 붙는 형태다.
	 * 이 정보가 실패 원인을 분석해야 하는 프로그래머 혹은 사이트 신뢰성 엔지니어(SRE)가 얻을 수 있는 유일한 정보인 경우가 많다.
	 * 따라서 예외의 toString 메서드에 실패 원인에 관한 정보를 가능한 한 많이 담아 변환하는 일은 아주 중요하다.
	 * 
	 * 실제 순간을 포착하려면 발생한 예외에 관여된 모든 매개변수와 필드의 값을 실패 메시지에 담아야 한다.
	 * 
	 * 유의사항
	 * 1. 관련 데이터를 모두 담아야 하지만 장황할 필요는 없다.
	 *   - 분석이 필요하다면 소스코드를 함께 살펴보기 때문
	 * 2. 예외의 상세 메시지와 최종 사용자에게 보여줄 오류 메시지를 혼동해서는 안된다.
	 *   - 최종 사용자에게는 친절한 안내메세지를 보여주는 반면, 오류 메시지는 가독성보다는 담긴 내용이 중요함.
	 * 3. 실패를 적절히 포착하려면 필요한 정보를 예외 생성자에서 모두 받아서 상세 메시지까지 생성해놓은 방법도 괜찮다.
	 *   - 예를들어 현재의 IndexOutOfBoundsException 생성자는 String 을 받지만, 다음과 같이 구현했어도 좋았을 것이다.
	 * */
	//IndexOutOfBoundsException 의 3번에 해당하는 생성자.
//	public IndexOutOfBoundsException(int lowerBound, int upperBound, int index) {
//		//실패를 포착하는 상세 메세지를 생성한다.
//		super(String.format("최솟값 : %d, 최댓값 : %d, 인덱스 : %d", lowerBound,upperBound,index));
//		
//		//프로그램에서이용할 수 있도록 실패 정보를 저장해둔다.
//		this.lowerBound =lowerBound;
//		this.upperBound =upperBound;
//		this.index =index;
//	}
	
	
	/*
	 * 자바9 에서는  IndexOutOfBoundsException애 정수 인덱스 값을 받는 생성자가 추가되었다.
	 * 
	 * 
	 * Item70에서 제안하였듯, 실패와 관련된 정보를 얻을수 있는 접근자 메서드를 적절히 제공하는것이 좋다.(앞에 예에서 lowerBound, upperBound, index)
	 * 포착한 실패정보는 예외의 상황을 복구하는데 유용할 수 있으므로 접근자 메서드는 비검사 예외보다 검사예외에서 더 빛을 발한다.
	 * 'toString이 반환한 값에 포함된 정보를 얻어올 수 있는 API를 제공하자'는 일반원칙(아이템 12, 75쪽)을 따른다는 관점에서, 비검사 예외라도 상세정보를 알려주는 접근자 메서드를 제공하라고 권하고 싶다.
	 * */
	
}



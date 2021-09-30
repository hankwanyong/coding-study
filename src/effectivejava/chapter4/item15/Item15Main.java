package effectivejava.chapter4.item15;

public class Item15Main {

	/*	Item15 클래스와 멤버의 접근 권한을 최소화하라
	 * 
	 * 잘 설계된 컴포넌트 > 캡슐화
	 * 	- 캡슐화 : 속성(Attribute) 와 행위(Method) 를 하나로 묶고 그중 일부를 외부에서 사용하지 못하도록 은닉한다
	 * 	- 캡슐화 장점 : 
	 * 		1. 여러 컴포넌트를 병렬로 개발 > 시스템 개발 속도를 높인다
	 * 		2. 각 컴포넌트를 더 빨리 파악 > 디버깅이 쉽고, 다른 컴포넌트로 교체하는 부담이 적다
	 * 		3. 성능 최적화에 도움을 준다
	 * 		4. 외부 의존하지 않는 컴포넌트라면 다른곳에서도 유용하게 사용 가능 > 소프트웨어 재사용성을 높인다
	 * 		5. 개별 컴포넌트 동작을 검증 가능 > 큰 시스템 제작 난이도를 낮춘다
	 *  - 기본원칙 : 모든 클래스와 멤버의 접근성을 가능한 한 좁혀야 한다.
	 *  - 자바 접근 제어자 > /img/accessModifier.PNG 참고
	 *  	* package-private : defalut 접근제어자
	 *  
	 *  public 멤버는 공개 API 이므로 꼭 필요한 경우가 아니면 public은 사용하지 않는다
	 *  
	 *  - 주의 : 길이가 0이 아닌 배열은 모두 변경 가능하다
	 *  	> public static final 배열은 제공하면 보안 허점!
	 *  	> private로 설정 후 제공자를 사용하자
	 *  		1. 불변제공자 or clone 인스턴스
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

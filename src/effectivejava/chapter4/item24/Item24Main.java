package effectivejava.chapter4.item24;

public class Item24Main{

	/*
	 * Item24 멤버 클래스는 되도록 static으로 만들라.
	 * 
	 * 중첩 클래스(nested class)란 다른 클래스 안에 정의된 클래스를 말한다.
	 * 중첩 클래스의 종류로는, 정적 멤버 클래스, 멤버 클래스, 익명 클래스, 지역 클래스 로 총 네 가지가 있다.
	 * 정적 멤버 클래스를 제외한 나머지는 내부클래스에 해당된다.
	 * 
	 * 먼저, 중첩클래스를 왜 사용하는지 알아보면
	 *  - 내부 클래스에서 외부 클래스의 멤버에 손쉽게 접근할 수 있다.
	 *  - 서로 관련 있는 클래스들을 논리적으로 묶어, 코드의 캡슐화를 증가시킬 수 있다.
	 *  - 외부에서 내부 클래스에 접근할 수 없으므로 코드의 복잡성을 줄일 수 있다. 
	 *    또한, 외부 클래스의 복잡한 코드를 내부 클래스로 옮겨 코드 복잡성을 줄일 수 있다.
	 *    
	 * -정적 멤버 클래스
	 *  해당 클래스는 다른 클래스 안에 선언되고, 
	 *  바깥 클래스의 private 멤버에도 접근할 수 있다는 점을 제외하고는 일반 클래스와 동일하다.
	 *  외부 클래스를 간편하게 사용하기 위한 목적으로 쓰이고 대표적인 예로 builder 패턴이 있다.(NutritionFacts)
	 *  정적 멤버 클래스는 외부 클래스를 사용할 때 자연스럽게 내부 클래스를 호출하여 외부 클래스의 생성을 도와주는 헬퍼 클래스이다.
	 *  즉, 외부 클래스를 인스턴스화할 때 매번 내부 클래스도 재생성하는 것은 메모리 측면에서 비효율적이기 때문에 static으로 선언하여 재사용하는 것이다. 
	 *  단, 내부 클래스에서는 외부 클래스 객체를 참조하지 않을 때에만 정적으로 선언이 가능하다.
	 * 
	 * -비정적 멤버 클래스
	 *  비정적 멤버 클래스의 경우 바깥 클래스의 인스턴스와 암묵적으로 연결된다. 
	 *  이로 인해, 비정적 멤버 클래스의 인스턴스 메소드에서 this를 통해 외부 클래스의 메소드를 호출하거나 외부 클래스를 참조할 수 있다.
	 *  따라서 개념상 중첩 클래스의 인스턴스가 바깥 인스턴스와 독립적으로 존재할 수 있다면 정적 멤버 클래스로 만들어야 한다. 
	 *  비정적 멤버 클래스는 바깥 인스턴스 없이 생성할 수 없기 때문이다.
	 *  비정적 멤버 클래스는 바깥인스턴스.new MemberClass()를 통해서 수동으로 생성해줄 수도 있는데,
	 *  이 관계정보는 비정적 멤버 클래스의 인스턴스 안에 만들어져 메모리 공간을 더 차지하고, 생성 시간도 오래 걸린다.
	 *  static을 선언하지 않으면 바깥 인스턴스로의 숨은 내부 참조를 갖게 된다. 
	 *  이는 시간과 자원을 더 사용할 뿐 아니라, 가비지 컬렉션이 바깥 클래스의 인스턴스를 수거하지 못하는 메모리 누수가 발생할 수 있다. 
	 *  그러므로 멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 반드시 static을 붙여 정적 멤버 클래스로 만들도록 하자.
	 *  
	 *  -익명클래스
	 *   당연히 이름이 없고, 바깥클래스의 멤버도 아니다.
	 *   쓰이는 시점에 선언과 동시에 인스턴스가 만들어진다.
	 *   비정적인 문맥에서 사용될때만 바깥 클래스의 인스턴스를 참조할 수 있다.
	 *   짧지 않으면 가독성이 떨어진다.
	 *   람다를 지원하기 전에는 즉석에서 작은 함수 객체나 처리 객체를 만드는에 익명클래스를 주로 사용했다.
	 *   
	 *  -지역클래스
	 *   4가지 중첩 클래스 중에서 가장 드물게 사용된다.
	 *   지역변수를 선언할 수 있는 곳 어디서든 선언가능하다.
	 *   멤버클래스처럼 이름이 있고, 반복 사용 가능하다.
	 *   익명 클래스처럼 비정적 문맥에서 사용될 때만 바깥 인스턴스를 참조할 수 있다.
	 *   정적 멤버는 가질 수 없고 가독성을 위해 짧게 작성해야한다.
	 *   
	 */
	

	/*
	 * 핵심 정리
	 * - 중첩 클래스에는 네 가지가 있으며, 각각의 쓰임이 다르다.
	 *   메서드 밖에서도 사용해야 하거나 메서드 안에서 정의하기엔 너무 길다면 멤버 클래스로 만든다.
	 *   멤버 클래스의 인스턴스 각각이 바깥 인스턴스를 참조한다면 비정적으로, 그렇지 않으면 정적으로 만들자.
	 *   중첩 클래스가 한 메서드 안에서만 쓰이면서 그 인스턴스를 생성하는 지점이 단 한 곳이고 
	 *   해당 타입으로 쓰기에 적합한 클래스나 인터페이스가 이미 있다면 익명 클래스로 만들고, 그렇지 않으면 지역클래스로 만들자.
	 */
	
}

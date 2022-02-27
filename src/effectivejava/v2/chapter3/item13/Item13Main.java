package effectivejava.v2.chapter3.item13;

/**
 * clone 재정의는 주의해서 진행하라.
 * @author 박민영
 *
 */
public class Item13Main {
	/*---------------------------------------------------------------------------
	 * clone 일반규약
	 * 
	 * 이 객체의 복사본을 생성해 반환한다.
	 * '복사'의 정확한 뜻은 그 객체를 구현한 클래스에 따라 다를 수 있다.
	 * 일반적인 의도는 다음과 같다.
	 * 
	 * 어떤 객체 x에 대해 다음 식은 참이다.
	 * 
	 * x.clone() != x
	 * 
	 * 또한 다음 식도 참이다.
	 * 
	 * x.clone().getClass() ==  x.getClass()
	 * 
	 * 하지만 이상의 요구를 반드시 만족해야 하는 것은 아니다.
	 * 
	 * 한편 다음 식도 일반적으로 참이지만, 역시 필수는 아니다.
	 * 
	 * x.clone().equals(x)
	 * 
	 * 관례상, 이 메서드가 반환하는 객체는 super.clone을 호출해 얻어야 한다.
	 * 이 클래스와 (Object를 제외한) 모든 상위 클래스가 이 관례를 따른다면 다음 식은 참이다.
	 * 
	 * x.clone().getClass() == x.getClass()
	 * 
	 * 관례상, 반환된 객체와 원본 객체는 독립적이어야 한다.
	 * 이를 만족하려면 super.clone으로 얻은 객체의 필드 중 하나 이상을 반환 전에 수정해야 할 수도 있다.
	 * ---------------------------------------------------------------------------
	 * (clone의 명세는 허술하며 제대로 만드냐 안만드냐의 책임이 개발자에게 있다. -시스템적으로 구조화X
	 * 예를들어 상위클래스에서 super.clone이 아니라 생성자를 호출해 반환해도 컴파일러는 정상으로 판단한다.)
	 * 
	 * */
	public static void main(String[] args){
		//복사하려는 클래스의 모든 필드가 기본타입이거나 불변 객체를 참조한다면 clone 메서드는 super.clone을 호출하는것 외에 더 손볼 것이 없다.
		PhoneNumberClone pnc = new PhoneNumberClone(707, 867, 5309);
		PhoneNumberClone pncClone = pnc.clone();
		System.out.println("pnc : "+pnc);
		System.out.println("pncClone : "+pncClone);
		
		
		//구현 클래스가 가변객체를 참조하는 순간 재앙으로 변한다.
		//clone 메서드는 사실상 생성자와 같은 효과를 낸다. 즉, clone은 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다.
		Stack stack = new Stack();
		Stack stackClone = stack.clone();
		
		//clone을 재귀적으로 호출하는 것만으로는 충분하지 않을때도 있다.
		HashTable table = new HashTable();
		HashTable tableClone = table.clone();
		
		
		
		
		/*
		 * 복잡한 가변객체를 복제하는 다른 방법.
		 * 
		 * 1. super.clone을 호출하여 얻은 객체의 모든 필드를 초기 상태로 설정
		 * 2. 원몬 객체의 상태를 다시 생성하는 고수준 메서드를 호출
		 *  (HashTable로 예를 들면, buckets 필드를 새로운 버킷 배열로 초기화 한 후 원본 테이블에 담긴 모든 키-값 쌍 각각에 대해 복제본 테이블에 put해주면 됨)
		 * 
		 * 위 방법은 Cloneable 아키텍쳐의 기초가 되는 필드 단위 객체 복사를 우회하기 때문에 전체 Cloable 아키텍쳐와는 어울리지 않는 방식이다.
		 * 
		 * clone 메서드는 재정의될 수 있는 메서드를 호출하지 않아야 한다.
		 * 만약 clone이 하위클래스에서 재정의한 메서드를 호출하면, 하위 클래스는 복제 과정에서 자신의 상태를 교정할 기회를 잃게 되어 원본과 복제본의 상태가 달라질 가능성이 크다.
		 * 따라서 앞에서 얘기한 put메서드는 final이거나 private이어야 한다.(private이라면 final이 아닌 public 메서드가 사용하는 도우미 메서드일 것이다.)
		 * 
		 * Object의 clone메소드는 CloneNotSupportedException을 던진다고 선언했지만 재정의한 메서드는 그렇지 않다.
		 * public인 clone 메서드에서는 thorws 절을 없애야 한다.
		 * 검사 예외를 던지지 않아야 그 메서드를 사용하기 편하기 때문이다.
		 * 
		 * 상속용 클래스는 Cloneable을 구현해서는 안된다.
		 * clone을 동작하지 않게 구현해놓고 하위 클래스에서 재정의하지 못하게 할 수도 있다.
		 * 
		 * Cloneable을 구현한 스레드 안전 클래스를 작성할 때는 clone 메서드 역시 적절히 동기화해줘야 한다.
		 * 
		 * Cloneable을 구현하는 모든 클래스는 clone을 재정의 해야한다.
		 * 이때 접근 제한자는 public으로, 반환 타입은 클래스 자신으로 변경한다.
		 * 
		 * Cloneable을 구현하지 않는 상황에서는 복사생성자와 복사팩터리라는 더 나은 객체복사 방식을 제공할 수 있다.
		 * 
		 * */
		
		HashTable tableClone2 = new HashTable(table);
		HashTable tableClone3 = HashTable.newInstance(table);
		
		/*
		 * 복사 생성자와 복사 팩터리는 해당 클래스가 구현한 '인터페이스'타입의 인스턴스를 인수로 받을 수 있다.
		 * 예컨데 관례상 모든 범용 컬랙션 구현체는 Collection이나 Map타입을 받는 생성자를 제공한다.
		 * 인터페이스 기반 복사 생성자와 복사 팩터리의 정확한 이름은 변환생성자(conversion constructor)와 변환 팩터리(conversion factory)다.
		 * (복사 생성자와 복사 팩토리에서는 자기 타입을 인수로 받아 복사하는 방식을 취한다.
		 *  이것은 clone 방식의 단점이었던 언어모순적, 위험, 허술한 스펙, final용법과 충돌, 불필요한 checked exception, 형변환 등이 모두 없다.)
		 * 
		 * 
		 * 
		 * Cloneable이 몰로 온 모든 문제를 되짚어봤을 때, 새로운 인터페이스를 만들 때는 절대 Cloneable을 확장해서는 안되며, 새로운 클래스도 이를 구현해서는 안된다.
		 * final 클래스라면 Cloneable을 구현해도 위험히 크지 않지만, 성능 최적화 관점에서 검토한 후 별다른 문제가 없을 때만 드물게 허용해야 한다.
		 * 기본 원칙은 '복제 기능은 생성자와 팩터리를 이용하는게 최고'라는 것이다.
		 * 단, 배열만은 clone메서드 방식이 가장 깔끔한, 이 규칙의 합당한 예외라 할 수 있다.
		 * 
		 * */
	}
}

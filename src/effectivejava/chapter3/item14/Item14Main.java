package effectivejava.chapter3.item14;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import effectivejava.chapter3.item10.CaseInsensitiveString;
import effectivejava.chapter3.item10.PhoneNumber;
import effectivejava.chapter3.item11.PhoneNumberHashCode;

/**
 * Comparable을 구현할지 고려하라
 * @author 박민영
 *
 */
public class Item14Main {
	/*
	 * 순서가 명확한 값 클래스를 작성한다면 반드시 Comparable 인터페이스를 구현하자.
	 * 
	 * compareTo 메서드의 일반규약
	 * 
	 * 이 객체와 주어진 객체의 순서를 비교한다.
	 * 이 객체가 주어진 객체보가 작으면 음의정수를, 같으면 0, 크면 양의 정수를 반환한다.
	 * 다음 설명에서 sgn(표현식) 표기는 수학에서 말하는 부호함수를 뜻하며, 표현식의 값이 음수, 0, 양수일 때 -1, 0 , 1을 반환하도록 정의했다.
	 * 
	 * 1. Comparable을 구현한 클래스는 모든 x, y에 대해 sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) 이다.
	 * 2. Comparable을 구현한 클래스는 추이성을 보장해야 한다. 즉 (x.compareTo(y) > 0 && y.compareTo(z) > 0) 이면 x.compareTo(z) > 0이다.
	 * 3. Comparable을 구현한 클래스는 모든 z에 대해 x.compareTo(y) == 0 이면 sgn(x.compareTo(z)) == sgn(y.compareTo(z)) 이다.
	 * 4. 이번 권고가 필수는 아니지만 꼭 지키는게 좋다. (x.compareTo(y) == 0) == (x.equals(y))여야 한다. Comparable을 구현하고 이 권고를 지키지 않는 모든 클래스는 그 사실을 명시해야 한다.
	 * 
	 * compareTo는 타입이 다른 객체를 신경쓰지 않아도 된다.
	 * 타입이 다른 객체가 주어지면 ClassCastException을 던져도 된다.
	 * 
	 * hashCode 규약을 지키지 못하면 해시를 사용하는 클래스와 어울리지 못하듯, compareTo 규약을 지키지 못하면 비교를 활용하는 클래스와 어울리지 못한다.
	 * (비교를 활용하는 클래스 : TreeSet, TreeMap, Collections, Arrays 등)
	 * 
	 * 1 ~ 3 규약은 compareTo 메서드로 수행하는 동치성 검사고 equals 규약과 똑같이 반사성, 대칭성, 추이성을 충족해야 함을 뜻한다.
	 * 그래서 주의사항도 같다.
	 * 기존 클래스를 확장한 구체 클래스에서 새로운 값 컴포넌트를 추가했다면 compareTo규약을 지킬 방법이 없다.
	 * 우회법도 같다. Comparable을 구현한 클래스를 확장해 값 컴포넌트를 추가하고 싶다면, 확장하는 대신 독립된 클래스를 만들고, 이 클래스에 원래 클래스의 인스턴스를 가리키는 필드를 두면 된다.
	 * 
	 * 4규약은 필수는 아니지만 꼭 지키길 권한다. 정렬된 컬렉션들은 동치성을 비교할 때 equals 대신 compareTo를 사용한다.(Collection, Set, 혹은 Map)
	 * 
	 * */
	public static void main(String[] args) {
		Set<BigDecimal> set1 = new HashSet<>();
		set1.add(new BigDecimal("1.0"));
		set1.add(new BigDecimal("1.00"));
		System.out.println("set1.size() : "+set1.size());
		
		set1 = new TreeSet<>();
		set1.add(new BigDecimal("1.0"));
		set1.add(new BigDecimal("1.00"));
		System.out.println("set1.size() : "+set1.size());
		
		
		/*
		 * 메서드 작성 요령은 equals와 비슷하다.
		 * 입력 인수의 타입을 확인하거나 형변환 할 필요는 없다.
		 * null을 인수로 넣어 호출하면 NullPointerException을 던져야 한다.
		 * 
		 * compareTo메서드는 각 필드가 동치인지 비교하는 것이 아니라 그 순서를 비교한다.
		 * 비교자는 직접 만들거나 자바가 제공하느 것 중 골라 쓰면 된다.
		 * 아래는 자바가 제공하는 비교자를 사용한다.
		 * */
		
		CaseInsensitiveString s1 = new CaseInsensitiveString("A");
		CaseInsensitiveString s2 = new CaseInsensitiveString("b");
		System.out.println("s1.compareTo(s2) : "+s1.compareTo(s2));
		
		/*
		 * compareTo 메서드를 정수 기본 타입 필드를 비교할 때는 박싱된 기본차입 클래스들에 새로 추가된 정적 메서드를 활용하자.
		 * 아래는 정적 메서드를 사용하여 비교한 코드
		 * */
		PhoneNumber pn1 = new PhoneNumber(042, 486, 4864);
		PhoneNumber pn2 = new PhoneNumber(042, 486, 4864);
		System.out.println("pn1.compareTo(pn2) : "+pn1.compareTo(pn2));
		
		
		/*
		 * java8에서는 Comparator 인터페이스가 일련의 비교자 생성 메서드와 팀을 꾸려 메서드 연쇄 방식으로 비교자를 생성할 수 있게 되었다.
		 * 그리고 이 비교자들을 Comparable인터페이스가 원하는 compareTo 메서드를 구현하는데 활용할 수 있다.
		 * */
		PhoneNumberHashCode pnh1 = new PhoneNumberHashCode(042, 486, 4864);
		PhoneNumberHashCode pnh2 = new PhoneNumberHashCode(042, 486, 4864);
		System.out.println("pnh1.compareTo(pnh2) : "+pnh1.compareTo(pnh2));
		
		/*
		 * 값의 차를 이용하는 방식은 사용해선 안된다.
		 * 해시코드의 값의 차를 이용하는 방식을 사용한다면 추이성을 위배한다.
		 * 
		 * 정적메서드(Integer.compare) 또는 Comparator 를 사용하자.
		 * 
		 * 
		 * 순서를 고려해야 하는 값 클래스를 작성한다면 꼭 Comparable 인터페이스를 구현하여 그 인스턴스들을 쉽게 정렬하고, 검색하고, 비교기능을 제공하는 컬렉션과 어우러지도록 해야 한다.
		 * compareTo 메서드에서 필드의 값을 비교할 때 <와 >연산자를 쓰지 말아야 한다. 그 대신 박싱된 기본 타입 클래스가 제공하는 정적 compare메서드나 Comparator 인터페이스가 제공하는 비교자 생성 메서드를 사용하자.
		 * */
	}
}

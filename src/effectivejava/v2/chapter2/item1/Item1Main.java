package effectivejava.v2.chapter2.item1;

/*
 * 아이템1 생성자 대신 정적 팩터리 메서드를 고려하라
 * 
 * 정적 팩터리 메서드 : private 생성자를 통해 new를 통한 객체 생성을 감추고 static 메서드를 통해 객체 생성을 캡슐화하는 디자인 패턴
 * 
 * 정적 팩터리 메서드 장/단점
 * 
 * 장점 :
 * 	1. 이름을 가질 수 있다.
 * 		- 객체의 특성을 쉽게 묘사할 수 있다.
 * 	2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
 * 		- 인스턴스를 통제할 수 있다.
 * 	3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
 * 		- 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 유연성을 제공
 * 	4. 입력 매새변수에 따라 매번 다른 클래스의 객체를 반활할 수 있다.
 * 		- EnumSet의 원소 개수에 따른 객체 (RegularEnumSet - 64개 이하, JumboEnumSet - 65개 이상) 
 * 	5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
 * 		- Class<?> childClass = Class.forName("File package path");
 * 		  return childClass.newInstance();
 * 		- application.xml 같이 설정하는 xml에  DB 드라이버 명시(텍스트)
 * 
 * 단점 : 
 * 	1. public 이나 protected 생성자가 없어서 상속이 불가능 하다
 * 	2. 프로그래머가 찾기가 힘들다. > API문서를 잘 써놓자
 * 
 */
public class Item1Main {
    // zzzzz commit test
}

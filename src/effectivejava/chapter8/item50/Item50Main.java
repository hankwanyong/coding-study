package effectivejava.chapter8.item50;

import java.util.Date;

public class Item50Main {
	/* 
	 *  아이템50
	 *  적시에 방어적 복사본을 만들라 
	 *  
	 *  - 클라이언트가 여러분의 불변식을 깨뜨리려 혈안이 되고 있다고 가정하고 방어적으로 프로그래밍해야 한다.
	 */
	

	public static void main(String[] args) {
		Date start = new Date();
		Date end = new Date();
		
		//첫 번째 공격
		Period p = new Period(start, end);
		System.out.println("======= 첫 번째 ============");
		System.out.println(p.toString());
		
		end.setYear(78);
		System.out.println("======= 두 번째 ============");
		System.out.println(p.toString());
		
		// 외부 공격으로 Period 인스턴스의 내부를 보호하려면 생성자에서 받은 가변 매개ㅐ변수 각각을 방어적으로 복사해야 한다.
		// 멀티스레딩 환경이라면 원본 객체의 유효성을 검사한 후 복사본을 만드는 그 찰나의 취약한 순간에 다른 스레드가 원본 객체를 수정하는 공격
		// 검사시점/사용시점(time-of-check/thie-of-use) 공격 혹은 TOCTOU 공격이라 한다.
		
		//두 번째 공격
		p.end().setYear(77);
		// -> 가변 필드의 방어적 복사본을 반환해라.
		
	}
	
	/* 핵심정리
	 * 되도록 불변 객체들을 조합해 객체를 구성해야 방어적 복사를 할 일이 줄어든다.
	 * Period 예제의 경우, 자바8 이상으로 개발해도 된다면 Instant 혹시 LocalDateTime ZonedDateTiem 을 사용 해라.
	 * 방어적 복사에는 성능 저하가 따르고 항상 쓸 수 있는 것도 아니다.
	 * 통제권을 이전하는 메서드를 호출하는 클라이언는 해당 객체를 더 이상 직접 수정하는 일이 없다고 약속해야 한다.
	 */
   
}

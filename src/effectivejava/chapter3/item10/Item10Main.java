package effectivejava.chapter3.item10;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * equals는 일반 규약을 지켜 재정의하라
 * @author 박민영
 *
 */
public class Item10Main {
	
	/* 
	 * 1) 재정의 하지 않는 경우
	 * 	1. 각 인스턴스가 본질적으로 고유하다.
	 * 	2. 인스턴스의 논리적 동치성을 검사할 일이 없다.
	 * 	3. 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다.
	 * 	4. 클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없다.
	 * 
	 * 2) 재정의 해야 할 때? -> 물리적 동치성이 아닌 논리적 동치성을 확인해야 하는 경우.
	 * 
	 * 3) equals 메서드의 일반 규약
	 * 	1. 반사성 : null이 아닌 모든 참조값 x에 대해, x.equals(x) 는 true다.
	 * 	2. 대칭성 : null이 아닌 모든 참조값 x, y에 대해, x.equals(y) 와 y.equals(x) 는 같은 값이다.
	 * 	3. 추이성 : null이 아닌 모든 참조값 x, y, z에 대해, x.equals(y) 가 ture 고  y.equals(z) 도 ture면 x.equals(z)도 ture다.
	 * 	4. 일관성 : null이 아닌 모든 참조값 x, y에 대해, x.equals(y) 를 반복해서 호출하면 항상 true를 반환하거나 false를 반환한다.
	 * 	5. null-아님 : null이 아닌 모든 참조값 x에 대해, x.equals(null)은 false다.
	 * 
	 * */
	public static void main(String[] args) throws MalformedURLException {
		
		//대칭성을 위배하는 경우
		CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
		String s = "polish";
		
		System.out.println("cis.equals(s) : "+cis.equals(s));
		System.out.println("s.equals(cis) : "+s.equals(cis));
		
		
		//추이성을 위배하는 경우
		ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
		Point p2 = new Point(1, 2);
		ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
		
		System.out.println("p1.equals(p2) : "+p1.equals(p2));
		System.out.println("p2.equals(p3) : "+p2.equals(p3));
		System.out.println("p1.equals(p3) : "+p1.equals(p3));
		
		
		//일관성을 위배하는 경우
		//java.net.URL의 equals 는 주어진 URL과 매핑된 호스트의 IP주소를 이용해 비교한다.
		//호스트 이름을 IP주소로 바꾸려면 네트워크를 통해야 하는데, 그 결과가 항상 같다고 보장할 수 없다.
		URL url1 = new URL("https://www.naver.com");
		URL url2 = new URL("https://www.naver.com");
		System.out.println("url1.equals(url2) 1 : "+url1.equals(url2));
		System.out.println("url1.equals(url2) 2 : "+url1.equals(url2));
		System.out.println("url1.equals(url2) 3 : "+url1.equals(url2));
		System.out.println("url1.getHost : "+url1.getHost());
		System.out.println("url2.getHost : "+url2.getHost());
		
		//null-아님에서 주의해야 할 것
		//o.equals(null) 이 true를 반환하는 상황은 상상할 수 없지만, NullPointerException을 던지는 코드는 흔하다. 이 일반규약은 이련 경우도 허용하지 않는다.
		MyType mt = new MyType();
		System.out.println("mt.equals(null) : "+mt.equals(null));
		
		
		
		/*
		 * equals 재정의시 주의사항
		 * 
		 * 1. equals를 재정의 할 땐 hashCode도 반드시 재정의하자.
		 * 2. 너무 복잡하게 해결하려 들지 말자. 필드들의 동치성만 검사해도 equals 규약을 어렵지 않게 지킬 수 있다.
		 * 3. Object 외의 타입을 매개변수로 받는 equals 메서드는 선언하지 말자.
		 * 
		 * 
		 * 꼭 필요한 경우가 아니라면 equals를 재정의 하지 말자.
		 * 많은 경우에 Object의 equals가 여러분이 원하는 비교를 정확히 수행해준다.
		 * 재정의해야 할 때는 그 클래스의 핵심 필드 모두를 빠짐없이, 다섯가지 규약을 확실히 지켜가며 비교해야 한다.
		 * */
	}
}

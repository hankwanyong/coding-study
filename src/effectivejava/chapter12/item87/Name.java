package effectivejava.chapter12.item87;

import java.io.Serializable;

public class Name implements Serializable{
	/**
	 * 성. null이 아니어야 함.
	 * @serial
	 */
	private final String lastName;
	
	/**
	 * 이름. null이 아니어야 함.
	 * @serial
	 */
	private final String firstName;
	/**
	 * 중간이름. 중간이름이 없다면 null.
	 * @serial
	 */
	private final String middleName;
	
	//기본 직렬화 형태가 적합하다고 결정했더라도 불변식 보장과 보안을 위해 readObject 메서드를 제공해야 할 때가 많다.
	//해당 클래스에서는 readObject 메서드가 lastName과 firstName 필드가 null이 아님을 보장해야한다.
	
	/*
	 * Name의 세 필드 모두 private임에도 문서화 주석이 달려있다.
	 * 이 필드들은 결국 클래스의 직렬화 형태에 포함되는 공개 API에 속하며 공개 API는 모두 문서화해야하기 떄문이다.
	 * private 필드의 설명을 API 문서에 포함하라고 자바독에 알려주는 역할은 @serial 태그가 한다.
	 * @serial 태그로 기술한 내용은  API문서에서 직렬화 형태를 설명하는 특별한 페이지에 기록된다.
	 */

}

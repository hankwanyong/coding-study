package effectivejava.v2.chapter2.item9;

/*
 * 아이템9 try-finally보다는 try-with-resources를 사용하라
 */
public class Item9Main {

	
	// try-with-resources 사용법
	/*
	 * try (사용할 자원 선언) {
	 * 		처리 소스
	 * }catch(excetion) {}
	 */
	
	// 자바7 부터 사용가능!
	// AutoCloseable 상속받은 객체만 사용가능
	// InputStream >> Closeable 상속 >> AutoCloseable 상속
	
	// java.lang.Object
	//  java.io.InputStream
	//    java.io.FilterInputStream
	//      java.io.BufferedInputStream
	
}

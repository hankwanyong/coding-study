package effectivejava.chapter10.item77;

/**
 * 예외를 무시하지 말라
 * @author 박민영
 *
 */
public class Item77Main {
	public static void main(String[] args) {
		
		
		
		try {
			//...여러가지 코드
		} catch (Exception e) {
		}
		
		/*
		 * 예외는 문제상황에 잘 대처하기 위해 존재하는데, catch 블록을 비워두면 예외가 존재할 이유가 없어진다.
		 * 
		 * 물론 예외를 무시해야 할 때도 있다. 예를 들어 FileInputStream을 닫을 때 그렇다.
		 * 입력 전용 스트림이므로 파일의 상태를 변경하지 않았으니 복구 할 것이 없으며,
		 * 스트림을 닫는다는 건 필요한 정보는 이미 다 읽었다는 뜻이니 남은 작업을 중단할 이유도 없다.
		 * 
		 * 예외를 무시하기로 했다면 catch 블록 안에 그렇게 결정한 이유를 주석으로 남기고 예외 변수의 이름도 ignored로 바꾸도록 하자.
		 * 
		 * */
		 
		try {
			//...여러가지 코드
		} catch (Exception ignored) {
			//예외를 무시하는 이유
		}
		
	}
}

package effectivejava.chapter12.item88;

import java.io.*;

public class BogusPeriod {
    // 진짜 Period 인스턴스에서는 만들어질 수 없는 바이트 스트림,
    // 정상적인 Period 인스턴스를 직렬화한 후에 손수 수정한 바이트 스트림이다.
    private static final byte[] serializedForm = {
        (byte)0xac, (byte)0xed, 0x00, 0x05, 0x73, 0x72, 0x00, 0x06,
        0x50, 0x65, 0x72, 0x69, 0x6f, 0x64, 0x40, 0x7e, (byte)0xf8
        //... 생략
    };

    // 상위 비트가 1인 바이트 값들은 byte로 형변환 했는데,
    // 이유는 자바가 바이트 리터럴을 지원하지 않고 byte 타입은 부호가 있는(signed) 타입이기 때문이다.

    public static void main(String[] args) {
        Period p = (Period) deserialize(serializedForm);
        System.out.println(p);
    }

    // 주어진 직렬화 형태(바이트 스트림)로부터 객체를 만들어 반환한다.
    static Object deserialize(byte[] sf) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sf)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                return objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
    // 실행 결과, end가 start 보다 과거다. 즉, Period의 불변식이 깨진다.
//    Fri Jan 01 12:00:00 PST 1999 - Sun Jan 01 12:00:00 PST 1984

}

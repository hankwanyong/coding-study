package effectivejava.chapter4.item22;

//코드 22-2 상수 유틸리티 클래스
public class PhysicalConstants {
	private PhysicalConstants () {} //인스턴스화 방지
	
	//아보카드로 수 (1/몰) 
	public static final double AVOGADROS_NUMBER = 6.022_140_8577e23; 
	//볼츠만 상수 (J/K) 
	public static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23; 
	//전자 질량 (kg) 
	public static final double ELETRON_MASS = 9.109_383_56e-31;
}

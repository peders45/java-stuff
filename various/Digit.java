package various;

public class Digit {
	
	private int numSystem;
	private int digitValue;
	private static String posOne;
	private static String posTen;
	private static String posHundred;
	
	public Digit(int system) {
		if (system < 1) {
			throw new IllegalArgumentException("Numeral system must be at least 1");
		}
		this.numSystem = system;
		this.digitValue = 0;
	}
	
	public int getValue() {
		return digitValue;
	}
	
	public boolean increment() {
		this.digitValue += 1; 
		if (digitValue == numSystem) {
			this.digitValue = 0;
			return true;
		}
		return false;
	}
	
	public int getBase() {
		return numSystem;
	}
	
	public String toString() {
		return Character.toString("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(digitValue));
	}

	public static void main(String[] args) {
		Digit myDigit = new Digit(16);
		System.out.println(myDigit.getBase()); //16
		myDigit.increment();
		System.out.println(myDigit.getValue()); //1
		myDigit.increment();
		System.out.println(myDigit.toString()); //2
		myDigit.increment();
		myDigit.increment();
		myDigit.increment();
		myDigit.increment();
		myDigit.increment();
		System.out.println(myDigit.toString()); //7
		myDigit.increment();
		myDigit.increment();
		myDigit.increment();
		System.out.println(myDigit.toString()); //A
		myDigit.increment();
		System.out.println(myDigit.toString()); //B
		myDigit.increment();
		System.out.println(myDigit.toString()); //C
		myDigit.increment();
		myDigit.increment();
		myDigit.increment();
		myDigit.increment();
		myDigit.increment();
		myDigit.increment();
		System.out.println(myDigit.toString()); //2
		
		//multiple digits
		Digit ones = new Digit(12);
		Digit tens = new Digit(6);
		Digit hundreds = new Digit(22);
		
		// biggest possible is number is L5B
		for (int i = 0; i < hundreds.getBase(); i++) {
			posHundred = hundreds.toString();
			for (int j = 0; j < tens.getBase(); j++) {
				posTen = tens.toString();
				for (int k = 0; k < ones.getBase(); k++) {
					posOne = ones.toString();
					System.out.println("Number is: " + posHundred + posTen + posOne);
					ones.increment();
				}
				tens.increment();
			}
			hundreds.increment();
		}
		posOne = ones.toString();
		posTen = tens.toString();
		posHundred = hundreds.toString();
		System.out.println("Number is: " + posHundred + posTen + posOne);
		
	}

}

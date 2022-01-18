package org.soul.udf;

public class MaskTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Mask udf1 = new Mask(true);
		Mask udf2 = new Mask(false);
		
		String input = "Practise";
		System.out.println("input: " +  input + " output : " + udf1.eval(input, 2));
		System.out.println("input: " +  input + " output : " + udf2.eval(input, 2));
	}

}

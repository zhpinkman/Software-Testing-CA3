package org.springframework.samples.petclinic.temp;

public class Temp {

	public int tempMethod(int a,int b, boolean c) {
		int sum = 0;
		if (c == true) {
			sum = a + b;
		} else {
			sum = a - b;
		}
		return sum;
	}
}

package org.springframework.samples.petclinic.Temp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.samples.petclinic.temp.Temp;

public class TempTest {

	private Temp temp;

	@Before
	public void setup() {
		temp = new Temp();
	}

	@Test
	public void sumTest() {
		int a = 2;
		int b = 3;
		int sum = temp.tempMethod(a, b, true);
		Assert.assertEquals(sum, 5);
	}

	@Test
	public void minusTest() {
		int a = 10;
		int b = 5;
		int minus = temp.tempMethod(a, b, false);
		Assert.assertEquals(minus, 5);
	}

}

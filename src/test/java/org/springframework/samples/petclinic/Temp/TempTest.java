package org.springframework.samples.petclinic.Temp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.temp.Temp;

import java.util.Date;

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

	@Ignore
	public void tempTest() throws JsonProcessingException {

		PetType petType = new PetType();
		petType.setId(2);
		petType.setName("dog");


		Owner owner = new Owner();
		owner.setId(1);
		owner.setFirstName("Eduardo");
		owner.setLastName("Rodriquez");
		owner.setAddress("2693 Commerce St.");
		owner.setCity("McFarland");
		owner.setTelephone("6085558763");


		Pet pet = new Pet();
		pet.setId(8);
		pet.setName("Rosy");
		pet.setBirthDate(new Date());
		pet.setType(petType);
		pet.setOwner(null);


		Visit visit = new Visit();
		visit.setId(2);
		visit.setPet(pet);
		visit.setDate(new Date());
		visit.setDescription("rabies shot");

		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		String newVisitAsJSON = mapper.writeValueAsString(visit);

		System.out.println(newVisitAsJSON);

	}

}

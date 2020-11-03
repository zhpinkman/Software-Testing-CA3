package org.springframework.samples.petclinic.service.clinicService;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.*;
import org.springframework.samples.petclinic.service.ClinicServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.*;

public class ClinicServiceTests {

	@Mock
	private PetRepository petRepository;
	@Mock
	private VetRepository vetRepository;
	@Mock
	private OwnerRepository ownerRepository;
	@Mock
	private VisitRepository visitRepository;
	@Mock
	private SpecialtyRepository specialtyRepository;
	@Mock
	private PetTypeRepository petTypeRepository;

	@InjectMocks
	private ClinicServiceImpl clinicService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void constructorTest() {
		Assert.assertNotNull(clinicService);
	}

	@Test
	public void visitOwnerPetsTest1() {
		Owner owner = new Owner();

		Pet pet1 = mock(Pet.class);

		when(pet1.getBirthDate()).thenReturn(Date.from(LocalDate.of(2020, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		Visit visit1 = mock(Visit.class);

		when(visit1.getDate()).thenReturn(Date.from(LocalDate.of(2020, 10, 27).atStartOfDay(ZoneId.systemDefault()).toInstant()));


		when(pet1.getLastVisit()).thenReturn(java.util.Optional.of(visit1));

		when(petRepository.findByOwner(owner)).thenReturn(
			new ArrayList<>(Arrays.asList(pet1))
		);

		Vet vet = mock(Vet.class);
		when(vet.canCurePetTye(any())).thenReturn(true);


		when(vetRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(vet)));

		clinicService.visitOwnerPets(owner);

		verify(visitRepository, times(0)).save(any(Visit.class));

	}

	@Test
	public void visitOwnerPetsTest2() {
		Owner owner = new Owner();

		Pet pet2 = mock(Pet.class);

		when(pet2.getBirthDate()).thenReturn(Date.from(LocalDate.of(2020, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));


		Visit visit2 = mock(Visit.class);

		when(visit2.getDate()).thenReturn(Date.from(LocalDate.of(2020, 02, 27).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		when(pet2.getLastVisit()).thenReturn(java.util.Optional.of(visit2));

		when(petRepository.findByOwner(owner)).thenReturn(
			new ArrayList<>(Arrays.asList(pet2))
		);

		Vet vet = mock(Vet.class);
		when(vet.canCurePetTye(any())).thenReturn(true);

		when(vetRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(vet)));

		clinicService.visitOwnerPets(owner);

		verify(visitRepository, times(1)).save(any(Visit.class));

	}

	@Test
	public void visitOwnerPetsTest3() {
		Owner owner = new Owner();

		Pet pet3 = mock(Pet.class);

		when(pet3.getBirthDate()).thenReturn(Date.from(LocalDate.of(2015, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		Visit visit3 = mock(Visit.class);

		when(visit3.getDate()).thenReturn(Date.from(LocalDate.of(2019, 02, 27).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		when(pet3.getLastVisit()).thenReturn(java.util.Optional.of(visit3));


		when(petRepository.findByOwner(owner)).thenReturn(
			new ArrayList<>(Arrays.asList(pet3))
		);


		Vet vet = mock(Vet.class);
		when(vet.canCurePetTye(any())).thenReturn(true);

		when(vetRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(vet)));

		clinicService.visitOwnerPets(owner);

		verify(visitRepository, times(1)).save(any(Visit.class));

	}

	@Test
	public void visitOwnerPetsTest4() {
		Owner owner = new Owner();

		Pet pet4 = mock(Pet.class);
		when(pet4.getBirthDate()).thenReturn(Date.from(LocalDate.of(2015, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		Visit visit4 = mock(Visit.class);

		when(visit4.getDate()).thenReturn(Date.from(LocalDate.of(2020, 10, 27).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		when(pet4.getLastVisit()).thenReturn(java.util.Optional.of(visit4));


		when(petRepository.findByOwner(owner)).thenReturn(
			new ArrayList<>(Arrays.asList(pet4))
		);


		Vet vet = mock(Vet.class);
		when(vet.canCurePetTye(any())).thenReturn(true);

		when(vetRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(vet)));

		clinicService.visitOwnerPets(owner);

		verify(visitRepository, times(0)).save(any(Visit.class));

	}

	@Test(expected = ClinicServiceImpl.VisitException.class)
	public void complimentaryTest()  {
		Owner owner = new Owner();

		Pet pet5 = mock(Pet.class);

		when(pet5.getBirthDate()).thenReturn(Date.from(LocalDate.of(2015, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		when(pet5.getLastVisit()).thenReturn(Optional.empty());

		when(petRepository.findByOwner(owner)).thenReturn(
			new ArrayList<>(Arrays.asList(pet5))
		);

		when(vetRepository.findAll()).thenReturn(Collections.emptyList());

		clinicService.visitOwnerPets(owner);

		verify(visitRepository, times(0)).save(any(Visit.class));

	}


}

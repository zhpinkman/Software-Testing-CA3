package org.springframework.samples.petclinic.service.userService;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.*;
import org.springframework.samples.petclinic.service.ClinicServiceImpl;
import org.springframework.samples.petclinic.service.UserServiceImpl;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.*;


public class UserServiceTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl UserService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void constructorTest() {
		Assert.assertNotNull(UserService);
	}

	@Test(expected = Exception.class)
	public void NullRoleTest() throws Exception {
		User user1 = mock(User.class);
		when(user1.getRoles()).thenReturn(null);
		UserService.saveUser(user1);
		verify(userRepository, times(0));
	}

	@Test(expected = Exception.class)
	public void EmptyRoleTest() throws Exception {
		User user1 = new User();
//		user1.addRole("Owner_admin");
		UserService.saveUser(user1);
		verify(userRepository, times(0));
	}
}

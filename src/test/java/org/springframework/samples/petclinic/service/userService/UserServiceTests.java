package org.springframework.samples.petclinic.service.userService;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
	private UserServiceImpl userService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void constructorTest() {
		Assert.assertNotNull(userService);
	}

	@Test(expected = Exception.class)
	public void NullRoleTest() throws Exception {
		User user1 = mock(User.class);
		when(user1.getRoles()).thenReturn(null);
		userService.saveUser(user1);
		verify(userRepository, times(0)).save(user1);
		verify(user1, times(1)).getRoles();
	}

	@Test(expected = Exception.class)
	public void EmptyRoleTest() throws Exception {
		User user1 = mock(User.class);
		HashSet<Role> roles = new HashSet<>();
		when(user1.getRoles()).thenReturn(roles);
		userService.saveUser(user1);
		verify(userRepository, times(0)).save(user1);
		verify(user1, times(1)).getRoles();
	}

	@Test
	public void SuccessSaveUserTest() throws Exception {
		User tempUser = mock(User.class);
		HashSet<Role> roles = new HashSet<>();
		Role role1 = new Role();
		role1.setName("Zhivar");
		role1.setUser(tempUser);
		roles.add(role1);
		Role role2 = new Role();
		role2.setName("ROLE_zhivar");
		role2.setUser(null);
		roles.add(role2);
		when(tempUser.getRoles()).thenReturn(roles);
		userService.saveUser(tempUser);
		verify(userRepository, times(1)).save(tempUser);
	}


}

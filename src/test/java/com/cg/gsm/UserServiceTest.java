package com.cg.gsm;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.cg.gsm.entities.User;
import com.cg.gsm.exception.UserNotFoundException;
import com.cg.gsm.repository.UserDAOInt;
import com.cg.gsm.service.UserServiceIntImp;

@ExtendWith(MockitoExtension.class) // Junit5
public class UserServiceTest {
	@Mock
	private UserDAOInt userdaoint;// dummy dao layer

	@InjectMocks
	private UserServiceIntImp userserviceintimp;// service layer

	@Test
	public void testFindByPKUser() throws UserNotFoundException {
		// int id=10;
		User u = new User(10, "Sanjay", "Degaonkar", "9425493053", "sanjay.degaonkar@capgemini.com", null, null);
		when(userdaoint.findById(u.getId())).thenReturn(Optional.of(u));
		User realu = userserviceintimp.findByPK(10);
		assertEquals(10, realu.getId());

	}
	
	@Test
	public void testAddUser() {
		User u = new User(10, "Sanjay", "Degaonkar", "9425493053", "sanjay.degaonkar@capgemini.com", null, null);
		when(userdaoint.save(u)).thenReturn(u);
		User realu = userserviceintimp.add(u);
		assertEquals(u, realu);

	}

	@Test
	public void testFindAll() {

		User u = new User(10, "Sanjay", "Degaonkar", "9425493053", "sanjay.degaonkar@capgemini.com", null, null);
		User u1 = new User(11, "Maaz", "Abdulla", "9425493053", "sanjay.degaonkar@capgemini.com", null, null);
		User u2 = new User(12, "Aditya", "Bajare", "9425493053", "sanjay.degaonkar@capgemini.com", null, null);

		List<User> listofuser = new ArrayList<User>();
		listofuser.add(u);
		listofuser.add(u1);
		listofuser.add(u2);

		when(userdaoint.findAll()).thenReturn(listofuser);
		List<User> list = userserviceintimp.findAll();
		assertEquals(listofuser, list);
	}

}

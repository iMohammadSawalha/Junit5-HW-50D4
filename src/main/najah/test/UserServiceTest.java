package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.UserService;

@DisplayName("UserService Tests")
public class UserServiceTest {

	@ParameterizedTest(name = "{index} => username={0}, password={1}, expected={2}")
	@DisplayName("Test authentication")
	@CsvSource({ "admin,1234,true", "admin,wrong,false", "user,1234,false", "user,wrong,false" })
	void testAuthenticateParameterized(String username, String password, boolean expected) {
		UserService service = new UserService();
		assertEquals(expected, service.authenticate(username, password));
	}

	@Test
	@Disabled("Need isValidEmail to validate the format more strictly (e.g., single '@' and proper domain)")
	@DisplayName("Test email validation for multiple '@' signs")
	void testEmailWithMultipleAtSigns() {
		UserService service = new UserService();
		boolean isValid = service.isValidEmail("user@@example.com");
		assertFalse(isValid);
	}

	@Test
	@DisplayName("Test isValidEmail with a valid email")
	void testIsValidEmailValid() {
		UserService service = new UserService();
		assertTrue(service.isValidEmail("user@example.com"));
	}

	@Test
	@DisplayName("Test isValidEmail with missing '@'")
	void testIsValidEmailMissingAt() {
		UserService service = new UserService();
		assertFalse(service.isValidEmail("userexample.com"));
	}

	@Test
	@DisplayName("Test isValidEmail with missing '.'")
	void testIsValidEmailMissingDot() {
		UserService service = new UserService();
		assertFalse(service.isValidEmail("user@examplecom"));
	}
}

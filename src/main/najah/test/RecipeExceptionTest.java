package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.RecipeException;

@DisplayName("RecipeException Tests")
public class RecipeExceptionTest {

	@ParameterizedTest(name = "{index} => message={0}")
	@DisplayName("Test RecipeException message")
	@CsvSource({ "'Invalid recipe'", "'Missing ingredient'", "'Bad input'" })
	void testRecipeExceptionMessage(String message) {
		RecipeException ex = new RecipeException(message);
		assertEquals(message, ex.getMessage());
	}
}

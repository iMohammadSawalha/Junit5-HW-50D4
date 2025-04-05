package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeException;

@DisplayName("Recipe Tests")
public class RecipeTest {

	@ParameterizedTest(name = "{index} => coffee={0}, milk={1}, sugar={2}, chocolate={3}, price={4}")
	@DisplayName("Test setting valid recipe values")
	@CsvSource({ "3, 2, 1, 4, 10", "0, 0, 0, 0, 0" })
	void testValidRecipeInputs(int coffee, int milk, int sugar, int chocolate, int price) throws RecipeException {
		Recipe recipe = new Recipe();
		recipe.setAmtCoffee(String.valueOf(coffee));
		recipe.setAmtMilk(String.valueOf(milk));
		recipe.setAmtSugar(String.valueOf(sugar));
		recipe.setAmtChocolate(String.valueOf(chocolate));
		recipe.setPrice(String.valueOf(price));

		assertEquals(coffee, recipe.getAmtCoffee());
		assertEquals(milk, recipe.getAmtMilk());
		assertEquals(sugar, recipe.getAmtSugar());
		assertEquals(chocolate, recipe.getAmtChocolate());
		assertEquals(price, recipe.getPrice());
	}

	@Test
	@DisplayName("Test setAmtCoffee with invalid input")
	void testSetAmtCoffeeInvalid() {
		Recipe recipe = new Recipe();
		Exception exception = assertThrows(RecipeException.class, () -> {
			recipe.setAmtCoffee("abc");
		});
		assertEquals("Units of coffee must be a positive integer", exception.getMessage());
	}

	@Test
	@DisplayName("Test setAmtMilk with invalid input")
	void testSetAmtMilkInvalid() {
		Recipe recipe = new Recipe();
		Exception exception = assertThrows(RecipeException.class, () -> {
			recipe.setAmtMilk("xyz");
		});
		assertEquals("Units of milk must be a positive integer", exception.getMessage());
	}

	@Test
	@DisplayName("Test setAmtSugar with invalid input")
	void testSetAmtSugarInvalid() {
		Recipe recipe = new Recipe();
		Exception exception = assertThrows(RecipeException.class, () -> {
			recipe.setAmtSugar("sugar");
		});
		assertEquals("Units of sugar must be a positive integer", exception.getMessage());
	}

	@Test
	@DisplayName("Test setAmtChocolate with invalid input")
	void testSetAmtChocolateInvalid() {
		Recipe recipe = new Recipe();
		Exception exception = assertThrows(RecipeException.class, () -> {
			recipe.setAmtChocolate("choco");
		});
		assertEquals("Units of chocolate must be a positive integer", exception.getMessage());
	}

	@Test
	@DisplayName("Test setPrice with invalid input")
	void testSetPriceInvalid() {
		Recipe recipe = new Recipe();
		Exception exception = assertThrows(RecipeException.class, () -> {
			recipe.setPrice("ten");
		});
		assertEquals("Price must be a positive integer", exception.getMessage());
	}

	@Test
	@DisplayName("Test toString returns recipe name")
	void testToString() {
		Recipe recipe = new Recipe();
		recipe.setName("Mocha");
		assertEquals("Mocha", recipe.toString());
	}

	@Test
	@DisplayName("Test hashCode consistency for recipes with same name")
	void testHashCodeConsistency() {
		Recipe recipe1 = new Recipe();
		recipe1.setName("Espresso");
		Recipe recipe2 = new Recipe();
		recipe2.setName("Espresso");
		assertEquals(recipe1.hashCode(), recipe2.hashCode());
	}
}

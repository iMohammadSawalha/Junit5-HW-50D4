package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

@DisplayName("RecipeBook Tests")
public class RecipeBookTest {

	@ParameterizedTest(name = "{index} => recipeName={0}")
	@DisplayName("Test adding recipes")
	@CsvSource({ "Espresso", "Latte", "Cappuccino", "Mocha" })
	void testAddRecipeParameterized(String recipeName) {
		RecipeBook book = new RecipeBook();
		Recipe recipe = new Recipe();
		recipe.setName(recipeName);
		assertTrue(book.addRecipe(recipe));
	}

	@Test
	@DisplayName("Test getRecipes initial state")
	void testGetRecipesInitial() {
		RecipeBook book = new RecipeBook();
		Recipe[] recipes = book.getRecipes();
		assertEquals(4, recipes.length);
		for (Recipe recipe : recipes) {
			assertNull(recipe);
		}
	}

	@Test
	@DisplayName("Test adding duplicate recipe")
	void testAddDuplicateRecipe() {
		RecipeBook book = new RecipeBook();
		Recipe recipe = new Recipe();
		recipe.setName("Duplicate");
		assertTrue(book.addRecipe(recipe));
		// Try to add duplicate
		Recipe duplicate = new Recipe();
		duplicate.setName("Duplicate");
		assertFalse(book.addRecipe(duplicate));
	}

	@Test
	@DisplayName("Test deleteRecipe removes the recipe and returns its name")
	void testDeleteRecipe() {
		RecipeBook book = new RecipeBook();
		Recipe recipe = new Recipe();
		recipe.setName("DeleteMe");
		book.addRecipe(recipe);

		String deletedName = book.deleteRecipe(0);
		assertEquals("DeleteMe", deletedName);

		Recipe defaultRecipe = book.getRecipes()[0];
		assertNotNull(defaultRecipe);
		assertEquals("", defaultRecipe.getName());
	}

	@Test
	@DisplayName("Test deleteRecipe on empty slot returns null")
	void testDeleteRecipeNonExisting() {
		RecipeBook book = new RecipeBook();
		assertNull(book.deleteRecipe(1));
	}

	@Test
	@DisplayName("Test editRecipe updates the recipe and returns the old recipe name")
	void testEditRecipe() {
		RecipeBook book = new RecipeBook();
		Recipe original = new Recipe();
		original.setName("OriginalRecipe");
		book.addRecipe(original);

		Recipe newRecipe = new Recipe();
		newRecipe.setName("NewRecipe");
		String oldName = book.editRecipe(0, newRecipe);
		assertEquals("OriginalRecipe", oldName);
		assertEquals("", newRecipe.getName());
		assertEquals("", book.getRecipes()[0].getName());
	}

	@Test
	@DisplayName("Test editRecipe on empty slot")
	void testEditRecipeNonExisting() {
		RecipeBook book = new RecipeBook();
		Recipe newRecipe = new Recipe();
		newRecipe.setName("NewRecipe");
		assertNull(book.editRecipe(2, newRecipe));
	}

	@BeforeAll
	static void startMessage() {
		System.out.println("Started Recipe Book Tests!");
	}

	@AfterAll
	static void finishMessage() {
		System.out.println("Finsihed Recipe Book Tests!");
	}
}

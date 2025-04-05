package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Product;

@DisplayName("Product Tests")
public class ProductTest {

	@ParameterizedTest(name = "{index} => name={0}, price={1}, discount={2}, expectedFinalPrice={3}")
	@DisplayName("Test final price calculation")
	@CsvSource({ "Widget, 100.0, 10, 90.0", "Gadget, 200.0, 20, 160.0" })
	void testFinalPriceParameterized(String name, double price, double discount, double expectedFinalPrice) {
		Product product = new Product(name, price);
		product.applyDiscount(discount);
		assertEquals(expectedFinalPrice, product.getFinalPrice(), 0.001);
	}

	@Test
	@DisplayName("Test constructor with negative price")
	void testConstructorNegativePrice() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Product("Invalid", -10.0);
		});
		assertEquals("Price must be non-negative", exception.getMessage());
	}

	@Test
	@DisplayName("Test state update after applying discount")
	void testGettersAndStateUpdate() {
		Product product = new Product("TestProduct", 150.0);
		assertEquals("TestProduct", product.getName());
		assertEquals(150.0, product.getPrice(), 0.001);
		assertEquals(0.0, product.getDiscount(), 0.001);

		product.applyDiscount(25);
		assertEquals(25.0, product.getDiscount(), 0.001);
		assertEquals(112.5, product.getFinalPrice(), 0.001);
	}

	@Test
	@DisplayName("Test applying negative discount")
	void testApplyNegativeDiscount() {
		Product product = new Product("NegativeDiscount", 100.0);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			product.applyDiscount(-5);
		});
		assertEquals("Invalid discount", exception.getMessage());
	}
}

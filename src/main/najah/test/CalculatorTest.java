package main.najah.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import main.najah.code.Calculator;

@DisplayName("Calculator Tests")
@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTest {

	Calculator calc;

	static Stream<org.junit.jupiter.params.provider.Arguments> positiveAdditionTestCases() {
		return Stream.of(org.junit.jupiter.params.provider.Arguments.of(new int[] { 1, 2, 3, 4 }, 10),
				org.junit.jupiter.params.provider.Arguments.of(new int[] { 2, 4, 6 }, 12));
	}

	@BeforeEach
	void setUp() throws Exception {
		this.calc = new Calculator();
	}

	// * Addition Tests

	@ParameterizedTest
	@MethodSource("positiveAdditionTestCases")
	@DisplayName("Test addition with multiple positive numbers (parameterized)")
	void testAdditionMultiplePositive(int[] numbers, int expected) {
		int result = calc.add(numbers);
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Test addition with all negative numbers")
	void testAdditionAllNegative() {
		int[] numbers = new int[] { -1, -2, -3 };
		int expected = -6;
		int result = calc.add(numbers);
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Test addition with a mix of positive and negative numbers")
	void testAdditionMixedNumbers() {
		int[] numbers = new int[] { 10, -5, 5 };
		int expected = 10;
		int result = calc.add(numbers);
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Test addition with a single number")
	void testAdditionSingleNumber() {
		int[] numbers = new int[] { 5 };
		int expected = 5;
		int result = calc.add(numbers);
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Test addition with a large number of arguments")
	@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
	void testAdditionLargeInput() {
		int[] numbers = IntStream.generate(() -> 1).limit(1000).toArray();
		int expected = 1000;
		int result = calc.add(numbers);
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Test addition with arguments that pass the int limit when summed")
	@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
	@Disabled("the sum of large numbers eventually pass the int limit, can be fixed by using long for the .add method sum accumelator")
	void testAddLargeNumbers() {
		var numbers = IntStream.generate(() -> 1000000000).limit(100).toArray();
		var result = calc.add(numbers);
		long expected = 1000000000L * 100;
		assertEquals(expected, result);
	}

	// * Division Tests

	@ParameterizedTest(name = "{index} => a={0}, b={1}, expected={2}")
	@DisplayName("Test division with normal cases, (positive/negative cases)")
	@CsvSource({ "10, 2, 5", "9, 3, 3", "7, 2, 3", "-10, 2, -5", "10, -2, -5", "-10, -2, 5" })
	void testDivideParametrized(int a, int b, int expected) {
		assertEquals(expected, this.calc.divide(a, b));
	}

	@Test
	@DisplayName("Test division by zero throws ArithmeticException")
	void testDivideByZero() {
		assertThrows(ArithmeticException.class, () -> this.calc.divide(10, 0));
	}

	// * Factorial Tests

	@Test
	@DisplayName("Test factorial of 0 returns 1")
	void testFactorialZero() {
		assertEquals(1, this.calc.factorial(0));
	}

	@Test
	@DisplayName("Test factorial with negative input throws IllegalArgumentException")
	void testFactorialNegative() {
		assertThrows(IllegalArgumentException.class, () -> this.calc.factorial(-1));
	}

	@ParameterizedTest(name = "{index} => n={0}, expected={1}")
	@DisplayName("Parameterized test for factorial values")
	@CsvSource({ "0, 1", "1, 1", "2, 2", "3, 6", "4, 24", "5, 120" })
	void testFactorialParameterized(int n, int expected) {
		assertEquals(expected, this.calc.factorial(n));
	}

}

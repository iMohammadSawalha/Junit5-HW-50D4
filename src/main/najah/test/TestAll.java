package main.najah.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ CalculatorTest.class, ProductTest.class, RecipeTest.class, RecipeBookTest.class,
		RecipeExceptionTest.class, UserServiceTest.class })
public class TestAll {

}

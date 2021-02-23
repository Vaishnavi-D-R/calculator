package com.mindtree.ArithmeticOperations;

	import com.mindtree.ArithmeticOperations.App;
	
	public class ArithmeticOperationsTest {
	private App objCalcUnderTest;

	
	public void setUp() {
	//Arrange
	objCalcUnderTest = new App();
	}

	
	public void testAdd() { 
	int a = 15; int b = 20; 
	int expectedResult = 35;
	//Act 
	long result = objCalcUnderTest.add(a, b);
	//Assert
	Assert.assertEquals(expectedResult, result);
	}

	
	public void testSubtract() {
	int a = 25; int b = 20; 
	int expectedResult = 5; 
	long result = objCalcUnderTest.subtract(a, b);
	Assert.assertEquals(expectedResult, result);
	}

	
	public void testMultiply() {
	int a = 10; int b = 25;
	long expectedResult = 250;
	long result = objCalcUnderTest.multiply(a, b);
	Assert.assertEquals(expectedResult, result);
	}

	
	public void testDivide() {
	int a = 56; int b = 10; 
	double expectedResult = 5.6; 
	double result = objCalcUnderTest.divide(a, b);
	Assert.assertEquals(expectedResult, result,0.00005); 
	}

	
	public void testDivideByZero() { 
	int a = 15; int b = 0;
	objCalcUnderTest.divide(a, b);
	} 
	}

